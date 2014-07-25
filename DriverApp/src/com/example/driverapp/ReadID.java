package com.example.driverapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.os.Environment;

public class ReadID {
  public static int getID()
  {
	  int id = 0;
		File Info=new File(Environment.getExternalStorageDirectory().getPath() + "/info_taxi.txt");
		if(Info.exists())
		{
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
			}}
		return id;
  }
}
