package com.example.driverapp;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.Marker;

import android.R.bool;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog implements
android.view.View.OnClickListener {

public Activity c;
public Dialog d;
public Button yes, no;
public TextView numberofpass;
public boolean result =false ; 

public CustomDialog(Activity a , int n ) {
super(a);
// TODO Auto-generated constructor stub
this.c = a;
}

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.dialog);
d.show();
yes = (Button) findViewById(R.id.btn_yes);
no = (Button) findViewById(R.id.btn_no);
numberofpass=(TextView) findViewById(R.id.Numofpass);
//numberofpass.append(n);
yes.setOnClickListener(this);
no.setOnClickListener(this);

}

@Override
public void onClick(View v) {
switch (v.getId()) {
case R.id.btn_yes:
  result =true ; 
  break;
case R.id.btn_no:
  dismiss();
  break;
default:
  break;
}
dismiss();
}}
