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
	private JLabel[] jLabel; // 定义一个标签数组对象
	private JLabel[] jLabe33; // 定义一个标签数组对象
	private JLabel[] jLabe44; // 定义一个标签数组对象
	private JLabel jLabe2; // 定义一个标签对象窗体的背景
	private JLabel jLabe; // 定义一个标签用来显示天气背景
	private JLabel jLabe3; // 定义一个标签用来显示天气背景明天
	private JLabel jLabe4; // 定义一个标签用来显示天气背景后天
	private ImageIcon image; // 即时天气的图片
	private ImageIcon image3; // 即时天气的图片明天
	private ImageIcon image4; // 即时天气的图片后天
	private ImageIcon image2; // 背景
	private Image image1; // 窗体的图标
	private JButton button; // 当前城市按钮
	private static Data data; // 定义一个静态的全局数据对象，用来更新调用数据
	// 集合存储所有支持的省份
	private static List<String> allProvinces; // 定义一个静态全局的数据集合，用来保存省份调用数据
	// 集合存储所选省份所支持的城市
	private List<String> allCity;
	private Data data1;
	// 集合存储天气信息
	private List<String> weatherMessage;

	// 时间的显示格式
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");

	// 初始化块
	{
		button = new JButton();
		allProvinces = new ArrayList<String>();
		allCity = new ArrayList<String>();
		weatherMessage = new ArrayList<String>();
		data = new Data();
	}

	// 初始化界面//,
	public Window(String city) {
		// 设置窗体布局管理器为null
		this.setLayout(null);
		// 初始化数据对象，程序运行时显示默认城市北京
		// 调用getWeather，获取北京的天气信息
		weatherMessage = data.getWeather(city);

		// String imageIndex = this.data.getWeatherMessage().get(28);
		// 获取天气的所有信息
		// weatherMessage = data.getWeatherMessage();
		// 定义9个标签用来显示天气信息
		jLabel = new JLabel[9];
		jLabe33 = new JLabel[3];// 用来显示未来明天的数据的标签
		jLabe44 = new JLabel[3];// 用来显示未来后天的数据的标签
		// 用来显示图标
		jLabe = new JLabel();
		jLabe2 = new JLabel();
		jLabe3 = new JLabel();
		jLabe4 = new JLabel();
		// 设置每一个标签的内容
		for (int i = 0; i < 9; i++) {
			jLabel[i] = new JLabel();
		}
		// 设置每一个标签的内容
		for (int i = 0; i < 3; i++) {
			jLabe33[i] = new JLabel();
		}
		// 设置每一个标签的内容
		for (int i = 0; i < 3; i++) {
			jLabe44[i] = new JLabel();
		}
		// 当天的信息
		jLabel[1].setText(weatherMessage.get(16));
		jLabel[0].setText(weatherMessage.get(15));
		jLabel[2].setText(weatherMessage.get(17));
		jLabel[3].setText(weatherMessage.get(3)); // 用来显示时间
		jLabel[4].setText("生活指数:");
		jLabel[5].setText(weatherMessage.get(10));
		jLabel[6].setText(weatherMessage.get(11));
		jLabel[7].setText(weatherMessage.get(13));
		jLabel[8].setText(weatherMessage.get(14));
		// 今后两天的信息
		jLabe33[1].setText(weatherMessage.get(20));
		jLabe33[0].setText(weatherMessage.get(21));
		jLabe33[2].setText(weatherMessage.get(22));
		jLabe44[1].setText(weatherMessage.get(25));
		jLabe44[0].setText(weatherMessage.get(26));
		jLabe44[2].setText(weatherMessage.get(27));

		// 设置标签de大小位置添加进容器
		for (int i = 5; i < 9; i++) {
			jLabel[i].setBounds(20, 550 + (i - 5) * 40, 1000, 50);
			jLabel[i].setFont(new Font("宋体", Font.BOLD, 20));
		}
		// 设置后两天标签的大小位置添加进容器
		for (int i = 0; i < 3; i++) {
			jLabe33[i].setBounds(650, 255 + i * 50, 200, 50);
			jLabe33[i].setFont(new Font("宋体", Font.BOLD, 20));
		}
		// 设置后两天标签的大小位置添加进容器
		for (int i = 0; i < 3; i++) {
			jLabe44[i].setBounds(650, 500 + i * 50, 200, 50);
			jLabe44[i].setFont(new Font("宋体", Font.BOLD, 20));
		}
		jLabel[0].setBounds(60, 140, 280, 50);
		jLabel[1].setBounds(60, 200, 280, 50);
		jLabel[2].setBounds(60, 260, 280, 50);
		jLabel[3].setBounds(60, 310, 350, 50);
		jLabel[4].setBounds(20, 510, 180, 40);
		for (int i = 0; i < 5; i++) {
			jLabel[i].setFont(new Font("宋体", Font.BOLD, 30));

		}
		// 添加标签进容器
		for (int i = 0; i < jLabel.length; i++) {
			this.add(jLabel[i]);
		}
		// 添加标签进容器
		for (int i = 0; i < jLabe33.length; i++) {
			this.add(jLabe33[i]);
		}
		// 添加标签进容器
		for (int i = 0; i < jLabe44.length; i++) {
			this.add(jLabe44[i]);
		}
		// 获取当前天气的图标在集合中下标19和匹配存储的图标的地址
		// 天气图标
		String picture = data.getWeatherMessage().get(19);
		String address = "G:\\a_" + picture;
		image = new ImageIcon(address);
		jLabe.setIcon(image);
		jLabe.setBounds(60, 60, 100, 100);
		this.add(jLabe);
		// 获取当前天气的图标在集合中下标19和匹配存储的图标的地址
		// 天气图标
		String picture3 = data.getWeatherMessage().get(23);
		String address3 = "G:\\a_" + picture;
		image3 = new ImageIcon(address3);
		jLabe3.setIcon(image3);
		jLabe3.setBounds(650, 130, 110, 100);
		this.add(jLabe3);
		// 获取当前天气的图标在集合中下标19和匹配存储的图标的地址
		// 天气图标
		String picture4 = data.getWeatherMessage().get(28);
		String address4 = "G:\\a_" + picture;
		image4 = new ImageIcon(address4);
		jLabe4.setIcon(image4);
		jLabe4.setBounds(650, 405, 110, 100);
		this.add(jLabe4);
		// 窗体背景
		image2 = new ImageIcon("G:\\2.jpg");
		jLabe2.setIcon(image2);
		jLabe2.setBounds(0, 0, 1000, 800);
		this.add(jLabe2);
		// 当前的城市按钮
		button.setText(city);
		button.setFont(new Font("黑体", Font.PLAIN, 40));
		button.setBounds(670, 20, 200, 100);
		button.addActionListener(this);
		button.setFocusPainted(false);
		button.setBackground(new Color(215, 205, 200));
		this.add(button);

		// 设置窗体：天气预报
		this.setTitle("天气预报");
		// 设置窗体的大小
		this.setBounds(0, 0, 1000, 800);
		// 窗体的图标
		Image image1 = this.getToolkit().getImage("G:\\1.jpg");
		this.setIconImage(image1);
		// 窗体不可调大小
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		;

		// 时间的调用
		// 设置时间变化的任务
		// Timer timer = new Timer();
		// timer.schedule(new ShowTime(), new Date(), 1000);
	}

	public void actionPerformed(ActionEvent arg0) {
		// 监听执行这里的功能
		// 打开一个子窗口
		new MyJDialog(Window.this).setVisible(true);
	}

	// 数据更新
	public void newShows(String city) {
		// 局部的天气信息变量
		List<String> weatherMessage = new ArrayList<String>();
		// 局部对象更新数据
		Data data1 = new Data();
		data1 = new Data();
		// 更新城市的数据
		weatherMessage = data1.getWeather(city);
		// 得到更新后的天气数据
		jLabel[1].setText(weatherMessage.get(16));
		jLabel[0].setText(weatherMessage.get(15));
		jLabel[2].setText(weatherMessage.get(17));
		jLabel[3].setText(weatherMessage.get(3));// 显示时间
		jLabel[4].setText("生活指数:");
		jLabel[5].setText(weatherMessage.get(10));
		jLabel[6].setText(weatherMessage.get(11));
		jLabel[7].setText(weatherMessage.get(13));
		jLabel[8].setText(weatherMessage.get(14));

		// 今后两天的信息
		jLabe33[1].setText(weatherMessage.get(20));
		jLabe33[0].setText(weatherMessage.get(21));
		jLabe33[2].setText(weatherMessage.get(22));
		jLabe44[1].setText(weatherMessage.get(25));
		jLabe44[0].setText(weatherMessage.get(26));
		jLabe44[2].setText(weatherMessage.get(27));

		// 天气的图标
		// this.setBounds(0, 0, 1000, 800);
		// 窗体的背景
		image2 = new ImageIcon("G:\\2.jpg");
		jLabe2.setIcon(image2);
		jLabe2.setBounds(0, 0, 1000, 800);

		// 更新天气的图标
		String picture = data1.getWeatherMessage().get(19);
		String address = "G:\\a_" + picture;
		jLabe.setIcon(image);
		image = new ImageIcon(address);
		jLabe.setBounds(60, 60, 100, 100);
		// 获取当前天气的图标在集合中下标19和匹配存储的图标的地址
		// 天气图标
		String picture3 = data1.getWeatherMessage().get(23);
		String address3 = "G:\\a_" + picture;
		image3 = new ImageIcon(address3);
		jLabe3.setIcon(image3);
		jLabe3.setBounds(650, 130, 110, 100);
		this.add(jLabe3);
		// 获取当前天气的图标在集合中下标19和匹配存储的图标的地址
		// 天气图标
		String picture4 = data1.getWeatherMessage().get(28);
		String address4 = "G:\\a_" + picture;
		image4 = new ImageIcon(address4);
		jLabe4.setIcon(image4);
		jLabe4.setBounds(650, 405, 110, 100);
		this.add(jLabe4);

		this.add(jLabe);
		this.add(jLabe2);
		button.setText(city);
		// 更新

	}

	public class MyJDialog extends JDialog implements ActionListener {
		private JButton[] button;
		// 重点城市的选择按钮
		private JButton button1;
		// 城市选择确定按钮
		private JLabel[] jLable;
		// 下拉框的名称
		private List<String> province = new ArrayList<>(); // 存储初始化的省份
		private List<String> city = new ArrayList<>(); // 城市
		// 数据的对象
		JComboBox<String> jcs = new JComboBox<String>();// 下拉列表框省份
		JComboBox<String> jcc = new JComboBox<String>();// 下拉列表框城市
		private String cityName; // 获取指定的城市
		// 初始化确定按钮、重点的城市
		{
			button1 = new JButton("确定");
			button = new JButton[] { new JButton("北京"), new JButton("上海"), new JButton("天津"), new JButton("深圳"),
					new JButton("广州"), new JButton("青岛"), new JButton("重庆"), new JButton("贵阳"), new JButton("武汉") };
			jLable = new JLabel[] { new JLabel("省份"), new JLabel("城市") };
			data = new Data();

		}

		// 带参构造函数
		public MyJDialog(Window win) {
			// 子窗体
			super(win, "切换城市", true);
			// 窗体布局
			this.setLayout(null);
			// 文本框的设置
			jcs.setBounds(140, 100, 140, 60);
			jcs.setFont(new Font("黑体", Font.PLAIN, 30));
			jcs.setEditable(true);// 列表框可编辑
			// 城市列表框
			jcc.setBounds(380, 100, 220, 60);
			jcc.setFont(new Font("黑体", Font.PLAIN, 30));
			jcc.setEditable(true);// 列表框可编辑
			// 获得省份数据
			data.getProvinces();
			province = data.getAllProvinces();
			// 往下拉省市列表框中添加数据
			for (int i = 0; i < province.size(); i++) {
				jcs.addItem(province.get(i));
			}
			// 省市列表框的鼠标单击监听事件
			jcs.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String str = (String) jcs.getSelectedItem();
						List<String> city = new ArrayList<String>();// 测试用
						// 通过省名调用Data类的方法获取城市信息
						// 定义局部数据对象用来更新数据
						Data data1 = new Data();
						data1.getCitys(str);
						city = data1.getAllCity();
						jcc.removeAllItems();
						// 往下拉城市列表框中添加数据
						for (int i = 0; i < city.size(); i++) {
							jcc.addItem(city.get(i));
						}

					}
				}
			});
			// 鼠标单击监听事件
			jcc.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String str = (String) jcc.getSelectedItem();
						cityName = str;
						if (cityName != null) {
							button1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									// 定义局部数据对象用来更新数据
									newShows(cityName);
								}
							});
						}
					}
				}
			});

			// 设置标签、循环处理
			jLable[0].setBounds(80, 100, 80, 60);
			jLable[1].setBounds(320, 100, 80, 60);
			// 标签进容器
			for (int i = 0; i < jLable.length; i++) {
				jLable[i].setFont(new Font("黑体", Font.PLAIN, 30));
				this.add(jLable[i]);
			}

			// 设置城市的位置
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
				// 为按钮设置监听器
				button[i].addActionListener(this);
				// 设置背景
				button[i].setBackground(new Color(205, 255, 200));
				// 设置字体的大小
				button[i].setFont(new Font("黑体", Font.PLAIN, 30));
				// 按钮加进容器
				this.add(button[i]);
			}
			// 确定按钮
			button1.setBounds(630, 80, 100, 100);
			button1.setFont(new Font("黑体", Font.PLAIN, 30));
			button1.setBackground(new Color(205, 205, 150));
			this.add(button1);
			// 添加相应组件
			this.add(jcs);
			this.add(jcc);

			// 容器进窗体
			this.getContentPane().setBackground(new Color(123, 180, 40));// 设置窗体背景颜色
			this.setResizable(false);
			this.setBounds(0, 0, 1000, 800);
		}

		// 重写监听器事件执行相应的功能
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

	// 时间内部类
	class ShowTime extends TimerTask {
		public void run() {
			// 刷新
			repaint();
		}
	}

	class TimePanel extends JPanel {
		public void paint(Graphics g) {
			super.paint(g);
			// 显示时间
			g.drawString(sdf.format(new Date()), 10, 10);
		}
	}

}
