package com.example.proj;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class EditProfile extends Activity {
	private static final int REQUEST_CODE = 3035;
	private Boolean buy=true,sale=false;
	private ExpandableListView catagory;
	private RadioGroup choose;
	private ToggleButton carb,tvb,phoneb,cars,tvs,phones;
	private Button finish;
   
	private String car,tv,phone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		//catagory=(ExpandableListView) findViewById(R.id.catagory);
        choose=(RadioGroup) findViewById(R.id.buy_sale_select);
        carb = (ToggleButton) findViewById(R.id.toggleCarB);
        cars = (ToggleButton) findViewById(R.id.ToggleCarS);
        tvb=(ToggleButton) findViewById(R.id.toggleTvB);
        tvs=(ToggleButton) findViewById(R.id.ToggleTvS);
        phoneb=(ToggleButton) findViewById(R.id.togglePhoneB);
        phones=(ToggleButton) findViewById(R.id.TogglePhones);
        finish=(Button) findViewById(R.id.finish); 
       tv=getIntent().getStringExtra("tv");
       car=getIntent().getStringExtra("car");
       phone=getIntent().getStringExtra("phone");

      
      
       if(tv.equals("btv"))
       {
    	tvb.setChecked(true);
    	
       }
       else if(tv.equals("stv"))
       {
    	  tvs.setChecked(true); 
       }
       if(car.equals("bcar"))
       {
    	 carb.setChecked(true);
       }
       else if(car.equals("scar"))
       {
    	  cars.setChecked(true); 
       }
       if(phone.equals("bphone"))
       {
    	 phoneb.setChecked(true);
       }
       else if(phone.equals("sphone"))
       {
    	  phones.setChecked(true); 
       }
       
       finish.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View arg0) {
 				Intent data=new Intent();
 				if(carb.isChecked())
 				{
 					data.putExtra("car", "bcar");
 				}
 				else if(cars.isChecked())
 				{
 					data.putExtra("car", "scar");
 				}
 				else
 				{
 					data.putExtra("car", "car");
 				}
 				
 				//=====================================
 				if(tvb.isChecked())
 				{
 					data.putExtra("tv", "btv");
 				}
 				else if(tvs.isChecked())
 				{
 					data.putExtra("tv","stv");
 				}
 				else
 				{
 					data.putExtra("tv","tv");
 				}
 				
 				//=====================================
 				if(phoneb.isChecked())
 				{
 					data.putExtra("phone", "bphone");
 				}
 				else if(phones.isChecked())
 				{
 					data.putExtra("phone","sphone");
 				}
 				else
 				{
 					data.putExtra("phone","phone");
 				}
				setResult(RESULT_OK, data);	
				
				finish();
 			}
         });
         }

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}

}
