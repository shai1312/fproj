package com.example.proj;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditProfile extends Activity {
	private static final int REQUEST_CODE = 3035;
	private Boolean buy=true,sale=false;
	private ExpandableListView catagory;
	private RadioGroup choose;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		catagory=(ExpandableListView) findViewById(R.id.catagory);
        choose=(RadioGroup) findViewById(R.id.buy_sale_select);
       
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}

}
