package FileOperation;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CreateFileMapDecimal2Hex {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedWriter bw=new BufferedWriter(new FileWriter("D://mymapping.txt"));
		for(int i=0x1d;i<0x16d;i++){
			String string="IndexDecimal:"+(335-(i-0x1d))+"\t\tStackAddrHex:0x"+Integer.toHexString(i);
			bw.write(string);
			bw.newLine();
		}
		bw.close();
		System.out.println("Finished");
	}

}
