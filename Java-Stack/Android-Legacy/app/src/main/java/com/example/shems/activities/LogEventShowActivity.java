package com.example.shems.activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;

 
import com.example.smarthome.R;
import com.example.shems.daos.sqllite.EventLogDB;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class LogEventShowActivity extends  Activity {
	EventLogDB db_eventlog=null;
	final static String FOLDER = "/sample/";  
	final static String FILENAME = "sample";   
	final static String SUFFIX = ".txt"; // suffix could be replaced on demand  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_event_show);
		StringBuilder sb=new StringBuilder();
		sb.append("ccccccccc");
		this.writeFile(sb);
		String path=Environment.getExternalStorageDirectory().getPath()  
                + FOLDER +FILENAME + SUFFIX;
		Log.i("path", path);
        this.readFile(path);
		String str=this.readFile(path);
		Log.i("str", str);
		Toast.makeText(this, str, 3000).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_event_show, menu);
		return true;
	}
	private void writeFile(StringBuilder sb) {  
	    String foldername = Environment.getExternalStorageDirectory().getPath()  
	                            + FOLDER;  
	    File folder = new File(foldername);  
	    if (folder != null && !folder.exists()) {  
	        if (!folder.mkdir() && !folder.isDirectory())  
	        {  
	            Log.d("TAG", "Error: make dir failed!");  
	        }  
	    }  
//	  if(!folder.exists())
//	  {
//		  return;
//	  }
	    String stringToWrite = sb.toString();  
	    String targetPath = foldername + FILENAME + SUFFIX;  
	    File targetFile = new File(targetPath);  
	    if (targetFile != null) {  
	        if (targetFile.exists()) {  
	            targetFile.delete();  
	        }  
	  
	        OutputStreamWriter osw;  
	        try{  
	            osw = new OutputStreamWriter(  
	                        new FileOutputStream(targetFile),"utf-8");  
	            try {  
	                    osw.write(stringToWrite);  
	                    osw.flush();  
	                    osw.close();  
	                } catch (IOException e) {  
	                    // TODO Auto-generated catch block  
	                    e.printStackTrace();  
	                }  
	        } catch (UnsupportedEncodingException e1) {  
	                // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        } catch (FileNotFoundException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        }  
	    }  
	    Log.i("end write", "end write");
	}  
	
	private String readFile(String filepath) {  
	    String path = filepath;  
	    if (null == path) {  
	        Log.d("TAG", "Error: Invalid file name!");  
	        return null;  
	    }  
	  
	    String filecontent = null;  
	    File f = new File(path);  
	    if (f != null && f.exists())  
	    {  
	        FileInputStream fis = null;  
	        try {  
	            fis = new FileInputStream(f);  
	        } catch (FileNotFoundException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	            Log.d("TAG", "Error: Input File not find!");  
	            return null;  
	        }  
	  
	        CharBuffer cb;  
	        try {  
	            cb = CharBuffer.allocate(fis.available());  
	        } catch (IOException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	            Log.d("TAG", "Error: CharBuffer initial failed!");  
	            return null;  
	        }  
	  
	        InputStreamReader isr;  
	        try {  
	            isr = new InputStreamReader(fis, "utf-8");  
	            try {  
	                if (cb != null) {  
	                   isr.read(cb);  
	                }  
	                filecontent = new String(cb.array());  
	                isr.close();  
	             } catch (IOException e) {  
	                e.printStackTrace();  
	             }  
	        } catch (UnsupportedEncodingException e) {  
	        // TODO Auto-generated catch block  
	            e.printStackTrace();          
	        }  
	    }  
	    Log.d("TAG", "readFile filecontent = " + filecontent);  
	    return filecontent;  
	}  
}
