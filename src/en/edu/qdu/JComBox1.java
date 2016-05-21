package en.edu.qdu;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JComBox1 extends JFrame {
	
	JTextField jt=new JTextField();//文本框 ---测试用
	public JComBox1(List<String> province){
		JFrame jf=new JFrame();//主窗体
		Container contain=jf.getContentPane();//窗体建成容器
		JLabel jl=new JLabel("Province:");//标签显示
		JComboBox<String> jcb=new JComboBox<String>();//下拉列表框
		//往下拉列表框中添加数据
		for (int i = 0; i < province.size(); i++) {
			jcb.addItem(province.get(i));
		}
		//设置窗体及组件大小位置
		jf.setSize(200, 200);
		jl.setBounds(15, 30, 13, 20);
		jt.setBounds(30, 40, 50, 20);
		jcb.setBounds(30, 30, 50, 10);
		
		jcb.setEditable(true);//列表框可编辑
		//鼠标单击监听事件
		jcb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					String str=(String)jcb.getSelectedItem();
					jt.setText(str);//测试用
					List<String> city=new ArrayList<String>();//测试用
					//通过省名调用Data类的方法获取城市信息
					Data Data=new Data();
					Data.getCitys(str);
					//测试用
					city=Data.getAllCity();
					System.out.println(city);
				}			
			}
		});
		contain.setBackground(Color.blue);//设置窗体背景颜色
		contain.setLayout(new FlowLayout());//设置窗体布局格式
		//添加相应组件
		contain.add(jl);
		contain.add(jcb);
		contain.add(jt);
		//设置窗体为可视
		contain.setVisible(true);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);//窗体关闭方式
	}
}
