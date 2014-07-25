package com.example.driverapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Messege 
{
   String source_text;
   private String dest_text;
   private String language;
   public void sendmessege (int idsource, int idtarget, int tripid )
   {
	   
	   String path="http://taxiapp.prana-co.com/insertmessege.php?idsrc="+idsource+"&idtarget="
			   +idtarget+"&tripid="+tripid+"&text="+getDest_text()+"&lan="+getLanguage();
	   
	   Connection.Run(path);
   }
   public String[] checkmessege (int id )
  {	   
	   JSONObject json = null;
	   String [] result=new String[12];
	   String path="http://taxiapp.prana-co.com/checkmessege.php?id="+id;
	   String str= Connection.Get(path);
	   try {
	       JSONArray jArray = new JSONArray(str);
	       int i=0;
	       while ( i< jArray.length())			        
	       {
		       	json = jArray.getJSONObject(i);
		        result[i]=json.getString("Text");
		       	
		       	i++;       	      
	       }
	   }
	   catch (JSONException e) {
		   e.printStackTrace();
	   }            
	   path="http://taxiapp.prana-co.com/delemessege.php?id="+id;
	   Connection.Run(path);
       return result;          	  
   }
public String getDest_text() {
	return dest_text;
}
public void setDest_text(String dest_text) {
	this.dest_text = dest_text;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
}
