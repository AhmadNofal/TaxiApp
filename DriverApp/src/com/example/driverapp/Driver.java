package com.example.driverapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Application;
import android.util.Log;



public class Driver   {
	
	private Car taxi ;
	private int driver_id ; 
	private Location_app driver_location ; 
	private String driver_name ; 
	private int driver_rate ;
	private int driver_phone ;
	private Messege driver_message = new Messege() ;			
	
	public Car getTaxi() {
		return taxi;
	}

	public void setTaxi() {
		
		JSONObject json = null;
		 Car temp=new  Car();
		String path = "http://taxiapp.prana-co.com/gettaxi.php?ID="+ driver_id ;
		String str = Connection.Get(path); 
		try {
		       JSONArray jArray = new JSONArray(str);
		       int i=0;		      
		       while ( i< jArray.length())			        
		       {
			       	json = jArray.getJSONObject(i);
			     
			       	temp.setType(json.getString("type"));
			       	temp.setModel(json.getString("model"));			       				       			     			    			       
			        temp.setLuxury(Integer.valueOf(json.getString("luxury")));
			        temp.setFuel(Integer.valueOf(json.getString("fuel")));
			        temp.setYear_Manuf(Integer.valueOf(json.getString("year")));
			        temp.setStatus(Integer.valueOf(json.getString("status")));
			        temp.setSeat(Integer.valueOf(json.getString("seat")));
			        temp.setNumber(Integer.valueOf(json.getString("number")));
			       	i++;       	      
		       }
		   }
		   catch (JSONException e) {
			   e.printStackTrace();
		   }         
		this.taxi = temp;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public  void setDriver_id(int diver_id) {
		driver_id = diver_id;
	} 
	public Location_app getDriver_location() {
		return driver_location;
	}

	public void setDriver_location(Location_app driver_location) {
		this.driver_location = driver_location;
	} 
	
	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String diver_name) {
		this.driver_name = diver_name;
	} 
	
	public int getDriver_rate() {
		return driver_rate;
	}

	public void setDriver_rate() {
		
		String path="http://taxiapp.prana-co.com/driver_rate.php?Id="+driver_id;
		String resString=Connection.Get(path);
		int result=Integer.valueOf(resString);	
		this.driver_rate = result;
	} 
	public int getDriver_phone() {
		return driver_phone;
	}

	public void setDriver_phone() {
		
		String path="http://taxiapp.prana-co.com/driver_phone.php?Id="+driver_id;
		String resString=Connection.Get(path);
		int result=Integer.valueOf(resString);	
		this.driver_phone = result;
	} 
	public int login (String Name, String Password)
	{
		String path="http://taxiapp.prana-co.com/driver_login.php?Name="+Name+"&Password="+Password;
		String resString=Connection.Get(path);
		int result=0;
		Log.d("ahmad",resString ); 
		if(! resString.matches(""))
		{
		
		     result=Integer.parseInt(resString);			
			setDriver_id(result);
			setDriver_name(Name); 
		
		}
		return result;	
	}

}
