package com.example.proj;

import java.net.MalformedURLException;
import java.net.URL;









import com.example.proj.MainActivity.mylocationlistener;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class LoginActivity extends Activity {
private Button login,register;
private EditText user,password;
private static final int REQUEST_CODE = 3034;
private TextView error;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		error=(TextView) findViewById(R.id.error);
		// Show the Up button in the action bar.
		//setupActionBar();
		
         login=(Button)  findViewById(R.id.loginb);
         register=(Button) findViewById(R.id.registerb);
         user=(EditText)  findViewById(R.id.username);
         password =(EditText)  findViewById(R.id.password);
         register.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				//setContentView(R.layout.activity_registration);
				Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
				startActivityForResult(intent, REQUEST_CODE);     
               
         }
			
         });
         
		login.setOnClickListener(new View.OnClickListener() {
		boolean checksucsses=false;	
			public void onClick(View arg0) {
				URL url;
				try {
					//url = new URL("http://192.168.1.12/login.php?user="+user.getText().toString()+"&pass="+password.getText().toString());
					url = new URL("http://10.0.0.13/login.php?user="+user.getText().toString()+"&pass="+password.getText().toString());
					HTTPConnHThread thread = new HTTPConnHThread("Login");
					thread.setUrl(url);
					thread.start();
			 while(thread.isAlive())
			 {}
					this.checksucsses=thread.check;		
					if(this.checksucsses)
					{
					    String info=thread.getdata();
						Intent data=new Intent();
						
						data.putExtra("user", user.getText().toString());
						data.putExtra("info", info);
						setResult(RESULT_OK, data);	
						
						finish();
					}
					else
					{
						error.setText("wrong user or password");
					}
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
/*	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case REQUEST_CODE:
				if(resultCode == RESULT_OK) {
					setResult(RESULT_OK, data);	
					finish();
					
				}
		}
	}

}
