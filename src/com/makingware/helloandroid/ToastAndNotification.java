package com.makingware.helloandroid;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class ToastAndNotification extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toast_notification);
		
		final Context context = this;
		final int NOTIFICATION_ID = R.layout.toast_notification;
		
		Button btn_toast = (Button)findViewById(R.id.btn_toast);
		btn_toast.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				View view = View.inflate(context, R.layout.mytoast, null);
				Toast toast = new Toast(context);
				toast.setView(view);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.setDuration(3000);
				toast.show();
			}
		});
		
		Button btn_notification = (Button)findViewById(R.id.btn_notification);
		btn_notification.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//创建 NotificationManager，其中创建的 nm 对象负责“发出”与“取消”  Notification。
				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				
				//参数1：显示图片的id  参数2：显示文字    参数3：Notification显示时间，一般是立即显示(System.currentTimeMillis())
				Notification notification = new Notification(R.drawable.icon, "我的状态栏信息", System.currentTimeMillis());
				
				String contentTitle = "天气预报";
				String contentText = "晴空万里";
				
				Intent intent = new Intent(context, HelloActivity.class);
				//注意，如果要以该Intent启动一个Activity，一定要设置 Intent.FLAG_ACTIVITY_NEW_TASK 标记。
				//Intent.FLAG_ACTIVITY_CLEAR_TOP ：默认情况下是把此Activity先finish掉然后再new一个新的。也有一种情况是执行此Activity的onNewIntent方法，前提是single_top!
				//Intent.FLAG_ACTIVITY_NEW_TASK ：系统会检查当前所有已创建的Task中是否有该要启动的Activity的Task，若有，则在该Task上创建Activity，若没有则新建具有该Activity属性的Task，并在该新建的Task上创建Activity。
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK); 
				
				//PendingIntent.FLAG_UPDATE_CURRENT  表示如果该描述的PendingIntent已存在，则改变已存在的PendingIntent的Extra数据为新的PendingIntent的Extra数据
				PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
				
				/**  改变 Notification 在“正在运行的”栏目下面的布局      **/
				//创建 RemoteViews 并赋给 Notification.contentView ，再把 PendingIntent 赋给 Notification.contentIntent 便可以了
				//注意，如果使用了contentView，那么便不要使用Notification.setLatestEventInfo。
				//如果setLatestEventInfo在赋给 Notification.contentView 的代码之后，那么contentView的效果将被覆盖，显示的便是 setLatestEventInfo 的效果；
				//如果 setLatestEventInfo 在 Notification.contentView 的代码之前，那么显示的便是 Notification.contentView 的效果，
				//也就是说不管你想要setLatestEventInfo 或 contentView 的自定义效果，请保证始终只有一句设置代码，
				//因为在最后一句绑定的时候，之前的设置contentView或setLatestEventInfo的代码都是完全没有必要的。
//				RemoteViews rv = new RemoteViews(getPackageName(), R.layout.notification_view);
//				rv.setImageViewResource(R.id.image, R.drawable.chat);
//				rv.setTextViewText(R.id.text, "Hello,there,I'm john.");
//				notification.contentView = rv;
//				notification.contentIntent = contentIntent;
				
				//FLAG_AUTO_CANCEL:表示当用户点击 Clear之后，能够清除该通知。         FLAG_ONGOING_EVENT:设置常驻通知图标，点击此条通知发出的Intent
				notification.flags = Notification.FLAG_AUTO_CANCEL;
				
				//DEFAULT_ALL：振动和音乐     DEFAULT_LIGHTS：闪光灯      DEFAULT_SOUND：音乐     DEFAULT_VIBRATE：振动
				notification.defaults = Notification.DEFAULT_ALL;
				
				//100ms延迟后，振动250ms，停止100ms后振动500ms
				notification.vibrate = new long[]{100, 250, 100, 500};
				
				//NOTIFICATION_ID 程序中标识Notification的id值（用来区分同一程序中的不同Notifycation，如果程序中只有一个Notification那么这里随便你填什么都可以，不过类型必须要为int）
				notificationManager.notify(NOTIFICATION_ID, notification);
			}
		});
		//简要说一下 Intent 与 PendingIntent 的区别：
		//Intent ：意图，即告诉系统我要干什么，然后系统根据这个Intent做对应的事。如startActivity相当于发送消息，而Intent是消息的内容。
		//PendingIntent ：包装Intent，Intent 是我们直接使用 startActivity ， startService 或 sendBroadcast 启动某项工作的意图。
		//而某些时候，我们并不能直接调用startActivity ， startServide 或 sendBroadcast ，而是当程序或系统达到某一条件才发送Intent。
		//如这里的Notification，当用户点击Notification之后，由系统发出一条Activity 的 Intent 。
		//因此如果我们不用某种方法来告诉系统的话，系统是不知道是使用 startActivity ，startService 还是 sendBroadcast 来启动Intent 的（当然还有其他的“描述”），因此这里便需要PendingIntent。
	}
}
