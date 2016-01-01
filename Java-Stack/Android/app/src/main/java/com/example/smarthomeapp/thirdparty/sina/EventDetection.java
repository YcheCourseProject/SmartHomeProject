package com.example.smarthomeapp.thirdparty.sina;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.*; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;

public class EventDetection {	
	/**--------------------------------------------------------说明--------------------------------------------------------//
	 	目前程序仅支持对单一事件+单一时间的信息检测
	 	主程序detection的输入为：原文本（不包括发送时间）、发送（接收）时间、类型（0：短信，1：微博）
	 	主程序detection的输出为一个ArrayList，该List的每一项为一个字符串数组String[]，数组格式为{事件编号, 起始时间, 结束时间}
	 	起始时间与结束时间默认为原文本的发送（接收）时间，如原文本中有可提取的时间信息，则修正为文本中的时间
	 	起始时间与结束时间格式：yyyy-MM-dd HH:mm:ss
	 	事件类型使用约定的编号如下：
		|1 - 运动类|
		|2 - 吃饭类|
		|3- 延迟类|
	//--------------------------------------------------------------------------------------------------------------------*/
	
	//时间与事件检测的主函数
	public static ArrayList<String[]> detection(String message, String post_time_str, int type){
		ArrayList<String[]> results = new ArrayList<String[]>();
		//**************************未完*****************************//
		String event = eventTypeDetection(message);//event为事件类别1,2,3
		String[] detected_time = timeDetection(message, post_time_str, type);
		if (!"".equals(event) && detected_time[0] != null){
			results.add(new String[]{event, detected_time[0], detected_time[1]});
		}
		return results;
	}
	
	//文本中的事件信息类别（1,2,3）识别
	public static String eventTypeDetection(String message){
		//**************************未完*****************************//
		ArrayList<Pattern> pattern_list = new ArrayList<Pattern>();
		
		//1: 运动相关事件
		pattern_list.add(Pattern.compile("(打.*?球|踢.*?球|游(个|)泳|跑(个|)步|健(个|)身|跳(个|)舞|运动)"));
		
		//2: 吃饭相关事件
		pattern_list.add(Pattern.compile("(吃.*?(饭|餐)|就餐)"));
		
		//3: 会造成延迟的事件
		pattern_list.add(Pattern.compile("(加(个|)班|堵车)"));
		
		for (int i=1;i < pattern_list.size()+1;i++){
			Pattern p = pattern_list.get(i-1);
			Matcher m = p.matcher(message);
			if (m.find()){
				return i+"";
			}
		}
		return "";
	}
	
	//中文数字转阿拉伯数字
	public static String chinese2number(String message){
		String message_new = message;
		
		//中文数字字符串转换
		Map<String, String> chinese2number = new HashMap<String, String>();
		chinese2number.put("半", "0.5");
		chinese2number.put("零", "0");
		chinese2number.put("一", "1");
		chinese2number.put("二", "2");
		chinese2number.put("两", "2");
		chinese2number.put("俩", "2");
		chinese2number.put("三", "3");
		chinese2number.put("仨", "3");
		chinese2number.put("四", "4");
		chinese2number.put("五", "5");
		chinese2number.put("六", "6");
		chinese2number.put("七", "7");
		chinese2number.put("八", "8");
		chinese2number.put("九", "9");
		chinese2number.put("十", "10");
		
		for(Map.Entry<String, String> entry:chinese2number.entrySet()){//先做替换
			message_new = message_new.replaceAll(entry.getKey(), entry.getValue());
		}

		//------查找所有替换后的数字部分，针对>10的数字做处理------//
		Pattern number_pattern = Pattern.compile("(\\d+)");
		Matcher number_matcher = number_pattern.matcher(message_new);
		while (number_matcher.find()){
			String number_1 = number_matcher.group(1);
			String number_2 = number_1;
			
			if (number_2.indexOf("10") > 0){//如果10前面还有数字，则将该数字乘以10，再与后面的拼接
				int first = Integer.parseInt(number_2.substring(0, number_2.indexOf("10")));
				number_2 = String.valueOf(first*10) + number_2.substring(number_2.indexOf("10")+2);
			}
			
			if (number_2.indexOf("0") >= 0 && number_2.indexOf("0") == number_2.length()-2){//如果0后面还有数字，则将该数字与前面的相加
				number_2 = String.valueOf(Integer.parseInt(number_2.substring(0, number_2.indexOf("0")+1)) + Integer.parseInt(number_2.substring(number_2.indexOf("0")+1)));
			}

			message_new = message_new.replaceFirst(number_1, number_2);
		}
		
		return message_new;
	}
	
