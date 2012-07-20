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
		//得到ContentResolver对象
		ContentResolver cr = getContentResolver();
		//取得电话本中开始一项的光标
		Cursor cursor = cr.query(Contacts.CONTENT_URI, null, null, null, null);
		while(cursor.moveToNext()){
			// 取得联系人名字
			int nameFieldColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
			String name = cursor.getString(nameFieldColumnIndex);
			str +=  name;
			
			// 取得联系人ID
			String contactId = cursor.getString(cursor.getColumnIndex(Contacts._ID));
			Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
					+ contactId, null, null);

			// 取得电话号码(可能存在多个号码)
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