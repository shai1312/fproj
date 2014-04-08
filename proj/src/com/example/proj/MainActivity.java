package com.example.proj;




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
   
	private static final int REQUEST_CODE = 3034;
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
				
				
			}
		});
		
	}
	public void login()
	{
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivityForResult(intent, REQUEST_CODE);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case REQUEST_CODE:
				if(resultCode == RESULT_OK) {
					textLat=(TextView) findViewById(R.id.textLat);
					textLong=(TextView) findViewById(R.id.textLong);
					LocationManager lm=(LocationManager) getSystemService (Context.LOCATION_SERVICE);
					LocationListener ll= new mylocationlistener();
					lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, ll);
					
				}
				break;
		}
	}
		class mylocationlistener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			if(location !=null)
			{
			
        	   double pLong= location.getLongitude();
        	   double pLat = location.getLatitude();
             
        	   textLat.setText(Double.toString(pLat));
               textLong.setText(Double.toString(pLong));
       		HTTPConnHThread thread = new HTTPConnHThread("SendGPS");
			thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
		
              thread.start();   
	           
			
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
