package FileOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.Iterator;

import models.CodeLine;

public class JniParseComments {

//	private static String INPUT_FILE_PATH_NAME = "D:\\ThesisDesign\\sp2_1refresh_1result_1parse.txt";
//	private static String OUTPUT_FILE_PATH_NAME_IN = "D:\\ThesisDesign\\sp2_1refresh_1result_1parseWithComments.txt";
//	private static String INPUT_FILE_PATH_NAME = "D:\\ThesisDesign\\sp2_set_timer_byte2.txt";
//	private static String OUTPUT_FILE_PATH_NAME_IN = "D:\\ThesisDesign\\sp2_set_timer_byte2WithComments.txt";
//	private static String INPUT_FILE_PATH_NAME = "D:\\ThesisDesign\\sp2_1get_1day_1energy_1parse.txt";
//	private static String OUTPUT_FILE_PATH_NAME_IN = "D:\\ThesisDesign\\cylsp2_1get_1day_1energy_1parseWithComments.txt";
	private static String INPUT_FILE_PATH_NAME = "D:\\ThesisDesign\\broadlink_network_senddata.txt";
	private static String OUTPUT_FILE_PATH_NAME_IN = "D:\\ThesisDesign\\broadlink_network_senddataWithComments.txt";
	/**
	 * @param args
	 * 首先获取指令集合Set
	 * 再获取JNINativeInterface结构体中Index与方法名键值对集合
	 * 再自动生成JNI函数的注释
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Set instructionSet = readInstructions2Set();
		Map<String, String> instructionIndexMap = readIndexMethodName2Map();
		System.out.println(instructionIndexMap);
		
		System.out.println(instructionIndexMap.get("908"));
		scanFile2GenerateComments(null, instructionIndexMap);
	}

	/**
	 * @param instructionSet          指令集合
	 * @param instructionIndexMap     JNINativeInterface结构中Index与Key之间的键值对集
	 * @throws Exception
	 */
	private static void scanFile2GenerateComments(Set instructionSet,
			Map<String, String> instructionIndexMap) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(
				INPUT_FILE_PATH_NAME));
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				OUTPUT_FILE_PATH_NAME_IN));
		String line;
		Stack<String> codeStack = new Stack();
		while ((line = br.readLine()) != null) {
			bw.write(line);
			if (line.contains("BLX")) {
				line = trimLineString(line);
				String registerName = line.split("\\s+")[1];
				if (registerName.startsWith("R")) {

					System.out.println(codeStack);
					int index = getRelativeAddr(codeStack, registerName);
					// String method=(String) instructionIndexMap.get(index);
					System.out.println("~~~handle" + registerName + "\t"
							+(String)instructionIndexMap.get(String.valueOf(index)));
					bw.write("\t\t\t\t\t\t\t"+"@"+instructionIndexMap.get(String.valueOf(index)));
					codeStack.clear();
				}
			}
			if ((line.contains("MOVS") || line.contains("ADDS") || line
					.contains("LDR"))
					&& !line.contains("SP")
					&& !line.contains("var") && !line.contains("=")) {
				line = trimLineString(line);
				codeStack.push(line);
			}
			bw.newLine();
		}
		br.close();
	    bw.close();
	}

	
	/**
	 * @param codeStack  传入代码行字符串的栈
	 * @param regName    传入BLX后面跟的寄存器名臣
	 * @return           偏移地址
	 * 还有BUG，有些跟之前代码密切相关的算不出来，没有考虑LSL等指令
	 */
	private static int getRelativeAddr(Stack<String> codeStack, String regName) {
		String curRegName = regName;
		int sum = 0;
		while (!codeStack.isEmpty()) {
			String line = codeStack.pop();
			CodeLine codeLine = new CodeLine(line);
			if (codeLine.getDestReg().equals(curRegName)) {
				// 终止条件 立即数
				if (codeLine.getRegArray() == null) {
					return codeLine.getInstantNumber();
				}
				// 终止条件 [R5]
				else if (codeLine.getRegArray().length == 1
						&& codeLine.getRegArray()[0].equals("R5")) {
					return 0;
				}
				// 正常递归
				else {
					for (String tmpRegName : codeLine.getRegArray())
						sum += getRelativeAddr(
								(Stack<String>) codeStack.clone(), tmpRegName);
					sum += codeLine.getInstantNumber();
				}
				return sum;
			}
		}
//		return 0x1B4;			//SetIntField
		return -1;				//有待改进。。。没有考虑不存在栈里面的之前指令中变量
	}

	/**
	 * @param line 传入需要处理的一行字符串
	 * @return     返回去除尾部;注释的字符串
	 */
	private static String trimLineString(String line) {
		line = line.split(";")[0]; // 去除尾部的额注释
		String[] strs = line.split("\\s+");
		StringBuilder sb = new StringBuilder();
		for (String tmpstr : strs) {
			if (!tmpstr.startsWith(".")) {
				sb.append(tmpstr).append(" ");
			}
		}
		return sb.toString();
	}

	/**
	 * 用来测试查看代码格式的方法
	 * @throws Exception
	 */
	private static void test() throws Exception {
		Set instructionSet = readInstructions2Set();
		BufferedReader br = new BufferedReader(new FileReader(
				INPUT_FILE_PATH_NAME));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.startsWith(".text")) {
				String[] strs = line.split("\\s+");
				if (strs.length > 2) {
					if (instructionSet.contains(strs[1])) {					
						if (strs[1].startsWith("LDR") && !line.contains("var")
								&& !line.contains("SP") && !line.contains("=")) {
							System.out.println(line);
						}
					}
				}
			}
		}
	}

	/**
	 * @return 指令的集合 如MOV,LDR等 （数据结构为HashSet，返回一个接口Set）
	 * @throws Exception
	 */
	private static Set<String> readInstructions2Set() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(
				INPUT_FILE_PATH_NAME));
		String line;
		Set<String> set = new HashSet<String>();
		while ((line = br.readLine()) != null) {
			if (line.startsWith(".text")) {
				String[] strs = line.split("\\s+");
				// System.out.println(strs.length);
				if (strs.length > 2) {
					Pattern pattern = Pattern.compile("[A-Z]+");
					Matcher matcher = pattern.matcher(strs[1]);
					if (matcher.matches()) {
						if (!strs[1].startsWith("B")) {
							pattern = Pattern
									.compile("[A-Z]{1}([0-9]|[A-Z]){1},");
							matcher = pattern.matcher(strs[2]);
							if (!matcher.matches()) {
								continue;
							}
						}
						if (!set.contains(strs[1])) {
							set.add(strs[1]);
							// System.out.println(strs[1]);
						}
					}
				}
			}
		}
		br.close();
		return set;
	}

	/**
	 * @return JNINativeInterface结构体中 index与方法名的键值对（数据结构为HashMap，返回一个接口Map）
	 * @throws Exception
	 */
	private static Map<String, String> readIndexMethodName2Map()
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		BufferedReader bufReader = new BufferedReader(new FileReader(
				"D://cylinterface.txt"));
		String str;
		while ((str = bufReader.readLine()) != null) {
			str = str.trim();
			String strs[] = str.split("\\s+");
			strs[1] = strs[1].replaceAll("index:", "");
			strs[0] = strs[0].replaceAll(",", "");
			map.put(Integer.parseInt(strs[1]) * 4 + "", strs[0]);
		}
		bufReader.close();
		return map;
	}
}
