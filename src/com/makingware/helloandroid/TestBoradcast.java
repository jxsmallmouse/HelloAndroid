package com.makingware.helloandroid;

import android.app.Activity; 
import android.content.BroadcastReceiver; 
import android.content.Context; 
import android.content.Intent; 
import android.content.IntentFilter; 
import android.os.Bundle; 
import android.view.View; 
import android.view.View.OnClickListener; 
import android.widget.Button; 
import android.widget.LinearLayout; 
import android.widget.TextView;
import android.widget.Toast; 

public class TestBoradcast extends Activity {
	
    public final static String ACTION_NAME = "com.makingware.boradcast.test"; 
    private Button mBtnMsgEvent = null; 
    private Button mBtnOther = null; 
    private TextView mTxtCount;
     
    protected void onCreate(Bundle savedInstanceState){ 
        super.onCreate(savedInstanceState); 
         
        //ע��㲥 
        registerBoradcastReceiver();          

        mBtnMsgEvent = new Button(this); 
        mBtnMsgEvent.setText("���͹㲥"); 
        mBtnOther = new Button(this); 
        mBtnOther.setText("��ת����"); 
        mTxtCount = new TextView(this);
        mTxtCount.setText("0");
        
        LinearLayout mLinearLayout = new LinearLayout(this); 
        mLinearLayout.addView(mBtnMsgEvent); 
        mLinearLayout.addView(mBtnOther); 
        mLinearLayout.addView(mTxtCount);
        setContentView(mLinearLayout); 
         
        mBtnMsgEvent.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) { 
                Intent mIntent = new Intent(ACTION_NAME); 
                mIntent.putExtra("count", 2); 
                 
                //���͹㲥 
                sendBroadcast(mIntent); 
            } 
        }); 
        
        mBtnOther.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) { 
                Intent mIntent = new Intent(TestBoradcast2.ACTION_NAME);
                mIntent.putExtra("count", 123); 

                //���͹㲥 
                sendBroadcast(mIntent); 
            } 
        }); 
    } 
     
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){ 
        @Override 
        public void onReceive(Context context, Intent intent) { 
            String action = intent.getAction(); 
            if(action.equals(ACTION_NAME)){ 
            	int count = intent.getIntExtra("count", 0);
                Toast.makeText(TestBoradcast.this, "��Activityע��㲥���յ���count��"+count, 3000).show(); 
                mTxtCount.setText(count+"");
            } 
        } 
         
    }; 
     
    public void registerBoradcastReceiver(){ 
        IntentFilter myIntentFilter = new IntentFilter(); 
        myIntentFilter.addAction(ACTION_NAME); 
        //ע��㲥       
        registerReceiver(mBroadcastReceiver, myIntentFilter); 
    } 
}
