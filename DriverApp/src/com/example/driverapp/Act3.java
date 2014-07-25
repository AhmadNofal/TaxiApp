 package com.example.driverapp;

 import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle.Control;


import android.R.integer;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiAutomation.OnAccessibilityEventListener;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


 import com.example.driverapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


 public  class Act3 extends Activity implements LocationListener,OnMarkerClickListener ,OnInitListener{
 	
	 protected static final int RESULT_SPEECH = 1;
	   private TextToSpeech tts;
	   int Customer_id=0;
	GoogleMap googleMap;
 	static ArrayList<String> lon;
 	static ArrayList<String> lan; 	
 	static int firsttime;
 	int id=0;
 	ArrayList<Order> customer_order =new ArrayList<Order>();
 	ArrayList<Location_app> traffic_load =new ArrayList<Location_app>();
 	//private Marker markers[];
 	ArrayList<Marker> Cust_markers =new ArrayList<Marker>();
 	ArrayList<Marker> traffic_markers =new ArrayList<Marker>();
 	private MarkerOptions custmarker;
 	private MarkerOptions traffic_marker;
 	@Override
 	protected void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.map); 		
 		tts = new TextToSpeech(this, this);
 		googleMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
 		googleMap.setMyLocationEnabled(true);
 		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
 		//markers=new Marker[customer_order.size()]; 		
 		LocationManager manager=(LocationManager)getSystemService(LOCATION_SERVICE);
 		Criteria criteria = new Criteria();
 		 
         // Getting the name of the best provider
         String provider = manager.NETWORK_PROVIDER;
         // Getting Current Location
         final Location location = manager.getLastKnownLocation(provider);         
         if(location!=null){
             onLocationChanged(location);
         }
         manager.requestLocationUpdates(provider, 20000, 0, this);
         Button trafic=(Button) findViewById(R.id.traffic);  
         Button message=(Button) findViewById(R.id.message);      
         ImageButton translate=(ImageButton) findViewById(R.id.translate);  
        googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker arg0) 
			{
				// TODO Auto-generated method stub
				int e=Cust_markers.indexOf(arg0);			
				if((e>=0))
				{
					
					final Order Clicked_order =customer_order.get(e);
					final Dialog dialog = new Dialog(Act3.this);
	                // Include dialog.xml file
	                dialog.setContentView(R.layout.dialog);
	                // Set dialog title
	                dialog.setTitle("Order Dialog");	
	                // set values for custom dialog components - text, image and button
	                TextView text = (TextView) dialog.findViewById(R.id.Numofpass);
	                text.setText("Number of passenger :"+String.valueOf(Clicked_order.getNum_of_passenger()));	              	 
	                dialog.show();	             
	                Button declineButton = (Button) dialog.findViewById(R.id.btn_no);
	                // if decline button is clicked, close the custom dialog
	                declineButton.setOnClickListener(new OnClickListener() 
	                {
	                    @Override
	                    public void onClick(View v) {
	                        // Close dialog
	                        dialog.dismiss();
	                    }
	                 });
	                Button AcceptButton= (Button)dialog.findViewById(R.id.btn_yes);
	                AcceptButton.setOnClickListener(new OnClickListener() 
	                {					
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							googleMap.clear();		
							LatLng l = new LatLng(Clicked_order.getCust_loc().getLatitude(), Clicked_order.getCust_loc().getLongitude());
			    			custmarker =new MarkerOptions().position(l);			    		
			    			custmarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.customer));
			    			Cust_markers.add(googleMap.addMarker(custmarker));
			    		
			    			 l = new LatLng(Clicked_order.getdest_Loc().getLatitude(), Clicked_order.getdest_Loc().getLongitude());
			    			custmarker =new MarkerOptions().position(l);
			    		
			    			Cust_markers.add(googleMap.addMarker(custmarker));
			    			
			    			Customer_id=Clicked_order.getCust_id(); 
			    				Log.d("idcust", String.valueOf(Customer_id));
			    				String path="http://taxiapp.prana-co.com/accept_order.php?Driver_Id="+ReadID.getID()+"&Cust_Id="+Customer_id;
								Connection.Run(path);
								
							dialog.dismiss();
							}
					});
	               
			}
				 return true;
			}});
        message.setOnClickListener(new OnClickListener() {
			
     			@Override
     			public void onClick(View v) {
     				// TODO Auto-generated method stub
     				Messege msg= new Messege();
     				String [] s=msg.checkmessege(ReadID.getID());
     				speakOut(s[0]);
     				
     			}
     		});
 		trafic.setOnClickListener(new OnClickListener() {
 		
 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				int id=ReadID.getID();
 				double latitude=location.getLatitude(); 				
 				double longitude=location.getLongitude();
 				String path="http://taxiapp.prana-co.com/add_traffic_point.php?ID="+id+"&Long="+longitude+"&Late="+latitude;
 				Connection.Run(path);
 			}
 		});
 		
 		translate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
 
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
 
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                  
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
			}
		}); 
 	}
 	
 	@Override
 	public boolean onCreateOptionsMenu(Menu menu) {
 		// TODO Auto-generated method stub
 		MenuInflater inflater= getMenuInflater();
 		inflater.inflate(R.menu.main, menu);
 		return true;
 	}
 	@Override
 	public void onLocationChanged(Location location) {
 		if(firsttime==0)
 		{
 			Toast.makeText(getApplicationContext(),"You are here" , Toast.LENGTH_SHORT).show();
 			firsttime++;
 		}
 		LatLng l1=new LatLng(location.getLatitude(), location.getLongitude());
 		CameraUpdate  camera_update=CameraUpdateFactory.newLatLngZoom(l1,10);
 		Log.d("speed", Double.toString(location.getLatitude()));
 		// TODO Auto-generated method stub
 		googleMap.animateCamera(camera_update);
 		
 		
 	}
 	@Override
 	public void onProviderDisabled(String provider) {
 		// TODO Auto-generated method stub
 		Toast.makeText(getApplicationContext(), "gps is not working", Toast.LENGTH_SHORT).show();
 	}
 	@Override
 	public void onProviderEnabled(String provider) {
 		// TODO Auto-generated method stub
 		
 	}
 	@Override
 	public void onStatusChanged(String provider, int status, Bundle extras) {
 		// TODO Auto-generated method stub
 		
 	}
 	 public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()) {
         case R.id.refresh:
        	 int id=ReadID.getID();
        	
        	 Order ord=new  Order();
           customer_order=ord.getorder(id);
           //markers=new Marker[customer_order.size()];
        
           
    		if(!customer_order.isEmpty()){
    			for(int i=0;i<customer_order.size();i++){
    				LatLng l=new LatLng(customer_order.get(i).getCust_loc().getLatitude(),customer_order.get(i).getCust_loc().getLongitude());
    				custmarker =new MarkerOptions().position(l);
    				custmarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.customer));
    				Cust_markers.add(googleMap.addMarker(custmarker));
    				//new MarkerOptions().position(l)
    				
    			}
    		}
    		
         return true;
         case R.id.trafficload:
        	 id=ReadID.getID();         	
        	 traffic_load=Traffic_load.get_traffic_load(id);        	                  	 
      		if(!traffic_load.isEmpty()){
      			for(int i=0;i<traffic_load.size();i++){
      				LatLng l=new LatLng(traffic_load.get(i).getLatitude(),traffic_load.get(i).getLongitude());
      				traffic_marker =new MarkerOptions().position(l);
    				traffic_marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.traffic));
    				traffic_markers.add(googleMap.addMarker(traffic_marker));
      				
    				
      			}
      			
      		}
      	
         return true;
         default:
         return super.onOptionsItemSelected(item);
         }
     }

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		
		
 		int e= Cust_markers.indexOf(marker.getId());
 		Order Clicked_order =customer_order.get(e);
 		Log.d("clickedorder",String.valueOf(Clicked_order.getCust_id()));
 		Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
 		//CustomDialog cd=new CustomDialog(Act3.this, 1);
 		//cd.show();
		return true;
	
	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	 
	        switch (requestCode) {
	        case RESULT_SPEECH: {
	            if (resultCode == RESULT_OK && null != data) {
	 
	                ArrayList<String> text = data
	                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	               //Transl
	                try {
	                	String result= translate("en","fr", text.get(0));
						Messege m = new Messege() ; 
						m.setDest_text(result);
						m.setLanguage("fr");
						m.sendmessege(ReadID.getID() , Customer_id , 4);
						
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            break;
	        }
	 
	        }
	    }
	 public static String translate(String sl, String tl, String text) throws IOException{
	        // fetch
	    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	    	StrictMode.setThreadPolicy(policy); 
	    	
	        URL url = new URL("http://translate.google.com/translate_a/t?client=t&text="+text+"&hl=en&sl=en&tl=fr&multires=1&otf=2&pc=1&ssel=0&tsel=0&sc=1");
	        URLConnection urlConnection = url.openConnection();
	        urlConnection.setRequestProperty("User-Agent", "Something Else");
	        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	        String result = br.readLine();
	        br.close();
	        // parse
	        // System.out.println(result);
	        result = result.substring(2, result.indexOf("]]") + 1);
	        StringBuilder sb = new StringBuilder();
	        String[] splits = result.split("(?<!\\\\)\"");
	        for(int i = 1; i < splits.length; i += 8)
	            sb.append(splits[i]);
	        return sb.toString().replace("\\n", "\n").replaceAll("\\\\(.)", "$1");
	    }

		@Override
		public void onInit(int status) {
			// TODO Auto-generated method stub
			if (status == TextToSpeech.SUCCESS) {
			
				Locale x[]=Locale.getAvailableLocales();
				for(int i=0;i<x.length;i++)
				{
					//Log.d("language",x[i].toString());
				}
	            int result = tts.setLanguage(new Locale("bg"));
	            int x1=tts.isLanguageAvailable(Locale.FRANCE);
	            Log.d("language",String.valueOf(x1));
	         
	        } else {
	            Log.e("TTS", "Initilization Failed!");
	        }
	 
	    }
	    private void speakOut(String text) {
	 
	      
	 
	        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	    }
 
 	/*@Override
 	
 	public boolean onMarkerClick(Marker marker) {
 		// TODO Auto-generated method stub
 		LatLng l=marker.getPosition(); 	
 		return false;
 	}*/

 }