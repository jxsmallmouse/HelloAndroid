package com.makingware.helloandroid;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.PhoneLookup;
import android.widget.TextView;

public class ReadContacts extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		TextView tv = new TextView(this);
		String str = "";
		//�õ�ContentResolver����
		ContentResolver cr = getContentResolver();
		//ȡ�õ绰���п�ʼһ��Ĺ��
		Cursor cursor = cr.query(Contacts.CONTENT_URI, null, null, null, null);
		while(cursor.moveToNext()){
			// ȡ����ϵ������
			int nameFieldColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
			String name = cursor.getString(nameFieldColumnIndex);
			str +=  name;
			
			// ȡ����ϵ��ID
			String contactId = cursor.getString(cursor.getColumnIndex(Contacts._ID));
			Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
					+ contactId, null, null);

			// ȡ�õ绰����(���ܴ��ڶ������)
			while (phone.moveToNext())
			{
				String strPhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				str += (":" + strPhoneNumber);
			}
			str += "\n";
			phone.close();
		}
		cursor.close();
		tv.setText(str);
		
		setContentView(tv);

	}
	
}