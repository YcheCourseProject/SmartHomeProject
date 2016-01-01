package FileOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

public class JniTextFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	 BufferedReader bufReader=new BufferedReader(new FileReader("D://interfaces.txt"));
	 BufferedWriter bufWriter=new BufferedWriter(new FileWriter("D://cylinterface.txt"));
	 String str;
	 int index=0;
	 StringBuilder sb=null;
	 while((str=bufReader.readLine())!=null){
		 if(str.trim().length()>0){			 
			 str=patchSpace(33,sb,str);
			 String indexDecStr=index+"";
			 indexDecStr=patchSpace(8,sb,indexDecStr);
			 String addrHexStr=Integer.toHexString(index*4);
			 addrHexStr=patchSpace(8,sb,addrHexStr);
			 String normalHexStr =Integer.toHexString(index);
			 normalHexStr=patchSpace(8,sb,normalHexStr);
			 str=str+"    index:"+indexDecStr+
					 "    addrHexStr:0x"+addrHexStr+
					 "    normalHexStr:0x"+normalHexStr;
			 bufWriter.write(str);
			 bufWriter.newLine();
			 index++;
		 }
	 }
	 bufReader.close();
	 bufWriter.close();
	 System.out.println("ok");
	}

	private static String patchSpace(int length,StringBuilder sb,String str){
		 if(str.length()<length){
			 sb=new StringBuilder(str);
			 while(sb.length()<length)
			   sb.append(" ");
			 str=sb.toString();
		 }
		 return str;
	}
}
