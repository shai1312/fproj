package com.example.proj;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class RegistrationActivity extends Activity {
EditText textUserName;
EditText textPassword;
EditText textFirstName;
EditText textLastName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		textUserName=(EditText) findViewById(R.id.UserNameText);
		textPassword=(EditText) findViewById(R.id.PasswordText);
		 
	
     RegistrationButton();
	}
	private void RegistrationButton() {
		Button Regisbutton = (Button) findViewById(R.id.RegisButton);
		
		Regisbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(textUserName==null && textPassword==null && textFirstName==null && textLastName==null)
				{
					Toast.makeText(RegistrationActivity.this, "missing information", Toast.LENGTH_LONG).show();
				
				}
				
				else{
				URL url;
				
				
				
				try {
					url = new URL("http://192.168.1.12/login.php?UserName="+textUserName.getText().toString()+"&Passward="+ textPassword.getText().toString());
					HTTPConnHThread thread= new HTTPConnHThread("checkExist");	
					thread.setUrl(url);
					thread.start();
					 
					
					while(thread.isAlive())
					{}
					
					if(!thread.check)
					{
					
						try {
							url = new URL("http://192.168.1.12/registrate.php?UserName="+textUserName.getText().toString()+"&Password="+ textPassword.getText().toString()+"&FirstName="+ textFirstName.getText().toString()+"&LastName="+ textLastName.getText().toString());
							HTTPConnHThread threadRegis= new HTTPConnHThread("registrate");	
							threadRegis.setUrl(url);
							threadRegis.start();	
							while(threadRegis.isAlive())
							{
							}
							if(threadRegis.check)
							{
								
								finish();			
							}
							else
							{
								Toast.makeText(RegistrationActivity.this, "registration didn't succed, try again", Toast.LENGTH_LONG).show();
							}
						}
							catch (MalformedURLException e) {
							
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					
						
						
					}
					else{
						Toast.makeText(RegistrationActivity.this, "registration didn't succed, try again", Toast.LENGTH_LONG).show();
					}
					}
				catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
			}
			}
			});
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}
}