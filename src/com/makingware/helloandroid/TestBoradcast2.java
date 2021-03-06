package com.makingware.helloandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TestBoradcast2 extends BroadcastReceiver {

	public final static String ACTION_NAME = "com.makingware.boradcast.test2"; 
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction(); 
        if(ACTION_NAME.equals(action)){ 
        	int count = intent.getIntExtra("count", 0);
            Toast.makeText(context, "Manifest.xml注册广播接收到的count："+count, 3000).show(); 
        } 
	}

}
