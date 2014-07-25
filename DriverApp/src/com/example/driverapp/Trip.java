package com.example.driverapp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Trip {
	
	private Date start_time ; 
	private Date end_time ; 
	private int counter =0 ;
	private int type = 0  ; 
	private boolean finish;
	private ArrayList<Order> trip_order = new ArrayList<Order>() ;
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	} 
	public void add_trip() 
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String currentDateandTime = sdf.format(Calendar.getInstance().getTime());
		String path = "http://taxiapp.prana-co.com/add_trip.php?TB="+currentDateandTime+"&T="+type+"&count="+counter ;
		Connection.Run(path); 
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public ArrayList<Order> getTrip_order() {
		return trip_order;
	}
	public void setTrip_order(ArrayList<Order> trip_order) {
		this.trip_order = trip_order;
	}

}
