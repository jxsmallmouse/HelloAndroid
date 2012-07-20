package com.makingware.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyEditTextActivity extends Activity {
	/** Called when the activity is first created. */
	Button b;
	MyEditText e;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_edittext);
		
		b = (Button) findViewById(R.id.myButton);
		e = (MyEditText) findViewById(R.id.myEdit);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				e.insertDrawable(R.drawable.tempico);
			}
		});
	}
}