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
		data.add("测试数据1");         
		data.add("测试数据2");         
		data.add("测试数据3");         
		data.add("测试数据4");
		data.add("测试数据5");
		data.add("测试数据6");
		data.add("测试数据7");
		data.add("测试数据8");
		data.add("测试数据9");
		data.add("测试数据10");
		data.add("测试数据11");
		data.add("测试数据12");
		return data;     
	} 
	private List<String> getData2(){                   
		List<String> data = new ArrayList<String>();         
		data.add("测试数据1111111111");         
		data.add("测试数据2222222222");         
		data.add("测试数据3333333333");         
		data.add("测试数据444444444");
		data.add("测试数据55555555");
		data.add("测试数据6666666666");
		data.add("测试数据77777");
		data.add("测试数据888888888");
		data.add("测试数据99999");
		data.add("测试数据10000000");
		data.add("测试数据111111111");
		data.add("测试数据12222222222");
		return data;     
	} 
}
