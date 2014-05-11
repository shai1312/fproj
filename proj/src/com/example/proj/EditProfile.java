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
	private ToggleButton car;
	private Button finish;

    private CheckBox bcar,scar,btv,stv,bphone,sphone;
	private String car,tv,phone;
	private Button Done;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		//catagory=(ExpandableListView) findViewById(R.id.catagory);

        choose=(RadioGroup) findViewById(R.id.buy_sale_select);
        car = (ToggleButton) findViewById(R.id.);
        finish=(Button) findViewById(R.id.) 
	}

       tv=getIntent().getStringExtra("tv");
       car=getIntent().getStringExtra("car");
       phone=getIntent().getStringExtra("phone");
       bcar=(CheckBox) findViewById(R.id.BuyCar);
       scar=(CheckBox) findViewById(R.id.SaleCar);
       btv=(CheckBox) findViewById(R.id.BuyTv);
       stv=(CheckBox) findViewById(R.id.SaleTv);
       bphone=(CheckBox) findViewById(R.id.BuyPhone);
       sphone=(CheckBox) findViewById(R.id.SalePhone);
       Done=(Button) findViewById(R.id.Done);
       if(tv=="btv")
       {
    	 btv.setChecked(true);
       }
       else if(tv=="stv")
       {
    	  stv.setChecked(true); 
       }
       if(car=="bcar")
       {
    	 bcar.setChecked(true);
       }
       else if(car=="scar")
       {
    	  scar.setChecked(true); 
       }
       if(phone=="bphone")
       {
    	 bphone.setChecked(true);
       }
       else if(phone=="sphone")
       {
    	  sphone.setChecked(true); 
       }
       
         Done.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View arg0) {
 				Intent data=new Intent();
 				if(bcar.isChecked())
 				{
 					data.putExtra("car", "bcar");
 				}
 				else if(scar.isChecked())
 				{
 					data.putExtra("car", "scar");
 				}
 				else
 				{
 					data.putExtra("car", "car");
 				}
 				
 				//=====================================
 				if(btv.isChecked())
 				{
 					data.putExtra("tv", "btv");
 				}
 				else if(stv.isChecked())
 				{
 					data.putExtra("tv","stv");
 				}
 				else
 				{
 					data.putExtra("tv","tv");
 				}
 				
 				//=====================================
 				if(bphone.isChecked())
 				{
 					data.putExtra("phone", "bphone");
 				}
 				else if(sphone.isChecked())
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

	
	private void ToggleButCar() {
		
		
		ToggleBtCar.on
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}

}
