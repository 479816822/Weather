package en.edu.qdu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class MycomboxBox  extends AbstractListModel implements ComboBoxModel{
	private String selecteditem=null;
	private  List<String> data;	
	//	列表框的数据内容
	{
		 data=new ArrayList<String>();	
	}
	
	
	//初始化data 数据
	public  void  initialize(List<String> datas){
		for (int i = 0; i < datas.size(); i++) {
			data.add(i, datas.get(i));
		}
	}
	//根据索引返回值
	public Object getElementAt(int index) {
		return data.get(index);
	}
	public int getSize() {
		return data.size();
	}
	//返回下拉框项目
	public Object getSelectedItem() {
		return selecteditem;
	}
	//设置下拉列表框的项目
	public void setSelectedItem(Object item) {
		selecteditem=(String)item;
	}
	public String getSelectedItem1(){
		String name=null;
		name=(String)getSelectedItem();
		return name;
	}

}


