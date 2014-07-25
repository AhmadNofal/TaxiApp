package com.example.driverapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import com.example.driverapp.Location_app;
import com.example.driverapp.Driver;

import android.R.integer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;


public class Update_location extends BroadcastReceiver {
	int id=0;
	

	@Override
	public void onReceive(Context context, Intent intent ) {
		// TODO Auto-generated method stub
		Log.d("recv","taxi");						
		File Info=new File(Environment.getExternalStorageDirectory().getPath() + "/info_taxi.txt");
		if(Info.exists()){
			BufferedReader reader;
			try {
				reader = new  BufferedReader(new FileReader(Info));
				String line;
				int i=0;
				while ((line=reader.readLine())!= null)
				{ 
					if(i==0)
					{
					 id=Integer.valueOf(line);
					 i++;
					}
				}
			} catch (  FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();			
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Log.d("id",Integer.toString(id));
			LocationListener loc=new LocationListener() {
				
				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onLocationChanged(Location location) {
					// TODO Auto-generated method stub
						Log.d("location","change");
						Location_app loc=new Location_app();
						loc.setLatitude((float) location.getLatitude()); 
						loc.setLongitude((float) location.getLongitude());						
						loc.updatelocation(id);
						
					}

				
			};
			
			LocationManager mLocationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
			if(mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
				mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, loc);
				Log.d("taxi","enable");
				if(mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null){
				    Location l= new Location(mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
				    if(l!=null){
				    	Log.d("location",Double.toString(l.getLongitude()));
				    	
				    
				    	
				    	
				    	}
					}								
				  
			}
		    
		}

	}

}
