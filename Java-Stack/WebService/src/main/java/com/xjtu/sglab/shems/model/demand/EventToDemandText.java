package com.xjtu.sglab.shems.model.demand;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class EventToDemandText {

	private static boolean flag = true;

	public  void useModel() throws IOException, InterruptedException{
		
		  Runtime rt = Runtime.getRuntime(); 
		  
		  Process p = null;
		  
	      String command = "E:/data/HydroThermal.exe";

          p = rt.exec(command);
          
          new CleanThread(p.getErrorStream(), "ERROR").start();   
          new CleanThread(p.getInputStream(), "INPUT").start();  

//          int valueCode = p.waitFor();
//          p.destroy();
//          System.out.println(valueCode);
//		
          Thread.sleep(5000);
          
          if(!flag){
        	  System.out.println("fail");
          }else{
        	  System.out.println("success");
          }
          
	}
	
	private static void sleep() {
	
		
	}

	static class CleanThread extends Thread{
		
	    InputStream is;   
	    String type;   
	    
	    public CleanThread (InputStream is, String type) {   
	    	this.is = is;   
	    	this.type = type;   
	    }   
        public void run() {   
	      try {   
	    	  InputStreamReader isr = new InputStreamReader(is);  
	    	  BufferedReader br = new BufferedReader(isr);   
	          String line = null;   
	          while ((line = br.readLine()) != null){   
	    	      System.out.println(type + ">" + line);              //控制台输出  
	    	      if(line.toLowerCase().contains("exception")){
	    	    	  flag = false;
	    	      }
	          }  
	      } catch (IOException ioe) {   
	    	  ioe.printStackTrace();   
	   	  }   
	   }   

		
		
	}
}
