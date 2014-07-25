package com.example.driverapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Traffic_load {
		
	 public static ArrayList<Location_app> get_traffic_load (int id)
	 {
		 JSONObject json = null;		 
		 ArrayList<Location_app> traffic_load = new ArrayList<Location_app>();
		 String path="http://taxiapp.prana-co.com/traffic_load.php?ID="+id;		 
		 String str= Connection.Get(path);
		 Log.d("trn", str);
		   try {
		       JSONArray jArray = new JSONArray(str);
		       int i=0;
		       while ( i< jArray.length())			        
		       {
			       	json = jArray.getJSONObject(i);
			       	Location_app temp=new  Location_app();
			       /*	temp.setValid(Integer.parseInt(json.getString("valid")));
			       	temp.setFinish(Boolean.valueOf(json.getString("finish")));
			       	float longitude= Float.parseFloat(json.getString("Long"));
			       	float latitude = Float.parseFloat(json.getString("Late"));
			       	Location_app loc = new  Location_app();
			       	loc.setLatitude(latitude);
			       	loc.setLongitude(longitude);
			       	  temp.setNum_of_passenger(Integer.valueOf(json.getString("NumofPassenger")));
			        temp.setPrice(Integer.valueOf(json.getString("orderprice")));
			       	temp.loc=loc;*/
			       	float longitude= Float.parseFloat(json.getString("Longitude"));			       	
			       	float latitude = Float.parseFloat(json.getString("Latitude"));			       	
			       	temp.setLatitude(latitude);
			       	temp.setLongitude(longitude);
			        traffic_load.add(temp);
			      			      
			       	i++;       	      
		       }
		   }
		   catch (JSONException e) {
			   e.printStackTrace();
		   }            
	       return traffic_load;
	 }
}
