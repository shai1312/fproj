package com.example.proj;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.HandlerThread;
import android.util.Log;

public class HTTPConnHThread extends HandlerThread {

	private HttpURLConnection urlConnection;
	private String lat, lng;
	private String name;
    private URL url;
    private String data="";
    public boolean check=false;
	public HTTPConnHThread(String name) {
		super(name);
		this.name = name;
	}

	@Override
	public void run() {
		httpConnSendData();
	}

	/**
	 * Creates the HTTP connection to send data.
	 */
	private void httpConnSendData() {
		try {
			
			urlConnection = (HttpURLConnection) url.openConnection();
			try {
				InputStream in = new BufferedInputStream(urlConnection.getInputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String result = br.readLine();
				if(result.equals("<br />"))
				{
					result=br.readLine();
				}
				if (result.startsWith("OK")) { // Something is wrong
					Log.i(name, "OK!");
					check=true;
					data=result;

				}
				else { // Data sent
					Log.i(name, "Not OK!");
					data=result;
					
				}
			}
			catch (IOException e) {
				Log.i(name, "IOExceptionBitch");
				urlConnection.disconnect();
			}
			urlConnection.disconnect();
		}
		catch (MalformedURLException e) {
			Log.i(name, "MalformedURLException");
		}
		catch (IOException e) {
			Log.i(name, "IOException1");
		}
		
	}

	// Getters and Setters

	
  public void setUrl(URL url){
	  this.url=url;
  
  }
  public URL getUrl()
  {
	  return this.url;
  }
  public boolean getcheck()
  {
	  return this.check;
  }
  public String getdata()
  {
	  return this.data;
  }
}