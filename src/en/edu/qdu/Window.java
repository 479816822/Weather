package en.edu.qdu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener {
	private JLabel[] jLabel; // ����һ����ǩ�������
	private JLabel[] jLabe33; // ����һ����ǩ�������
	private JLabel[] jLabe44; // ����һ����ǩ�������
	private JLabel jLabe2; // ����һ����ǩ������ı���
	private JLabel jLabe; // ����һ����ǩ������ʾ��������
	private JLabel jLabe3; // ����һ����ǩ������ʾ������������
	private JLabel jLabe4; // ����һ����ǩ������ʾ������������
	private ImageIcon image; // ��ʱ������ͼƬ
	private ImageIcon image3; // ��ʱ������ͼƬ����
	private ImageIcon image4; // ��ʱ������ͼƬ����
	private ImageIcon image2; // ����
	private Image image1; // �����ͼ��
	private JButton button; // ��ǰ���а�ť
	private static Data data; // ����һ����̬��ȫ�����ݶ����������µ�������
	// ���ϴ洢����֧�ֵ�ʡ��
	private static List<String> allProvinces; // ����һ����̬ȫ�ֵ����ݼ��ϣ���������ʡ�ݵ�������
	// ���ϴ洢��ѡʡ����֧�ֵĳ���
	private List<String> allCity;
	private Data data1;
	// ���ϴ洢������Ϣ
	private List<String> weatherMessage;

	// ʱ�����ʾ��ʽ
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");

	// ��ʼ����
	{
		button = new JButton();
		allProvinces = new ArrayList<String>();
		allCity = new ArrayList<String>();
		weatherMessage = new ArrayList<String>();
		data = new Data();
	}

	// ��ʼ������//,
	public Window(String city) {
		// ���ô��岼�ֹ�����Ϊnull
		this.setLayout(null);
		// ��ʼ�����ݶ��󣬳�������ʱ��ʾĬ�ϳ��б���
		// ����getWeather����ȡ������������Ϣ
		weatherMessage = data.getWeather(city);

		// String imageIndex = this.data.getWeatherMessage().get(28);
		// ��ȡ������������Ϣ
		// weatherMessage = data.getWeatherMessage();
		// ����9����ǩ������ʾ������Ϣ
		jLabel = new JLabel[9];
		jLabe33 = new JLabel[3];// ������ʾδ����������ݵı�ǩ
		jLabe44 = new JLabel[3];// ������ʾδ����������ݵı�ǩ
		// ������ʾͼ��
		jLabe = new JLabel();
		jLabe2 = new JLabel();
		jLabe3 = new JLabel();
		jLabe4 = new JLabel();
		// ����ÿһ����ǩ������
		for (int i = 0; i < 9; i++) {
			jLabel[i] = new JLabel();
		}
		// ����ÿһ����ǩ������
		for (int i = 0; i < 3; i++) {
			jLabe33[i] = new JLabel();
		}
		// ����ÿһ����ǩ������
		for (int i = 0; i < 3; i++) {
			jLabe44[i] = new JLabel();
		}
		// �������Ϣ
		jLabel[1].setText(weatherMessage.get(16));
		jLabel[0].setText(weatherMessage.get(15));
		jLabel[2].setText(weatherMessage.get(17));
		jLabel[3].setText(weatherMessage.get(3)); // ������ʾʱ��
		jLabel[4].setText("����ָ��:");
		jLabel[5].setText(weatherMessage.get(10));
		jLabel[6].setText(weatherMessage.get(11));
		jLabel[7].setText(weatherMessage.get(13));
		jLabel[8].setText(weatherMessage.get(14));
		// ����������Ϣ
		jLabe33[1].setText(weatherMessage.get(20));
		jLabe33[0].setText(weatherMessage.get(21));
		jLabe33[2].setText(weatherMessage.get(22));
		jLabe44[1].setText(weatherMessage.get(25));
		jLabe44[0].setText(weatherMessage.get(26));
		jLabe44[2].setText(weatherMessage.get(27));

		// ���ñ�ǩde��Сλ����ӽ�����
		for (int i = 5; i < 9; i++) {
			jLabel[i].setBounds(20, 550 + (i - 5) * 40, 1000, 50);
			jLabel[i].setFont(new Font("����", Font.BOLD, 20));
		}
		// ���ú������ǩ�Ĵ�Сλ����ӽ�����
		for (int i = 0; i < 3; i++) {
			jLabe33[i].setBounds(650, 255 + i * 50, 200, 50);
			jLabe33[i].setFont(new Font("����", Font.BOLD, 20));
		}
		// ���ú������ǩ�Ĵ�Сλ����ӽ�����
		for (int i = 0; i < 3; i++) {
			jLabe44[i].setBounds(650, 500 + i * 50, 200, 50);
			jLabe44[i].setFont(new Font("����", Font.BOLD, 20));
		}
		jLabel[0].setBounds(60, 140, 280, 50);
		jLabel[1].setBounds(60, 200, 280, 50);
		jLabel[2].setBounds(60, 260, 280, 50);
		jLabel[3].setBounds(60, 310, 350, 50);
		jLabel[4].setBounds(20, 510, 180, 40);
		for (int i = 0; i < 5; i++) {
			jLabel[i].setFont(new Font("����", Font.BOLD, 30));

		}
		// ��ӱ�ǩ������
		for (int i = 0; i < jLabel.length; i++) {
			this.add(jLabel[i]);
		}
		// ��ӱ�ǩ������
		for (int i = 0; i < jLabe33.length; i++) {
			this.add(jLabe33[i]);
		}
		// ��ӱ�ǩ������
		for (int i = 0; i < jLabe44.length; i++) {
			this.add(jLabe44[i]);
		}
		// ��ȡ��ǰ������ͼ���ڼ������±�19��ƥ��洢��ͼ��ĵ�ַ
		// ����ͼ��
		String picture = data.getWeatherMessage().get(19);
		String address = "G:\\a_" + picture;
		image = new ImageIcon(address);
		jLabe.setIcon(image);
		jLabe.setBounds(60, 60, 100, 100);
		this.add(jLabe);
		// ��ȡ��ǰ������ͼ���ڼ������±�19��ƥ��洢��ͼ��ĵ�ַ
		// ����ͼ��
		String picture3 = data.getWeatherMessage().get(23);
		String address3 = "G:\\a_" + picture;
		image3 = new ImageIcon(address3);
		jLabe3.setIcon(image3);
		jLabe3.setBounds(650, 130, 110, 100);
		this.add(jLabe3);
		// ��ȡ��ǰ������ͼ���ڼ������±�19��ƥ��洢��ͼ��ĵ�ַ
		// ����ͼ��
		String picture4 = data.getWeatherMessage().get(28);
		String address4 = "G:\\a_" + picture;
		image4 = new ImageIcon(address4);
		jLabe4.setIcon(image4);
		jLabe4.setBounds(650, 405, 110, 100);
		this.add(jLabe4);
		// ���屳��
		image2 = new ImageIcon("G:\\2.jpg");
		jLabe2.setIcon(image2);
		jLabe2.setBounds(0, 0, 1000, 800);
		this.add(jLabe2);
		// ��ǰ�ĳ��а�ť
		button.setText(city);
		button.setFont(new Font("����", Font.PLAIN, 40));
		button.setBounds(670, 20, 200, 100);
		button.addActionListener(this);
		button.setFocusPainted(false);
		button.setBackground(new Color(215, 205, 200));
		this.add(button);

		// ���ô��壺����Ԥ��
		this.setTitle("����Ԥ��");
		// ���ô���Ĵ�С
		this.setBounds(0, 0, 1000, 800);
		// �����ͼ��
		Image image1 = this.getToolkit().getImage("G:\\1.jpg");
		this.setIconImage(image1);
		// ���岻�ɵ���С
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		;

		// ʱ��ĵ���
		// ����ʱ��仯������
		// Timer timer = new Timer();
		// timer.schedule(new ShowTime(), new Date(), 1000);
	}

	public void actionPerformed(ActionEvent arg0) {
		// ����ִ������Ĺ���
		// ��һ���Ӵ���
		new MyJDialog(Window.this).setVisible(true);
	}

	// ���ݸ���
	public void newShows(String city) {
		// �ֲ���������Ϣ����
		List<String> weatherMessage = new ArrayList<String>();
		// �ֲ������������
		Data data1 = new Data();
		data1 = new Data();
		// ���³��е�����
		weatherMessage = data1.getWeather(city);
		// �õ����º����������
		jLabel[1].setText(weatherMessage.get(16));
		jLabel[0].setText(weatherMessage.get(15));
		jLabel[2].setText(weatherMessage.get(17));
		jLabel[3].setText(weatherMessage.get(3));// ��ʾʱ��
		jLabel[4].setText("����ָ��:");
		jLabel[5].setText(weatherMessage.get(10));
		jLabel[6].setText(weatherMessage.get(11));
		jLabel[7].setText(weatherMessage.get(13));
		jLabel[8].setText(weatherMessage.get(14));

		// ����������Ϣ
		jLabe33[1].setText(weatherMessage.get(20));
		jLabe33[0].setText(weatherMessage.get(21));
		jLabe33[2].setText(weatherMessage.get(22));
		jLabe44[1].setText(weatherMessage.get(25));
		jLabe44[0].setText(weatherMessage.get(26));
		jLabe44[2].setText(weatherMessage.get(27));

		// ������ͼ��
		// this.setBounds(0, 0, 1000, 800);
		// ����ı���
		image2 = new ImageIcon("G:\\2.jpg");
		jLabe2.setIcon(image2);
		jLabe2.setBounds(0, 0, 1000, 800);

		// ����������ͼ��
		String picture = data1.getWeatherMessage().get(19);
		String address = "G:\\a_" + picture;
		jLabe.setIcon(image);
		image = new ImageIcon(address);
		jLabe.setBounds(60, 60, 100, 100);
		// ��ȡ��ǰ������ͼ���ڼ������±�19��ƥ��洢��ͼ��ĵ�ַ
		// ����ͼ��
		String picture3 = data1.getWeatherMessage().get(23);
		String address3 = "G:\\a_" + picture;
		image3 = new ImageIcon(address3);
		jLabe3.setIcon(image3);
		jLabe3.setBounds(650, 130, 110, 100);
		this.add(jLabe3);
		// ��ȡ��ǰ������ͼ���ڼ������±�19��ƥ��洢��ͼ��ĵ�ַ
		// ����ͼ��
		String picture4 = data1.getWeatherMessage().get(28);
		String address4 = "G:\\a_" + picture;
		image4 = new ImageIcon(address4);
		jLabe4.setIcon(image4);
		jLabe4.setBounds(650, 405, 110, 100);
		this.add(jLabe4);

		this.add(jLabe);
		this.add(jLabe2);
		button.setText(city);
		// ����

	}

	public class MyJDialog extends JDialog implements ActionListener {
		private JButton[] button;
		// �ص���е�ѡ��ť
		private JButton button1;
		// ����ѡ��ȷ����ť
		private JLabel[] jLable;
		// �����������
		private List<String> province = new ArrayList<>(); // �洢��ʼ����ʡ��
		private List<String> city = new ArrayList<>(); // ����
		// ���ݵĶ���
		JComboBox<String> jcs = new JComboBox<String>();// �����б��ʡ��
		JComboBox<String> jcc = new JComboBox<String>();// �����б�����
		private String cityName; // ��ȡָ���ĳ���
		// ��ʼ��ȷ����ť���ص�ĳ���
		{
			button1 = new JButton("ȷ��");
			button = new JButton[] { new JButton("����"), new JButton("�Ϻ�"), new JButton("���"), new JButton("����"),
					new JButton("����"), new JButton("�ൺ"), new JButton("����"), new JButton("����"), new JButton("�人") };
			jLable = new JLabel[] { new JLabel("ʡ��"), new JLabel("����") };
			data = new Data();

		}

		// ���ι��캯��
		public MyJDialog(Window win) {
			// �Ӵ���
			super(win, "�л�����", true);
			// ���岼��
			this.setLayout(null);
			// �ı��������
			jcs.setBounds(140, 100, 140, 60);
			jcs.setFont(new Font("����", Font.PLAIN, 30));
			jcs.setEditable(true);// �б��ɱ༭
			// �����б��
			jcc.setBounds(380, 100, 220, 60);
			jcc.setFont(new Font("����", Font.PLAIN, 30));
			jcc.setEditable(true);// �б��ɱ༭
			// ���ʡ������
			data.getProvinces();
			province = data.getAllProvinces();
			// ������ʡ���б�����������
			for (int i = 0; i < province.size(); i++) {
				jcs.addItem(province.get(i));
			}
			// ʡ���б�����굥�������¼�
			jcs.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String str = (String) jcs.getSelectedItem();
						List<String> city = new ArrayList<String>();// ������
						// ͨ��ʡ������Data��ķ�����ȡ������Ϣ
						// ����ֲ����ݶ���������������
						Data data1 = new Data();
						data1.getCitys(str);
						city = data1.getAllCity();
						jcc.removeAllItems();
						// �����������б�����������
						for (int i = 0; i < city.size(); i++) {
							jcc.addItem(city.get(i));
						}

					}
				}
			});
			// ��굥�������¼�
			jcc.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String str = (String) jcc.getSelectedItem();
						cityName = str;
						if (cityName != null) {
							button1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									// ����ֲ����ݶ���������������
									newShows(cityName);
								}
							});
						}
					}
				}
			});

			// ���ñ�ǩ��ѭ������
			jLable[0].setBounds(80, 100, 80, 60);
			jLable[1].setBounds(320, 100, 80, 60);
			// ��ǩ������
			for (int i = 0; i < jLable.length; i++) {
				jLable[i].setFont(new Font("����", Font.PLAIN, 30));
				this.add(jLable[i]);
			}

			// ���ó��е�λ��
			button[0].setBounds(50, 400, 100, 100);
			button[1].setBounds(250, 400, 100, 100);
			button[2].setBounds(450, 400, 100, 100);
			button[3].setBounds(50, 535, 100, 100);
			button[4].setBounds(250, 535, 100, 100);
			button[5].setBounds(450, 535, 100, 100);
			button[6].setBounds(50, 650, 100, 100);
			button[7].setBounds(250, 650, 100, 100);
			button[8].setBounds(450, 650, 100, 100);

			for (int i = 0; i < button.length; i++) {
				// Ϊ��ť���ü�����
				button[i].addActionListener(this);
				// ���ñ���
				button[i].setBackground(new Color(205, 255, 200));
				// ��������Ĵ�С
				button[i].setFont(new Font("����", Font.PLAIN, 30));
				// ��ť�ӽ�����
				this.add(button[i]);
			}
			// ȷ����ť
			button1.setBounds(630, 80, 100, 100);
			button1.setFont(new Font("����", Font.PLAIN, 30));
			button1.setBackground(new Color(205, 205, 150));
			this.add(button1);
			// �����Ӧ���
			this.add(jcs);
			this.add(jcc);

			// ����������
			this.getContentPane().setBackground(new Color(123, 180, 40));// ���ô��屳����ɫ
			this.setResizable(false);
			this.setBounds(0, 0, 1000, 800);
		}

		// ��д�������¼�ִ����Ӧ�Ĺ���
		public void actionPerformed(ActionEvent e) {
			String city = null;
			for (int i = 0; i < button.length - 1; i++) {
				if (e.getSource() == button[i]) {
					city = button[i].getText();
				}
				if (city != null)
					break;
			}
			newShows(city);
		}

	}

	// ʱ���ڲ���
	class ShowTime extends TimerTask {
		public void run() {
			// ˢ��
			repaint();
		}
	}

	class TimePanel extends JPanel {
		public void paint(Graphics g) {
			super.paint(g);
			// ��ʾʱ��
			g.drawString(sdf.format(new Date()), 10, 10);
		}
	}

}
