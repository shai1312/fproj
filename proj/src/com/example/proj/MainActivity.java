package com.example.proj;




import java.net.URL;

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

public class MainActivity extends Activity {
   TextView textLat;
   TextView textLong;
   private boolean car,phone,tv;
   private String username;
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
				setContentView(R.layout.activity_edit_profile);
				Intent intent = new Intent(getApplicationContext(), EditProfile.class);
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
				}
				break;
			case REQUEST_CODE_EDIT:
				car=data.getBooleanExtra("car",false);
				tv=data.getBooleanExtra("tv", false);
				phone=data.getBooleanExtra("phone", false);
				break;
			
		}
	}
		class mylocationlistener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			if(location !=null)
			{
			  String car="",tv="",phone="";
			  if(MainActivity.this.car)
			  {
				  car="TRUE";
			  }
			  else
			  {
				  car="FALSE";
			  }
			  if(MainActivity.this.phone)
			  {
				  phone="TRUE";
			  }
			  else
			  {
				  phone="FALSE";
			  }
			  if(MainActivity.this.tv)
			  {
				  tv="TRUE";
			  }
			  else
			  {
				  tv="FALSE";
			  }
        	   double pLong= location.getLongitude();
        	   double pLat = location.getLatitude();
             
        	   textLat.setText(Double.toString(pLat));
               textLong.setText(Double.toString(pLong));
               URL url;
				try {
					url = new URL("http://192.168.1.12/insert.php?lat="+Double.toString(pLat)+"&lon="+Double.toString(pLong)+"&id="+MainActivity.this.username+"&car="+car+"&tv="+tv+"&phone="+phone);
					//url = new URL("http://10.0.0.13/insert.php?lat="+Double.toString(pLat)+"&lon="+Double.toString(pLong)+"&id="+MainActivity.this.username+"&car="+car+"&tv="+tv+"&phone="+phone);
					HTTPConnHThread thread = new HTTPConnHThread("refresh");
					thread.setUrl(url);
					thread.start();
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
