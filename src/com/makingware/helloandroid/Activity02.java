package com.makingware.helloandroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Activity02 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LayoutParams params= new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(params);
		layout.setBackgroundColor(Color.YELLOW);
		
		setContentView(layout);
	}

}