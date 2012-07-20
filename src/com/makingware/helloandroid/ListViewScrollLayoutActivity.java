package com.makingware.helloandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewScrollLayoutActivity extends Activity{
	private ListView listView01;
	private ListView listView02;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewscrolllayout);
		
		listView01 = (ListView)findViewById(R.id.listview01);         
		listView01.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getData())); 
		
		listView02 = (ListView)findViewById(R.id.listview02);         
		listView02.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getData2())); 
	}
	private List<String> getData(){                   
		List<String> data = new ArrayList<String>();         
		data.add("��������1");         
		data.add("��������2");         
		data.add("��������3");         
		data.add("��������4");
		data.add("��������5");
		data.add("��������6");
		data.add("��������7");
		data.add("��������8");
		data.add("��������9");
		data.add("��������10");
		data.add("��������11");
		data.add("��������12");
		return data;     
	} 
	private List<String> getData2(){                   
		List<String> data = new ArrayList<String>();         
		data.add("��������1111111111");         
		data.add("��������2222222222");         
		data.add("��������3333333333");         
		data.add("��������444444444");
		data.add("��������55555555");
		data.add("��������6666666666");
		data.add("��������77777");
		data.add("��������888888888");
		data.add("��������99999");
		data.add("��������10000000");
		data.add("��������111111111");
		data.add("��������12222222222");
		return data;     
	} 
}
