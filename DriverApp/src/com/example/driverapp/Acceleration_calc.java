package com.example.driverapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class Acceleration_calc extends Activity {
    LocationManager locManager;
    LocationListener li;
    Location oldloc=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accc);        
        locManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        li=new speed();
        String provider = locManager.NETWORK_PROVIDER;
        oldloc=new Location(provider);
        oldloc.setAltitude(0);
        oldloc.setLongitude(0);
        locManager.requestLocationUpdates(provider, 0, 0, li);
    }
    class speed implements LocationListener{
        @Override
        public void onLocationChanged(Location loc) {
            double thespeed=Math.sqrt(Math.pow(loc.getLongitude()-oldloc.getLongitude(),2)+Math.pow(loc.getLatitude()-oldloc.getLatitude(), 2));
            oldloc=loc;
            Toast.makeText(Acceleration_calc.this,String.valueOf(thespeed), Toast.LENGTH_LONG).show();
        }
        @Override
        public void onProviderDisabled(String arg0) {}
        @Override
        public void onProviderEnabled(String arg0) {}
        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}

    }
}
