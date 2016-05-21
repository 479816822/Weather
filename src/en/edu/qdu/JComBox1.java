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
	
	JTextField jt=new JTextField();//�ı��� ---������
	public JComBox1(List<String> province){
		JFrame jf=new JFrame();//������
		Container contain=jf.getContentPane();//���彨������
		JLabel jl=new JLabel("Province:");//��ǩ��ʾ
		JComboBox<String> jcb=new JComboBox<String>();//�����б��
		//�������б�����������
		for (int i = 0; i < province.size(); i++) {
			jcb.addItem(province.get(i));
		}
		//���ô��弰�����Сλ��
		jf.setSize(200, 200);
		jl.setBounds(15, 30, 13, 20);
		jt.setBounds(30, 40, 50, 20);
		jcb.setBounds(30, 30, 50, 10);
		
		jcb.setEditable(true);//�б��ɱ༭
		//��굥�������¼�
		jcb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					String str=(String)jcb.getSelectedItem();
					jt.setText(str);//������
					List<String> city=new ArrayList<String>();//������
					//ͨ��ʡ������Data��ķ�����ȡ������Ϣ
					Data Data=new Data();
					Data.getCitys(str);
					//������
					city=Data.getAllCity();
					System.out.println(city);
				}			
			}
		});
		contain.setBackground(Color.blue);//���ô��屳����ɫ
		contain.setLayout(new FlowLayout());//���ô��岼�ָ�ʽ
		//�����Ӧ���
		contain.add(jl);
		contain.add(jcb);
		contain.add(jt);
		//���ô���Ϊ����
		contain.setVisible(true);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);//����رշ�ʽ
	}
}
