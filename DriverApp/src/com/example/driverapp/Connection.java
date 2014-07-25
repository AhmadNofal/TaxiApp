package com.example.driverapp;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import android.os.StrictMode;


public class Connection 
{   
	/**
	 * 
	 * @param m the web page 
	 */
   static public void Run(String m) 

   {
	   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
       .detectAll()
       .penaltyLog()        
       .build());
    
       StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()       
       .build());
       			
	   HttpResponse response;
       HttpClient myClient = new DefaultHttpClient();       
       HttpGet myConnection = new HttpGet(m);
      
          try {
           response = myClient.execute(myConnection);        
            
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
        
   }
   static public String Get(String path)
   {
	   
	   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
       .detectAll()
       .penaltyLog()       
       .build());
    
       StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()       
       .build());                   
        
       
       String str = "";
       HttpResponse response;
       HttpClient myClient = new DefaultHttpClient();
       HttpGet myConnection = new HttpGet(path);
        
       try {
           response = myClient.execute(myConnection);
           str = EntityUtils.toString(response.getEntity(), "UTF-8");
            
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }                             
        return str;
   }
}
