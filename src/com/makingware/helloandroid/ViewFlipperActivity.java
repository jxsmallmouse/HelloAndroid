GIF89a  �     A�;J�E^�cr����ϕ
җ֝ޥ��$��&��E��a�喝���뚚笫��܃�����������������������                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                !�  � ,       � �	H�����(��A�08X�C�>\x�@��� +T�����D�Y@��?�,@�B% (@@�	pȉ`� 	>0h���F�D�г�ЧE@��u� �Q	@@�t<u� ��}���ѷp�M�1�߿ ;                                                                          ) {
                //��layout�ж�������ԣ�Ҳ�����ڴ�����ָ��
//             mViewFlipper.setInAnimation(getApplicationContext(), R.anim.push_left_in);
//             mViewFlipper.setOutAnimation(getApplicationContext(), R.anim.push_left_out);
//             mViewFlipper.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
//             mViewFlipper.setFlipInterval(1000);
                mViewFlipper.showNext();
                //��������ĺ�������ѭ����ʾmViewFlipper�ڵ�����View��
//             mViewFlipper.startFlipping();
        }
        });
 
        Button buttonNext2 = (Button) findViewById(R.id.Button_next2);
        buttonNext2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mViewFlipper.showNext();
        }
 
        });   
        Button buttonNext3 = (Button) findViewById(R.id.Button_next3);
        buttonNext3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mViewFlipper.showNext();
        }
 
        });
 
        mGestureDetector = new GestureDetector(this);

    }
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        //Log.d(tag, "...onFling...");
        if(e1.getX() > e2.getX()) {//move to left
            mViewFlipper.showNext();
        }else if(e1.getX() < e2.getX()) {
            mViewFlipper.setInAnimation(getApplicationContext(), R.anim.push_left_in);//R.anim.push_right_in
            mViewFlipper.setOutAnimation(getApplicationContext(), R.anim.push_left_out);//R.anim.push_right_out
            mViewFlipper.showPrevious();
            mViewFlipper.setInAnimation(getApplicationContext(), R.anim.push_left_in);
            mViewFlipper.setOutAnimation(getApplicationContext(), R.anim.push_left_out);
        }else {
            return false;
        }
        return true;
     }
    public boolean onDoubleTap(MotionEvent e) {
        //Log.d(tag, "...onDoubleTap...");
        if(mViewFlipper.isFlipping()) {
            mViewFlipper.stopFlipping();
        }else {
            mViewFlipper.startFlipping();
        }
        return true;
     }
	public boolean onDoubleTapEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onSingleTapConfirmed(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
    
}