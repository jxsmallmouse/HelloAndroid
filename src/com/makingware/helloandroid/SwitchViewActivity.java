package com.makingware.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class SwitchViewActivity extends Activity {
	ViewFlipper viewFlipper = null;
	float startX;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper);

		init();
	}

	private void init() {
		viewFlipper = (ViewFlipper) this.findViewById(R.id.myViewFlipper);
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			break;
		case MotionEvent.ACTION_UP:

			if (event.getX() > startX) { // 向右滑动
				viewFlipper.setInAnimation(this, R.anim.in_leftright);
				viewFlipper.setOutAnimation(this, R.anim.out_leftright);
				viewFlipper.showPrevious();
			} else if (event.getX() < startX) { // 向左滑动
				viewFlipper.setInAnimation(this, R.anim.in_rightleft);
				viewFlipper.setOutAnimation(this, R.anim.out_rightleft);
				viewFlipper.showNext();
			}
			break;
		}

		return super.onTouchEvent(event);
	}


}
