package en.edu.qdu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class MycomboxBox  extends AbstractListModel implements ComboBoxModel{
	private String selecteditem=null;
	private  List<String> data;	
	//	�б�����������
	{
		 data=new ArrayList<String>();	
	}
	
	
	//��ʼ��data ����
	public  void  initialize(List<String> datas){
		for (int i = 0; i < datas.size(); i++) {
			data.add(i, datas.get(i));
		}
	}
	//������������ֵ
	public Object getElementAt(int index) {
		return data.get(index);
	}
	public int getSize() {
		return data.size();
	}
	//������������Ŀ
	public Object getSelectedItem() {
		return selecteditem;
	}
	//���������б�����Ŀ
	public void setSelectedItem(Object item) {
		selecteditem=(String)item;
	}
	public String getSelectedItem1(){
		String name=null;
		name=(String)getSelectedItem();
		return name;
	}

}


