package com.xjtu.sglab.exp;

import com.xjtu.sglab.gateway.comm.ACctrl;

public class ExpTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="5aa5aa555aa5aa550000000000000000000000000000000000000000000000004bd2000011276a008c82901f110d43b402000000b1be0000f67d8af808934aebffebd86bfec1da30";
		for(int i=0;i<str.length()/2;i++){
			System.out.println("(byte)0x"+str.charAt(i*2)+str.charAt(i*2+1)+",");
		}

	}

}
