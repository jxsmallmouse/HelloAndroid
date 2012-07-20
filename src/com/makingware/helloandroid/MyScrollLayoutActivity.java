package com.makingware.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.makingware.helloandroid.MyScrollLayout.OnViewChangeListener;

public class MyScrollLayoutActivity extends Activity implements OnViewChangeListener, OnClickListener{
    /** Called when the activity is first created. */

	private MyScrollLayout mScrollLayout;	
	private ImageView[] mImageViews;	
	private int mViewCount;	
	private int mCurSel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switchview);        
        init();
    }
    
    private void init()
    {
    	mScrollLayout = (MyScrollLayout) findViewById(R.id.MyScrollLayout); 	
    	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llayout);   	
    	mViewCount = mScrollLayout.getChildCount();
    	mImageViews = new ImageView[mViewCount];   	
    	for(int i = 0; i < mViewCount; i++)    	{
    		mImageViews[i] = (ImageView) linearLayout.getChildAt(i);
    		mImageViews[i].setEnabled(true);
    		mImageViews[i].setOnClickListener(this);
    		mImageViews[i].setTag(i);
    	}    	
    	mCurSel = 0;
    	mImageViews[mCurSel].setEnabled(false);    	
    	mScrollLayout.SetOnViewChangeListener(this);
    }

    private void setCurPoint(int index)
    {
    	if (index < 0 || index > mViewCount - 1 || mCurSel == index)    	{
    		return ;
    	}    	
    	mImageViews[mCurSel].setEnabled(true);
    	mImageViews[index].setEnabled(false);    	
    	mCurSel = index;
    }


	public void OnViewChange(int view) {
		// TODO Auto-generated method stub
		setCurPoint(view);
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pos = (Integer)(v.getTag());
		setCurPoint(pos);
		mScrollLayout.snapToScreen(pos);
	}
}