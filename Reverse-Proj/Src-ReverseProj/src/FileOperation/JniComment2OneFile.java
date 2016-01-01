package FileOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JniComment2OneFile {
	private static String IN_PATH=
			"D:\\ThesisDesign\\sp2_1refresh_1result_1parseWithComments.txt";
	private static String OUT_PATH=
			"D:\\ThesisDesign\\sp2_1refresh_1result_1parseWithCommentsInSummary.txt";
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader(IN_PATH));
		BufferedWriter bw=new BufferedWriter(new FileWriter(OUT_PATH));
		String string;
		while((string=br.readLine())!=null){
			string.trim();
//			System.out.println(string);
			Pattern pattern=Pattern.compile(".*@([A-Z]|[a-z])*");
			
			Matcher matcher=pattern.matcher(string);
			Pattern pattern2=Pattern.compile(".*;\\s+\".*\"");
			
			Matcher matcher2=pattern2.matcher(string);
			if(matcher.matches()){
				bw.write("@"+string.split("@")[1]);
				bw.newLine();
				System.out.println(string);
				bw.newLine();
			}
			else if(matcher2.matches()){
				bw.write("@@@@"+string.split(";")[1]);
				bw.newLine();
				System.out.println(string);
			}
		}
		bw.close();
		br.close();
		System.out.println("Finished");
	}

}
