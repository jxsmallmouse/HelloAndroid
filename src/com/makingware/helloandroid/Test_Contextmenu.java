package com.makingware.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;

public class Test_Contextmenu extends Activity {
	
	final int MENU1 = 1;  
	final int MENU2 = 2;  
	final int MENU3 = 3;  
	final int MENU4 = 4;  
	final int MENU5 = 5;  
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_contextmenu);
        
        TextView txt1 = (TextView) this.findViewById(R.id.txt1);
        this.registerForContextMenu(txt1);
        TextView txt2 = (TextView) this.findViewById(R.id.txt2);
        this.registerForContextMenu(txt2);
    }  
    //����д��onCreateContextMenu�����Դ��������Ĳ˵�
     @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        // ���� R.id.txt1  �������Ĳ˵�
        if (v == (TextView) this.findViewById(R.id.txt1)) {
            menu.setHeaderIcon(R.drawable.icon);
            menu.setHeaderTitle("����һ�β�������");
            //menu.clearHeader();
            //����һ����������ID
            //���ڶ����������˵���ID
            //��������������˳���
            //�����ĸ��������˵�������ʾ������
            menu.add(1,0,0,"�˵�1");
            menu.add(1,1,1,"�˵�2").setCheckable(true); //������һ����ѡ��
            
        }
        // ���� R.id.txt2 �������Ĳ˵����༶��
        else if(v == (TextView) this.findViewById(R.id.txt2)){
            
        // ContextMenu.addSubMenu("�˵�����")��-����������Ӳ˵����Ӳ˵���ʵ����һ������Ĳ˵�
            SubMenu sub1 = menu.addSubMenu("���˵�1");
            sub1.setHeaderIcon(R.drawable.icon);
            sub1.add(0, 0, 0, "�˵�1");
            sub1.add(0, 1, 1, "�˵�2");
            sub1.setGroupCheckable(1, true, true);
            SubMenu sub2 = menu.addSubMenu("���˵�2");
            sub2.setIcon(R.drawable.icon);
            sub2.add(1, 0, 0, "�˵�3");
            sub2.add(1, 1, 1, "�˵�4");
            sub2.setGroupCheckable(1, true, true);
        }
    }
         
     //��Ӧ�����Ĳ˵�
     @Override
     public boolean onContextItemSelected(MenuItem item) {
     	switch (item.getItemId()) {
	 		case MENU1:
	 		case MENU2:
	 		case MENU3:
	 			break;
	 		case MENU4:
	 		case MENU5:
	 			break;
 		}
     	return true;
     }

}
