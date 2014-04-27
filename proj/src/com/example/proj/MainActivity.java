package com.example.proj;




import java.net.URL;
import java.util.StringTokenizer;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Process;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
   TextView textLat;
   TextView textLong;
   private String car,phone,tv;
   private String username,Fname,Lname,PhoneNum;
	private static final int REQUEST_CODE_LOGIN = 3034;
	private static final int REQUEST_CODE_EDIT=3035;
	private Button EdProfile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        login();
	EdProfile=(Button)  findViewById(R.id.EditProfile);
		
		EdProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	setContentView(R.layout.activity_edit_profile);
				Intent intent = new Intent(getApplicationContext(), EditProfile.class);
				intent.putExtra("car", car);
				intent.putExtra("tv", tv);
				intent.putExtra("phone", phone);
				startActivityForResult(intent, REQUEST_CODE_EDIT);     

				
			}
		});
		
	}
	public void login()
	{
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivityForResult(intent, REQUEST_CODE_LOGIN);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case REQUEST_CODE_LOGIN:
				if(resultCode == RESULT_OK) {
					textLat=(TextView) findViewById(R.id.textLat);
					textLong=(TextView) findViewById(R.id.textLong);
					LocationManager lm=(LocationManager) getSystemService (Context.LOCATION_SERVICE);
					LocationListener ll= new mylocationlistener();
					lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, ll);
					this.username=data.getStringExtra("user");
					String info=data.getStringExtra("info");
					StringTokenizer st = new StringTokenizer(info);
					st.nextToken();
					this.Fname=st.nextToken();
					this.Lname=st.nextToken();
					this.PhoneNum=st.nextToken();
					this.phone=st.nextToken();
					this.car=st.nextToken();
					this.tv=st.nextToken();
				  	
					
				}
				break;
			case REQUEST_CODE_EDIT:
				if(resultCode == RESULT_OK) {

				car=data.getStringExtra("car");
				tv=data.getStringExtra("tv");
				phone=data.getStringExtra("phone");
				break;
				}
		}
	}
		class mylocationlistener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			if(location !=null)
			{
			 
        	   double pLong= location.getLongitude();
        	   double pLat = location.getLatitude();
               double maxpLong=pLong+1/*+0.000009*/,minpLong=pLong-1/*-0.000009*/,maxpLat=pLat+1/*+0.000009*/,minpLat=pLat-1/*-0.000009*/;
        	   textLat.setText(Double.toString(pLat));
               textLong.setText(Double.toString(pLong));
               URL url;
				try {
				//  url = new URL("http://10.0.0.13/insert.php?lat="+Double.toString(pLat)+"&lon="+Double.toString(pLong)+"&id="+MainActivity.this.username+"&car="+car+"&tv="+tv+"&phone="+phone+"&maxpLat="+Double.toString(maxpLat)+"&minpLat="+Double.toString(minpLat)+"&maxpLong="+Double.toString(maxpLong)+"&minPlong="+Double.toString(minpLong));
					url = new URL("http://10.0.0.13/insert.php?lat="+Double.toString(pLat)+"&lon="+Double.toString(pLong)+"&id="+MainActivity.this.username+"&car="+car+"&tv="+tv+"&phone="+phone+"&maxpLat="+Double.toString(maxpLat)+"&minpLat="+Double.toString(minpLat)+"&maxpLong="+Double.toString(maxpLong)+"&minpLong="+Double.toString(minpLong));
					HTTPConnHThread thread = new HTTPConnHThread("refresh");
					thread.setUrl(url);
					thread.start();
					while(thread.isAlive())
					{
						
					}
					String data=thread.getdata();
					StringTokenizer st = new StringTokenizer(data);
					String tcar,ttv,tphone;
					tcar=MainActivity.this.car;
					ttv=MainActivity.this.tv;
					tphone=MainActivity.this.phone;
					String user,phonenum,car,tv,phone,ack="";
					st.nextToken();
					while(st.hasMoreTokens())
					{
						user=st.nextToken();
						phonenum=st.nextToken();
						phone=st.nextToken();
						car=st.nextToken();
						tv=st.nextToken();
						if(ttv.equals("btv")&&tv.equals("stv"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+tv+"\0"+"\n";
						}
						else if(ttv.equals("stv")&&tv.equals("btv"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+tv+"\0"+"\n";
						}
						if(tcar.equals("bcar")&&car.equals("scar"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+car+"\n";
						}
						else if(tcar.equals("scar")&&car.equals("bcar"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+car+"\0"+"\n";
						}
						if(ttv.equals("bphone")&&phone.equals("sphone"))
						{
							ack=ack+user+"\0"+phonenum+" "+phone+"\0"+"\n";
						}
						else if(tphone.equals("sphone")&&phone.equals("bphone"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+phone+"\0"+"\n";
						}
						
					}
					
					Toast.makeText(MainActivity.this,ack, Toast.LENGTH_LONG).show();
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			 
	           
			
			}
           
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		  
	  }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
