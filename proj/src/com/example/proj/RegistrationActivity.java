package com.example.proj;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class RegistrationActivity extends Activity {
private static final int REQUEST_CODE = 3034;
EditText textUserName;
EditText textPassword;
EditText textFirstName;
EditText textLastName;
EditText PhoneNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		textUserName=(EditText) findViewById(R.id.UserNameText);
		textPassword=(EditText) findViewById(R.id.PasswordText);
        textFirstName=(EditText) findViewById(R.id.DistanceText);
        textLastName=(EditText) findViewById(R.id.LastNameText);
        PhoneNum=(EditText) findViewById(R.id.PhoneNumText);
     RegistrationButton();
	}
	private void RegistrationButton() {
		Button Regisbutton = (Button) findViewById(R.id.RegisButton);
		
		Regisbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
			if(textUserName.getText().toString().length()!=0&& textPassword.getText().toString().length()!=0 &&textFirstName.getText().toString().length()!=0 && textLastName.getText().toString().length()!=0 &&PhoneNum.getText().toString().length()!=0)
			{
				URL url;
				
				
				try {
					boolean checksucsses=true;
					url = new URL("http://localhost/login.php?UserName="+textUserName.getText().toString()+"&Passward="+ textPassword.getText().toString());
					//url = new URL("http://192.168.1.12/login.php?UserName="+textUserName.getText().toString()+"&Passward="+ textPassword.getText().toString());
					HTTPConnHThread thread= new HTTPConnHThread("checkExist");	
					thread.setUrl(url);
					thread.start();
					 
					
					while(thread.isAlive())
					{}
					checksucsses=thread.check;
					if(!checksucsses)
					{
					
						try {
							url = new URL("http://10.0.0.13/registrate.php?UserName="+textUserName.getText().toString()+"&Password="+textPassword.getText().toString()+"&FirstName="+textFirstName.getText().toString()+"&LastName="+textLastName.getText().toString()+"&Phone="+PhoneNum.getText().toString());
							//url = new URL("http://192.168.1.12/registrate.php?UserName="+textUserName.getText().toString()+"&Password="+textPassword.getText().toString()+"&FirstName="+textFirstName.getText().toString()+"&LastName="+textUserName.getText().toString());
							HTTPConnHThread threadRegis= new HTTPConnHThread("registrate");	
							threadRegis.setUrl(url);
							threadRegis.start();	
							while(threadRegis.isAlive())
							{
							}
							if(threadRegis.check)
							{
								Intent data=new Intent();
								
								data.putExtra("user",textUserName.getText().toString());
								data.putExtra("info", "OK"+" "+textFirstName.getText().toString()+" "+textLastName.getText().toString()+" "+PhoneNum.getText().toString()+" "+"phone car tv 1");
								setResult(RESULT_OK, data);	
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
			else{
			Toast.makeText(RegistrationActivity.this, "missing information", Toast.LENGTH_LONG).show();
			
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