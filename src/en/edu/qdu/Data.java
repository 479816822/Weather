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
	//���ϴ洢����֧�ֵ�ʡ��
	private List<String> allProvinces;
	//���ϴ洢��ѡʡ����֧�ֵĳ���
	private List<String> allCity;
	//���ϴ洢������Ϣ
	private List<String> weatherMessage;
		
	//��ʼ����
	{
		allProvinces=new ArrayList<String>();
		allCity=new ArrayList<String>();
		weatherMessage=new ArrayList<String>();
	}
		
			
	//HTTP post���󲢻�ȡProvince��֧�ֵ�ʡ���Լ�ֱϽ��
	public List<String> getProvinces(){
		OutputStreamWriter out=null;
		try {
			//ͨ��URL�ϴ�����openConnection()�����������Ӷ���
			URL urlTemp=new URL("http://www.webxml.com.cn/WebServices/WeatherWS.asmx/getRegionProvince");
			//����URL��ȡURLConnection����
			URLConnection connection=urlTemp.openConnection();
			//����Э��ΪHTTPЭ��
			HttpURLConnection httpConnection =(HttpURLConnection)connection;
			httpConnection.setDoOutput(true);//�Ƿ�д������
			httpConnection.setDoInput(true);//�Ƿ��������
			//ʹ��connect����������Զ�̶����ʵ������
			httpConnection.connect();
			out=new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");
			//ˢ��
			out.flush();			
			//��ȡ����--��֧�ֵ�ʡ�ݡ�ֱϽ������ ���ݷ�������ΪһάString����
			InputStream in=httpConnection.getInputStream();
			String allMessage="";
			String str="";
			BufferedReader read=new BufferedReader(new InputStreamReader(in,"UTF-8"));
			while((str=read.readLine())!=null){
				allMessage+=str;
			}
			//�������ݴ���--ֻ����ʡ��
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
		
	//HTTP post���󲢻�ȡProvince��֧�ֵĳ���
	public List<String> getCitys(String provinceName){
		OutputStreamWriter out=null;
		try {
			//ͨ��URL�ϴ�����openConnection()�����������Ӷ���
			URL urlTemp=new URL("http://www.webxml.com.cn/WebServices/WeatherWS.asmx/getSupportCityString");
			//����URL��ȡURLConnection����
			URLConnection connection=urlTemp.openConnection();
			//����Э��ΪHTTPЭ��
			HttpURLConnection httpConnection =(HttpURLConnection)connection;
			httpConnection.setDoOutput(true);//�Ƿ�д������
			httpConnection.setDoInput(true);//�Ƿ��������
			//ʹ��connect����������Զ�̶����ʵ������
			httpConnection.connect();
			out=new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");		
			out.write("theRegionCode="+provinceName);
			//ˢ��
			out.flush();
			//��ȡ����--province��֧�ֵĳ������� ���ݷ�������ΪһάString����
			InputStream in=httpConnection.getInputStream();
			String allMessage="";
			String str="";
			BufferedReader read=new BufferedReader(new InputStreamReader(in,"UTF-8"));
			while((str=read.readLine())!=null){
				allMessage+=str;
			}	
			//�������ݴ���--ֻ���³�����
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
		
	//HTTP post���󲢻�ȡcity��������Ϣ
	public List<String> getWeather(String cityName){
		OutputStreamWriter out=null;
		try {
			//ͨ��URL�ϴ�����openConnection()�����������Ӷ���
			URL urlTemp=new URL("http://www.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather");
			//����URL��ȡURLConnection����
			URLConnection connection=urlTemp.openConnection();
			//����Э��ΪHTTPЭ��
			HttpURLConnection httpConnection =(HttpURLConnection)connection;
			httpConnection.setDoOutput(true);//�Ƿ�д������
			httpConnection.setDoInput(true);//�Ƿ��������
			//ʹ��connect����������Զ�̶����ʵ������
			httpConnection.connect();
			out=new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");		
			out.write("theCityCode="+cityName+"&theUserID="+"");
			//ˢ��
			out.flush();
			//��ȡ����--cityNamr����������   ���ݷ�������ΪһάString����
			InputStream in=httpConnection.getInputStream();
			String allMessage="";
			String str="";
			BufferedReader read=new BufferedReader(new InputStreamReader(in,"UTF-8"));
			while((str=read.readLine())!=null){
				allMessage+=str;
			}	
			//�������ݴ���
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
	* �����õ�������
	* ���ݵõ������ݽ�ȡ����Ҫ�Ĳ���
	*/
	public List<String> getNeedMessage(String allMessage,boolean isWeather) {				
		//ȥ����ǩ
		int beingIndex=allMessage.indexOf("<string>")+8;
		int endIndex=allMessage.lastIndexOf("</string>");
		String str=allMessage.substring(beingIndex, endIndex);
		String[] arrStr=str.split("</string>  <string>");
					
		//��ȡ��Ҫ����Ϣ
		List<String> arrlistTemp=new ArrayList<String>();
		String strTemp="";
		for (int i = 0; i < arrStr.length; i++) {
			if(isWeather){
				if(i==4){
					//���������Ϣ����
					//��������ʵ��--���¡�������ʪ��
					String[] strTemps=arrStr[i].split("��");	//'��'��������״̬�µ�'��'
					for (int j = 0; j < strTemps.length; j++) {
						arrlistTemp.add(strTemps[j]);		
					}
				}else if(i==5 || i==6){  //�����ߡ����������Լ������Ƽ�
					String[] strTemps=arrStr[i].split("��");
					for (int j = 0; j < strTemps.length; j++) {
						arrlistTemp.add(strTemps[j]);					
					}
				}
				//����Ϊ4~6���������洦����ӽ�ȥ��
				if(i<4 || i>6){
					arrlistTemp.add(arrStr[i]);					
				}
				arrlistTemp.remove("");//ɾ��������ֵΪΪ""��Ԫ��
			}else{
				//��������ʡ��������--��Ҫʡ�ݻ���д���
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
