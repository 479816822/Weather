package en.edu.qdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Data {
	//集合存储所有支持的省份
	private List<String> allProvinces;
	//集合存储所选省份所支持的城市
	private List<String> allCity;
	//集合存储天气信息
	private List<String> weatherMessage;
		
	//初始化块
	{
		allProvinces=new ArrayList<String>();
		allCity=new ArrayList<String>();
		weatherMessage=new ArrayList<String>();
	}
		
			
	//HTTP post请求并获取Province可支持的省份以及直辖市
	public List<String> getProvinces(){
		OutputStreamWriter out=null;
		try {
			//通过URL上代调用openConnection()方法创建连接对象
			URL urlTemp=new URL("http://www.webxml.com.cn/WebServices/WeatherWS.asmx/getRegionProvince");
			//根据URL获取URLConnection对象
			URLConnection connection=urlTemp.openConnection();
			//请求协议为HTTP协议
			HttpURLConnection httpConnection =(HttpURLConnection)connection;
			httpConnection.setDoOutput(true);//是否写入链接
			httpConnection.setDoInput(true);//是否读入链接
			//使用connect方法建立到远程对象的实际连接
			httpConnection.connect();
			out=new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");
			//刷新
			out.flush();			
			//获取数据--所支持的省份、直辖市数据 数据返回类型为一维String数组
			InputStream in=httpConnection.getInputStream();
			String allMessage="";
			String str="";
			BufferedReader read=new BufferedReader(new InputStreamReader(in,"UTF-8"));
			while((str=read.readLine())!=null){
				allMessage+=str;
			}
			//进行数据处理--只留下省名
			allProvinces=getNeedMessage(allMessage,false);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		return allProvinces;
	}
		
	//HTTP post请求并获取Province可支持的城市
	public List<String> getCitys(String provinceName){
		OutputStreamWriter out=null;
		try {
			//通过URL上代调用openConnection()方法创建连接对象
			URL urlTemp=new URL("http://www.webxml.com.cn/WebServices/WeatherWS.asmx/getSupportCityString");
			//根据URL获取URLConnection对象
			URLConnection connection=urlTemp.openConnection();
			//请求协议为HTTP协议
			HttpURLConnection httpConnection =(HttpURLConnection)connection;
			httpConnection.setDoOutput(true);//是否写入链接
			httpConnection.setDoInput(true);//是否读入链接
			//使用connect方法建立到远程对象的实际连接
			httpConnection.connect();
			out=new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");		
			out.write("theRegionCode="+provinceName);
			//刷新
			out.flush();
			//获取数据--province所支持的城市数据 数据返回类型为一维String数组
			InputStream in=httpConnection.getInputStream();
			String allMessage="";
			String str="";
			BufferedReader read=new BufferedReader(new InputStreamReader(in,"UTF-8"));
			while((str=read.readLine())!=null){
				allMessage+=str;
			}	
			//进行数据处理--只留下城市名
			allCity=getNeedMessage(allMessage,false);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		return allCity;
	}
		
	//HTTP post请求并获取city的天气信息
	public List<String> getWeather(String cityName){
		OutputStreamWriter out=null;
		try {
			//通过URL上代调用openConnection()方法创建连接对象
			URL urlTemp=new URL("http://www.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather");
			//根据URL获取URLConnection对象
			URLConnection connection=urlTemp.openConnection();
			//请求协议为HTTP协议
			HttpURLConnection httpConnection =(HttpURLConnection)connection;
			httpConnection.setDoOutput(true);//是否写入链接
			httpConnection.setDoInput(true);//是否读入链接
			//使用connect方法建立到远程对象的实际连接
			httpConnection.connect();
			out=new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");		
			out.write("theCityCode="+cityName+"&theUserID="+"");
			//刷新
			out.flush();
			//获取数据--cityNamr的天气数据   数据返回类型为一维String数组
			InputStream in=httpConnection.getInputStream();
			String allMessage="";
			String str="";
			BufferedReader read=new BufferedReader(new InputStreamReader(in,"UTF-8"));
			while((str=read.readLine())!=null){
				allMessage+=str;
			}	
			//进行数据处理
			weatherMessage=getNeedMessage(allMessage,true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return weatherMessage;
	}
		
	/*
	* 解析得到的数据
	* 根据得到的数据截取出需要的部分
	*/
	public List<String> getNeedMessage(String allMessage,boolean isWeather) {				
		//去掉标签
		int beingIndex=allMessage.indexOf("<string>")+8;
		int endIndex=allMessage.lastIndexOf("</string>");
		String str=allMessage.substring(beingIndex, endIndex);
		String[] arrStr=str.split("</string>  <string>");
					
		//提取需要的信息
		List<String> arrlistTemp=new ArrayList<String>();
		String strTemp="";
		for (int i = 0; i < arrStr.length; i++) {
			if(isWeather){
				if(i==4){
					//天气情况信息处理
					//今日天气实况--气温、风力、湿度
					String[] strTemps=arrStr[i].split("；");	//'；'号是中文状态下的'；'
					for (int j = 0; j < strTemps.length; j++) {
						arrlistTemp.add(strTemps[j]);		
					}
				}else if(i==5 || i==6){  //紫外线、空气质量以及生活推荐
					String[] strTemps=arrStr[i].split("。");
					for (int j = 0; j < strTemps.length; j++) {
						arrlistTemp.add(strTemps[j]);					
					}
				}
				//集合为4~6的已在上面处理并添加进去了
				if(i<4 || i>6){
					arrlistTemp.add(arrStr[i]);					
				}
				arrlistTemp.remove("");//删除集合中值为为""的元素
			}else{
				//城市名和省份名处理--不要省份或城市代码
				int indexTemp=arrStr[i].indexOf(",");
				strTemp=arrStr[i].substring(0, indexTemp);
				}			
					arrlistTemp.add(strTemp);
			}
				return arrlistTemp;
		}

	public List<String> getAllProvinces() {
		return allProvinces;
	}

	public void setAllProvinces(List<String> allProvinces) {
		this.allProvinces = allProvinces;
	}

	public List<String> getAllCity() {
		return allCity;
	}

	public void setAllCity(List<String> allCity) {
		this.allCity = allCity;
	}

	public List<String> getWeatherMessage() {
		return weatherMessage;
	}

	public void setWeatherMessage(List<String> weatherMessage) {
		this.weatherMessage = weatherMessage;
	}

}