	//文本中的时间信息识别
	public static String[] timeDetection(String message, String post_time_str, int type){
		
		//------如果文本中有“昨天”、“前天”、“上个月”等词语，直接返回空集------//
		Pattern expired_pattern = Pattern.compile("((昨|前)天|上(个|)(周|月))");
		Matcher expired_mathcer = expired_pattern.matcher(message);
		if (expired_mathcer.find()){
			return new String[]{null, null};
		}

		//------将原文本的发表（接收）时间转换为GregorianCalendar类型，作为基准时间(可进行加减运算)------//
		SimpleDateFormat format_0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//短信
		SimpleDateFormat format_1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZ yyyy",Locale.US);//微博
	
		Date post_time = null;
		switch (type) {
		case 0: try {//短信
						post_time = format_0.parse(post_time_str);//字符串类型post_time_str转化到日期类型
				} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
		case 1: try {//微博
						post_time = format_1.parse(post_time_str);//字符串类型post_time_str转化到日期类型
				} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
		}
		GregorianCalendar start_time = new GregorianCalendar();
		GregorianCalendar end_time = new GregorianCalendar();
		start_time.setTime(post_time);
		end_time.setTime(post_time);
		
		//记录时间信息字符串的提取结果
		List<Info> timeInfo_list = new ArrayList<Info>();
		
		//------将原文本中的中文数字全部转换为阿拉伯数字------//
		String message_new = chinese2number(message);
		
		//------各类时间信息的提取规则------//
		ArrayList<Pattern> time_patterns = new ArrayList<Pattern>();
		
		//0: 相对时间
		time_patterns.add(Pattern.compile("((\\d+)(个|)(0.5|)(月|星期|天|小时|分钟)(前|后|))"));
		
		//1: 一天之内的绝对时间（按小时-分钟表示）
		time_patterns.add(Pattern.compile("((\\d+)(点|:|：)(0.5|)(\\d+|)(分|))"));
		
		//2: 一天之内的绝对时间（描述性）
		time_patterns.add(Pattern.compile("(早上|上午|中午|下午|傍晚|晚上|半夜)"));
		
		//3: 绝对日期（按月-日表示）
		time_patterns.add(Pattern.compile("((\\d+)(月|)(\\d+|)(日|号))"));
		
		//4: 绝对日期（按星期几表示）
		time_patterns.add(Pattern.compile("((下|)(周|星期)(\\d+|日|天))"));
		
		//5: 绝对日期（描述性）
		time_patterns.add(Pattern.compile("((明|大后|后)天)"));
		
		//提取并记录所有时间信息
		for (int i=0;i<time_patterns.size();i++){
			Pattern p = (Pattern) time_patterns.get(i);
			Matcher m = p.matcher(message_new);
			while (m.find()){
				String result = m.group(1);
				Info timeInfo = new Info(result, i, message_new.indexOf(result));//每一条记录 = 时间信息 + 时间类型 + 在原文中的位置；
				timeInfo_list.add(timeInfo);
			}
		}
		
		//------将时间信息按其在原文中出现的位置排序------//
		Collections.sort(timeInfo_list, new SortByIndex());
		
		//------时间信息的识别------//
		Boolean is_today = true;//标识是否为当天
		
		for (Info timeInfo:timeInfo_list){
			String info = timeInfo.getInfo();
			switch (timeInfo.getType()){
			
			//相对时间处理：在基准时间基础上增加，目前只考虑当前时间之后的时间
			case 0:
				Pattern p0 = (Pattern) time_patterns.get(0);
				Matcher m0 = p0.matcher(info);
				m0.find();
				int n0 = Integer.parseInt(m0.group(2));
				switch (m0.group(5)){
				case "月":
					if ("前".equals(m0.group(6))){}//不考虑
					else if ("后".equals(m0.group(6))){//起始时间后滚
						start_time.add(GregorianCalendar.MONTH, n0);
						if (info.indexOf("0.5") >= 0){
							start_time.add(GregorianCalendar.DAY_OF_YEAR, 15);
						}
					}
					else {//结束时间后滚
						end_time.add(GregorianCalendar.MONTH, n0);
						if (info.indexOf("0.5") >= 0){
							end_time.add(GregorianCalendar.DAY_OF_YEAR, 15);
						}
					}
					break;
				case "星期":
					if ("前".equals(m0.group(6))){}//不考虑
					else if ("后".equals(m0.group(6))){//起始时间后滚
						start_time.add(GregorianCalendar.WEEK_OF_YEAR, n0);
						if (info.indexOf("0.5") >= 0){
							start_time.add(GregorianCalendar.DAY_OF_YEAR, 3);
						}
					}
					else {//结束时间后滚
						end_time.add(GregorianCalendar.WEEK_OF_YEAR, n0);
						if (info.indexOf("0.5") >= 0){
							end_time.add(GregorianCalendar.DAY_OF_YEAR, 3);
						}
					}
					break;
				case "天":
					if ("前".equals(m0.group(6))){}//不考虑
					else if ("后".equals(m0.group(6))){//起始时间后滚
						start_time.add(GregorianCalendar.DAY_OF_YEAR, n0);
						if (info.indexOf("0.5") >= 0){
							start_time.add(GregorianCalendar.HOUR_OF_DAY, 12);
						}
					}
					else {//结束时间后滚
						end_time.add(GregorianCalendar.DAY_OF_YEAR, n0);
						if (info.indexOf("0.5") >= 0){
							end_time.add(GregorianCalendar.HOUR_OF_DAY, 12);
						}
					}
					break;
				case "小时":
					if ("前".equals(m0.group(6))){}//不考虑
					else if ("后".equals(m0.group(6))){//起始时间后滚
						start_time.add(GregorianCalendar.HOUR_OF_DAY, n0);
						if (info.indexOf("0.5") >= 0){
							start_time.add(GregorianCalendar.MINUTE, 30);
						}
					}
					else {//结束时间后滚
						end_time.add(GregorianCalendar.HOUR_OF_DAY, n0);
						if (info.indexOf("0.5") >= 0){
							end_time.add(GregorianCalendar.MINUTE, 30);
						}
					}
					break;
				case "分钟":
					if ("前".equals(m0.group(6))){}//不考虑
					else if ("后".equals(m0.group(6))){//起始时间后滚
						start_time.add(GregorianCalendar.MINUTE, n0);
						if (info.indexOf("0.5") >= 0){
							start_time.add(GregorianCalendar.SECOND, 30);
						}
					}
					else {//结束时间后滚
						end_time.add(GregorianCalendar.MINUTE, n0);
						if (info.indexOf("0.5") >= 0){
							end_time.add(GregorianCalendar.SECOND, 30);
						}
					}
					break;
				}
				break;
				
			//一天之内的绝对时间（按小时-分钟表示）处理：需要结合基准时间考虑12小时制的情况（中午1点、晚上8点之类），目前只考虑当前时间后的时间
			case 1:
				Pattern p1 = (Pattern) time_patterns.get(1);
				Matcher m1 = p1.matcher(info);
				m1.find();
				int hour = Integer.parseInt(m1.group(2));
				int base_hour = start_time.get(GregorianCalendar.HOUR_OF_DAY);
				if (is_today){//如果为当天，则只考虑当天之后的时间
					//如果可根据时间差或前置修饰语判断出事件时间小于当前时间则直接返回
					if ((hour < base_hour && base_hour <= 12) || (hour < base_hour && (message.indexOf("早上") >= 0 || message.indexOf("上午") >= 0 || message.indexOf("中午") >= 0))){
						return new String[]{null, null};
					}
					else if (hour >= 0 && hour < 13 && hour < base_hour){//如果事件时间 > 当前时间，则修正12小时制
						hour = hour + 12;
					}
					start_time.set(GregorianCalendar.HOUR_OF_DAY, hour);
				}
				else {//如果不为当天，则根据前置修饰语修正12小时制
					if ((message.indexOf("中午") >= 0 && hour < 12) || ((message.indexOf("下午") >= 0 || message.indexOf("晚") >= 0 || message.indexOf("半夜") >= 0) && hour <= 12)){
						hour = hour + 12;
					}
					start_time.set(GregorianCalendar.HOUR_OF_DAY, hour);
				}
				
				if (!"".equals(m1.group(5))){//设置分钟
					start_time.set(GregorianCalendar.MINUTE, Integer.parseInt(m1.group(5)));
				}
				else if (info.indexOf("0.5") >= 0){//设置半小时
					start_time.set(GregorianCalendar.MINUTE, 30);
				}
				else {//没有分钟信息，默认为0
					start_time.set(GregorianCalendar.MINUTE, 0);
				}
				start_time.set(GregorianCalendar.SECOND, 0);
				break;
				
			//一天之内的绝对时间（描述性）
			case 2:
				//如果有具体时间则不对描述性词语进行处理
				Boolean hasTime = false;
				for (Info ti:timeInfo_list){
					if (ti.getType() == 1){hasTime=true;}
				}
				if (hasTime){break;}
				
				Date base_time = start_time.getTime();//作为改变前的参考基准时间
				switch (info){
				case "早上"://默认时间段：6点到9点
					start_time.set(GregorianCalendar.HOUR_OF_DAY, 6);
					end_time.set(GregorianCalendar.HOUR_OF_DAY, 9);
					break;
				case "上午"://默认时间段：8点到11点
					start_time.set(GregorianCalendar.HOUR_OF_DAY, 8);
					end_time.set(GregorianCalendar.HOUR_OF_DAY, 11);
					break;
				case "中午"://默认时间段：11点到14点
					start_time.set(GregorianCalendar.HOUR_OF_DAY, 11);
					end_time.set(GregorianCalendar.HOUR_OF_DAY, 14);
					break;
				case "下午"://默认时间段：14点到18点
					start_time.set(GregorianCalendar.HOUR_OF_DAY, 14);
					end_time.set(GregorianCalendar.HOUR_OF_DAY, 18);
					break;
				case "傍晚"://默认时间段：18点到20点
					start_time.set(GregorianCalendar.HOUR_OF_DAY, 18);
					end_time.set(GregorianCalendar.HOUR_OF_DAY, 20);
					break;
				case "晚上"://默认时间段：18点到23点
					start_time.set(GregorianCalendar.HOUR_OF_DAY, 18);
					end_time.set(GregorianCalendar.HOUR_OF_DAY, 23);
					break;
				case "半夜"://默认时间段：23点到24点
					start_time.set(GregorianCalendar.HOUR_OF_DAY, 23);
					end_time.set(GregorianCalendar.HOUR_OF_DAY, 24);
					break;
				}
				Date temp_start_time = start_time.getTime();
				if (is_today && temp_start_time.before(base_time)){//如果事件时间早于当前时间，则直接返回
					return new String[]{null, null};
				}
				else {
					start_time.set(GregorianCalendar.MINUTE, 0);
					end_time.set(GregorianCalendar.MINUTE, 0);
					start_time.set(GregorianCalendar.SECOND, 0);
					end_time.set(GregorianCalendar.SECOND, 0);
				}
				break;
				
			//绝对日期（按月-日表示）
			case 3:
				Pattern p3 = (Pattern) time_patterns.get(3);
				Matcher m3 = p3.matcher(info);
				m3.find();
				int base_month = start_time.get(GregorianCalendar.MONTH);//参考基准月
				int base_date = start_time.get(GregorianCalendar.DAY_OF_MONTH);//参考基准日期
				int month = start_time.get(GregorianCalendar.MONTH);//月份默认为当月
				int day = start_time.get(GregorianCalendar.DAY_OF_MONTH);//日期默认为当日
				if (!"".equals(m3.group(2))){
					if ("月".equals(m3.group(3))){//如果时间信息中指定月份，则设置为指定月份与日期
						month = Integer.parseInt(m3.group(2));
						day = Integer.parseInt(m3.group(4));
					}
					else {//如果没有指定月份，那么匹配到的第一个数字为日期
						day = Integer.parseInt(m3.group(2));
					}
				}
				if (month < base_month || (month == base_month && day < base_date)){//如果事件日期小于当前日期，则直接返回
					return new String[]{null, null};
				}
				else {
					
					is_today = false;
					start_time.set(GregorianCalendar.MONTH, month-1);
					start_time.set(GregorianCalendar.DAY_OF_MONTH, day);
					end_time.set(GregorianCalendar.MONTH, month-1);
					end_time.set(GregorianCalendar.DAY_OF_MONTH, day);
				}
				break;
				
			//绝对日期（按星期几表示）
			case 4:
				Pattern p4 = (Pattern) time_patterns.get(4);
				Matcher m4 = p4.matcher(info);
				m4.find();
				int base_day_of_week = start_time.get(GregorianCalendar.DAY_OF_WEEK);
				int day_of_week = base_day_of_week;
				if (info.indexOf("日") >= 0 || info.indexOf("天") >= 0){//处理星期天、周日
					day_of_week = 7;
				}
				else {
					day_of_week = Integer.parseInt(m4.group(4));
				}
				if (m4.group(2).indexOf("下") >= 0){//如果是下周，则直接指定日期
					int add_days = day_of_week - base_day_of_week;
					if (add_days < 0){add_days = add_days + 7;}
					start_time.add(GregorianCalendar.DAY_OF_YEAR, add_days);
					end_time.add(GregorianCalendar.DAY_OF_YEAR, add_days);
				}
				else {//如果是本周，则只处理大于当前时间的情况
					if (day_of_week > base_day_of_week){
						start_time.add(GregorianCalendar.DAY_OF_YEAR, day_of_week-base_day_of_week);
						end_time.add(GregorianCalendar.DAY_OF_YEAR, day_of_week-base_day_of_week);
					}
				}
				break;
				
			//绝对日期（描述性）
			case 5:
				is_today = false;
				if (info.indexOf("大后") >= 0){
					start_time.add(GregorianCalendar.DAY_OF_YEAR, 3);
					end_time.add(GregorianCalendar.DAY_OF_YEAR, 3);
				}
				else if (info.indexOf("后") >= 0){
					start_time.add(GregorianCalendar.DAY_OF_YEAR, 2);
					end_time.add(GregorianCalendar.DAY_OF_YEAR, 2);
				}
				else if (info.indexOf("明") >= 0){
					System.out.println(info);
					start_time.add(GregorianCalendar.DAY_OF_YEAR, 1);
					end_time.add(GregorianCalendar.DAY_OF_YEAR, 1);
				}
				break;
			}
		}
		
		//------返回最终识别出的时间信息------//
		Date detected_start_time = start_time.getTime();
		Date detected_end_time = end_time.getTime();
		if (detected_start_time.after(detected_end_time)){//若结束时间小于起始时间
			return new String[]{format_0.format(detected_start_time), format_0.format(detected_start_time)};
		}
		else{
			return new String[]{format_0.format(detected_start_time), format_0.format(detected_end_time)};
		}
	}

	
	//存储提取出的原始信息、信息的种类、信息在原文中的位置
	public static class Info{
		private String info;
		private int type;
		private int index;
		public String getInfo() {
			return this.info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public int getType() {
			return this.type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getIndex() {
			return this.index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public Info(String info, int type, int index){
			this.info = info;
			this.type = type;
			this.index = index;
		}
	}

	//按信息在原文中的位置排序
	public static class SortByIndex implements Comparator {
		public int compare(Object o1, Object o2) {
			Info s1 = (Info) o1;
			Info s2 = (Info) o2;
			if (s1.getIndex() > s2.getIndex()){
				return 1;
			}
			else if (s1.getIndex() == s2.getIndex()) {
				return 0;
			}
			else {
				return -1;
			}
		}
	}

}

