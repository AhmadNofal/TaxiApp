package com.example.driverapp;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.internal.ky;

import android.util.Log;

public class Order  
{
	private int valid;
	private Location_app cust_loc;	
	private Location_app des_loc;
	private int num_of_passenger;
	private boolean finish;
	private float price;
	private int cust_id;
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public ArrayList<Order> getorder (int id )
	{
		 JSONObject json = null;
		 ArrayList<Order> myorder= new ArrayList<Order>();
		 String path="http://taxiapp.prana-co.com/getordergood.php?ID="+id;		 	
		 String str= Connection.Get(path);
		
		   try {
		       JSONArray jArray = new JSONArray(str);
		       int i=0;
		       while ( i< jArray.length())			        
		       {
			       	json = jArray.getJSONObject(i);			        
			       	Order temp=new  Order();
			       	temp.setCust_id(Integer.valueOf((json.getString("Cust_id"))));			       	
			       	float longitude= Float.parseFloat(json.getString("Long"));
			       	float latitude = Float.parseFloat(json.getString("Late"));
			       	Location_app loc = new  Location_app();
			       	loc.setLatitude(latitude);
			       	loc.setLongitude(longitude);
			       	temp.setdest_Loc(loc);
			       	temp.setNum_of_passenger(Integer.valueOf(json.getString("NumofPassenger")));			       	
			        myorder.add(temp);			      			      
			       	i++;			        
			      
		       }
		     
		   }
		   catch (JSONException e) {
			   e.printStackTrace();
		   }  
		  
		   for(int i=0;i<myorder.size();i++)
		   {
			   
			   int id_cust=myorder.get(i).getCust_id();			   
			   JSONObject json_cust = null;			
			   String path_cust="http://taxiapp.prana-co.com/get_customer.php?id_cus="+id_cust;
			   String str_cus= Connection.Get(path_cust);
			   
			   try {
			       JSONArray jArray = new JSONArray(str_cus);
			        json_cust = jArray.getJSONObject(0);
			        float longitude= Float.parseFloat(json_cust.getString("Long"));
			       	float latitude = Float.parseFloat(json_cust.getString("Late"));
			       	Location_app loc = new  Location_app();
			       	loc.setLatitude(latitude);
			       	loc.setLongitude(longitude);
			       	myorder.get(i).setCust_loc(loc);
			   }
			   catch (JSONException e) {
				   e.printStackTrace();
			   }
		   }
		  
	       return myorder;
	}
	public Location_app getlocpassenger()
	{
		return null;
	}
	
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public Location_app getdest_Loc() {
		return des_loc;
	}
	public void setdest_Loc(Location_app loc) {
		this.des_loc = loc;
	}	
	public Location_app getCust_loc() {
		return cust_loc;
	}	
	public void setCust_loc(Location_app cust_loc) {
		this.cust_loc = cust_loc;
	}
	public int getNum_of_passenger() {
		return num_of_passenger;
	}
	public void setNum_of_passenger(int num_of_passenger) {
		this.num_of_passenger = num_of_passenger;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
