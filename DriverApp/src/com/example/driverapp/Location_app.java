package com.example.driverapp;



public class Location_app 
{
	
	private float Longitude;
	private float Latitude;	
	public int id_driver ; 
	public float getLongitude() {
		return Longitude;
	}
	public void setLongitude(float longitude) {
		Longitude = longitude;
	}
	public float getLatitude() {
		return Latitude;
	}
	public void setLatitude(float latitude) {
		Latitude = latitude;
	}
	
	public void updatelocation (int id )
	{
		        
		String g="http://taxiapp.prana-co.com/UpdDrvrLoc.php?long="+getLongitude()+"&id="+id_driver+"&lat="+getLatitude();
		Connection.Run(g);		              
		
	}
}
