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
    //　重写　onCreateContextMenu　用以创建上下文菜单
     @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        // 创建 R.id.txt1  的上下文菜单
        if (v == (TextView) this.findViewById(R.id.txt1)) {
            menu.setHeaderIcon(R.drawable.icon);
            menu.setHeaderTitle("这是一段测试文字");
            //menu.clearHeader();
            //　第一个参数：组ID
            //　第二个参数：菜单项ID
            //　第三个参数：顺序号
            //　第四个参数：菜单项上显示的内容
            menu.add(1,0,0,"菜单1");
            menu.add(1,1,1,"菜单2").setCheckable(true); //　增加一个√选项
            
        }
        // 创建 R.id.txt2 的上下文菜单（多级）
        else if(v == (TextView) this.findViewById(R.id.txt2)){
            
        // ContextMenu.addSubMenu("菜单名称")　-　用来添加子菜单。子菜单其实就是一个特殊的菜单
            SubMenu sub1 = menu.addSubMenu("父菜单1");
            sub1.setHeaderIcon(R.drawable.icon);
            sub1.add(0, 0, 0, "菜单1");
            sub1.add(0, 1, 1, "菜单2");
            sub1.setGroupCheckable(1, true, true);
            SubMenu sub2 = menu.addSubMenu("父菜单2");
            sub2.setIcon(R.drawable.icon);
            sub2.add(1, 0, 0, "菜单3");
            sub2.add(1, 1, 1, "菜单4");
            sub2.setGroupCheckable(1, true, true);
        }
    }
         
     //响应上下文菜单
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
