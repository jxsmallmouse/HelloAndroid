package com.makingware.helloandroid;

import com.makingware.tools.CrashHandler;

import android.app.Activity;
import android.os.Bundle;

/** 
 * 捕获全局异常
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-8-24
 */
public class CrashHandlerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        CrashHandler crashHandler = CrashHandler.getInstance();  
        crashHandler.init(this);  //传入参数必须为Activity，否则AlertDialog将不显示。
        // 创建错误
        throw new NullPointerException();
    }
}
