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
				//���� NotificationManager�����д����� nm �����𡰷������롰ȡ����  Notification��
				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				
				//����1����ʾͼƬ��id  ����2����ʾ����    ����3��Notification��ʾʱ�䣬һ����������ʾ(System.currentTimeMillis())
				Notification notification = new Notification(R.drawable.icon, "�ҵ�״̬����Ϣ", System.currentTimeMillis());
				
				String contentTitle = "����Ԥ��";
				String contentText = "�������";
				
				Intent intent = new Intent(context, HelloActivity.class);
				//ע�⣬���Ҫ�Ը�Intent����һ��Activity��һ��Ҫ���� Intent.FLAG_ACTIVITY_NEW_TASK ��ǡ�
				//Intent.FLAG_ACTIVITY_CLEAR_TOP ��Ĭ��������ǰѴ�Activity��finish��Ȼ����newһ���µġ�Ҳ��һ�������ִ�д�Activity��onNewIntent������ǰ����single_top!
				//Intent.FLAG_ACTIVITY_NEW_TASK ��ϵͳ���鵱ǰ�����Ѵ�����Task���Ƿ��и�Ҫ������Activity��Task�����У����ڸ�Task�ϴ���Activity����û�����½����и�Activity���Ե�Task�����ڸ��½���Task�ϴ���Activity��
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK); 
				
				//PendingIntent.FLAG_UPDATE_CURRENT  ��ʾ�����������PendingIntent�Ѵ��ڣ���ı��Ѵ��ڵ�PendingIntent��Extra����Ϊ�µ�PendingIntent��Extra����
				PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
				
				/**  �ı� Notification �ڡ��������еġ���Ŀ����Ĳ���      **/
				//���� RemoteViews ������ Notification.contentView ���ٰ� PendingIntent ���� Notification.contentIntent �������
				//ע�⣬���ʹ����contentView����ô�㲻Ҫʹ��Notification.setLatestEventInfo��
				//���setLatestEventInfo�ڸ��� Notification.contentView �Ĵ���֮����ôcontentView��Ч���������ǣ���ʾ�ı��� setLatestEventInfo ��Ч����
				//��� setLatestEventInfo �� Notification.contentView �Ĵ���֮ǰ����ô��ʾ�ı��� Notification.contentView ��Ч����
				//Ҳ����˵��������ҪsetLatestEventInfo �� contentView ���Զ���Ч�����뱣֤ʼ��ֻ��һ�����ô��룬
				//��Ϊ�����һ��󶨵�ʱ��֮ǰ������contentView��setLatestEventInfo�Ĵ��붼����ȫû�б�Ҫ�ġ�
//				RemoteViews rv = new RemoteViews(getPackageName(), R.layout.notification_view);
//				rv.setImageViewResource(R.id.image, R.drawable.chat);
//				rv.setTextViewText(R.id.text, "Hello,there,I'm john.");
//				notification.contentView = rv;
//				notification.contentIntent = contentIntent;
				
				//FLAG_AUTO_CANCEL:��ʾ���û���� Clear֮���ܹ������֪ͨ��         FLAG_ONGOING_EVENT:���ó�פ֪ͨͼ�꣬�������֪ͨ������Intent
				notification.flags = Notification.FLAG_AUTO_CANCEL;
				
				//DEFAULT_ALL���񶯺�����     DEFAULT_LIGHTS�������      DEFAULT_SOUND������     DEFAULT_VIBRATE����
				notification.defaults = Notification.DEFAULT_ALL;
				
				//100ms�ӳٺ���250ms��ֹͣ100ms����500ms
				notification.vibrate = new long[]{100, 250, 100, 500};
				
				//NOTIFICATION_ID �����б�ʶNotification��idֵ����������ͬһ�����еĲ�ͬNotifycation�����������ֻ��һ��Notification��ô�����������ʲô�����ԣ��������ͱ���ҪΪint��
				notificationManager.notify(NOTIFICATION_ID, notification);
			}
		});
		//��Ҫ˵һ�� Intent �� PendingIntent ������
		//Intent ����ͼ��������ϵͳ��Ҫ��ʲô��Ȼ��ϵͳ�������Intent����Ӧ���¡���startActivity�൱�ڷ�����Ϣ����Intent����Ϣ�����ݡ�
		//PendingIntent ����װIntent��Intent ������ֱ��ʹ�� startActivity �� startService �� sendBroadcast ����ĳ�������ͼ��
		//��ĳЩʱ�����ǲ�����ֱ�ӵ���startActivity �� startServide �� sendBroadcast �����ǵ������ϵͳ�ﵽĳһ�����ŷ���Intent��
		//�������Notification�����û����Notification֮����ϵͳ����һ��Activity �� Intent ��
		//���������ǲ���ĳ�ַ���������ϵͳ�Ļ���ϵͳ�ǲ�֪����ʹ�� startActivity ��startService ���� sendBroadcast ������Intent �ģ���Ȼ���������ġ���������������������ҪPendingIntent��
	}
}
