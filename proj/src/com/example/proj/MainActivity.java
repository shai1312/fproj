package com.example.proj;




import java.net.URL;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

import android.location.Criteria;
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
   TextView result,Accuracy;
   Double distance;
   double pLong;
   double pLat;
   private String car,phone,tv;
   private String username,Fname,Lname,PhoneNum;
	private static final int REQUEST_CODE_LOGIN = 3034;
	private static final int REQUEST_CODE_EDIT=3035;
	private Button EdProfile;
	private TextView DistanceText;
	
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
				intent.putExtra("distance", distance);
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
					Accuracy=(TextView) findViewById(R.id.Accuracy);
                     
					lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5,10, ll);
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
				  	this.distance=Double.valueOf(st.nextToken());
					this.result=(TextView) findViewById(R.id.result);
					this.DistanceText=(TextView) findViewById(R.id.DistnaceLimit);
					 DistanceText.setText("current distnance limit: "+Double.toString(distance));
						boolean isgpsenabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
						if(isgpsenabled)
						{
							 Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							 if (location != null) {
					                ll.onLocationChanged(location);
					                
					        }
							 else {
					                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

					                if (location != null) {
					                	ll.onLocationChanged(location);
					                }
					                else
					                {
					             	//location.setLatitude(34);
				                	//location.setLongitude(32);
					                //ll.onLocationChanged(location);
					                	
					                }
									Criteria hdCrit = new Criteria();
									 
									hdCrit.setAccuracy(Criteria.ACCURACY_COARSE);

						}
				}
				}
				break;
			case REQUEST_CODE_EDIT:
				if(resultCode == RESULT_OK) {

				car=data.getStringExtra("car");
				tv=data.getStringExtra("tv");
				phone=data.getStringExtra("phone");
				this.distance=Double.valueOf(data.getStringExtra("distance"));
				 DistanceText.setText("current distnance limit: "+Double.toString(distance));
				break;
				}
		}
	}
		class mylocationlistener implements LocationListener{

			
			
			
		@Override
		public void onLocationChanged(Location location) {
			if(location !=null)
			{
				double Accuracy;
	        	   DecimalFormat df = new DecimalFormat("#.####################");
        	    pLong= location.getLongitude();
        	    pLat = location.getLatitude();
        	    Accuracy=location.getAccuracy()/1000;
               MainActivity.this.Accuracy.setText(Double.toString(Accuracy));
   
        	 //  pLong=Double.valueOf(df.format(pLong));
        	  // pLat=Double.valueOf(df.format(pLat));
        	    double R = 6371;  // earth radius in km

        	    double longitudeD =Math.toDegrees(distance/R/Math.cos(Math.toRadians(pLat)));
        	   double latitudeD = Math.toDegrees(distance/R);
              double maxpLong=pLong+distance*0.000009,minpLong=pLong-distance*0.000009,maxpLat=pLat+distance*0.000009,minpLat=pLat-distance*0.000009;
        	 //  double maxpLong=pLong+longitudeD,minpLong=pLong-longitudeD,maxpLat=pLat+latitudeD,minpLat=pLat-latitudeD;
               
               textLat.setText(Double.toString(pLat));
               textLong.setText(Double.toString(pLong));
               DistanceText.setText(Double.toString(distance));
               URL url;
				try {
				//  url = new URL("http://10.0.0.13/insert.php?lat="+Double.toString(pLat)+"&lon="+Double.toString(pLong)+"&id="+MainActivity.this.username+"&car="+car+"&tv="+tv+"&phone="+phone+"&maxpLat="+Double.toString(maxpLat)+"&minpLat="+Double.toString(minpLat)+"&maxpLong="+Double.toString(maxpLong)+"&minPlong="+Double.toString(minpLong));
					url = new URL("http://10.0.0.13/insert.php?lat="+Double.toString(pLat)+"&lon="+Double.toString(pLong)+"&id="+MainActivity.this.username+"&car="+car+"&tv="+tv+"&phone="+phone+"&maxpLat="+df.format(maxpLat)+"&minpLat="+df.format(minpLat)+"&maxpLong="+df.format(maxpLong)+"&minpLong="+df.format(minpLong)+"&distance="+Double.toString(MainActivity.this.distance));
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
							ack=ack+user+"\0"+phonenum+"\0"+"sell tv"+"\0"+"\n";
						}
						else if(ttv.equals("stv")&&tv.equals("btv"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+"buy tv"+"\0"+"\n";
						}
						if(tcar.equals("bcar")&&car.equals("scar"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+"sell car"+"\n";
						}
						else if(tcar.equals("scar")&&car.equals("bcar"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+"buy car"+"\0"+"\n";
						}
						if(tphone.equals("bphone")&&phone.equals("sphone"))
						{
							ack=ack+user+"\0"+phonenum+" "+"sell phone"+"\0"+"\n";
						}
						else if(tphone.equals("sphone")&&phone.equals("bphone"))
						{
							ack=ack+user+"\0"+phonenum+"\0"+"buy phone"+"\0"+"\n";
						}
						
					}
					
					//Toast.makeText(MainActivity.this,ack, Toast.LENGTH_LONG).show();
					result.setText(ack);
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
