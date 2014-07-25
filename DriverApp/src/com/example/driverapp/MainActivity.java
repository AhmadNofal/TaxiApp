package com.example.driverapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	final Driver driver =  new Driver();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);		
		
		final EditText edt_name = (EditText) findViewById(R.id.username);
		final EditText edt_pass = (EditText) findViewById(R.id.password); 
		Button b = (Button) findViewById(R.id.login); 
	
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
							
					int  login_result = driver.login(edt_name.getText().toString(), edt_pass.getText().toString());
					if(login_result != 0 ){
						driver.setDriver_id(login_result);						
						driver.setTaxi();											
						File Info =new File (Environment.getExternalStorageDirectory().getPath()+"/info_taxi.txt");
						try {
							Info.createNewFile();
						} catch (  IOException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						FileWriter writer;
						try {
							writer=new  FileWriter(Info);
							writer.write(login_result+"\n");
							writer.flush();
							writer.close();
						} catch (IOException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						Intent in = new Intent(getApplicationContext(), Act3.class);
						startActivity(in); 
						 
						 /*Intent intent = new Intent(getApplicationContext(), Update_location.class);						 
						 PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
						 AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE); 
						 alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (10 * 1000), pendingIntent);*/
						 										 						 
					}
					else {
						Toast.makeText(getApplicationContext(), "Login falid", Toast.LENGTH_SHORT).show(); 
					}
				
				
				
			}
		}); 
		
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
	}

}
