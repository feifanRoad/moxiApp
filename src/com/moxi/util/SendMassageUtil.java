package com.moxi.util;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class SendMassageUtil {
	
	private static String serviceURL;
	
	private static String loginName;
	
	private static String passWord;
     
    static{
    	  Properties prop = new Properties(); 
        try{
            //读取属性文件a.properties
            InputStream in = SendMassageUtil.class.getClassLoader().getResourceAsStream("service.properties");  
            //加载属性列表
            prop.load(in);
            serviceURL = prop.getProperty("serviceurl"); 
            loginName = prop.getProperty("loginName"); 
            passWord = prop.getProperty("passWord"); 
            ///保存属性到b.properties文件
            FileOutputStream oFile = new FileOutputStream("b.properties", true);//true表示追加打开
            oFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
	
	/**
	 * 发送短信方法<BR>
	 * 方法名：post<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午12:02:08
	 * @param phoneNumber
	 * @param randomnumber
	 * @return
	 * @throws Exception String
	 * @exception 
	 * @since  1.0.0
	 */
	 public static String post(String phoneNumber,StringBuilder randomnumber) throws Exception{ 
		 
		String  params="id="+loginName+"&pwd="+passWord+"&to="+phoneNumber+"&content="+URLEncoder.encode("您好，您的验证码是"+randomnumber+"【中正云通信】","gb2312")+"&time="+TmStringUtils.getStrTime(TmStringUtils.getTimestampTime());
		 
         BufferedReader in=null;
         PrintWriter out=null;  
         
         HttpURLConnection httpConn = null;
         try {                 
	         URL url=new URL(serviceURL);
	         httpConn=(HttpURLConnection)url.openConnection(); 
	         httpConn.setRequestMethod("POST"); 
	         httpConn.setDoInput(true);
	         httpConn.setDoOutput(true); 
	      
	         out=new PrintWriter(httpConn.getOutputStream());
	         out.println(params);
	         out.flush();
	                                               
		    if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){
				StringBuffer content = new StringBuffer();
				String tempStr = "";
				in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				while ((tempStr = in.readLine()) != null) {
					content.append(tempStr);
				}
				return content.toString();
			} else {
				throw new Exception("请求出现了问题!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
			httpConn.disconnect();
		}
		return null;   
     }   
		
	 public static String HTTPPOST(String url) throws Exception{ 
		 
         BufferedReader in=null;
         PrintWriter out=null;  
         
         HttpURLConnection httpConn = null;
         try {                 
          URL url_HTTPPOST=new URL(url);
         httpConn=(HttpURLConnection)url_HTTPPOST.openConnection(); 
         httpConn.setRequestMethod("POST"); 
         httpConn.setDoInput(true);
         httpConn.setDoOutput(true); 
      
         out=new PrintWriter(httpConn.getOutputStream());
         out.println(out);
         out.flush();
                                               
    if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){  
         StringBuffer content=new StringBuffer(); 
         String tempStr="";  
         in=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"utf-8"));   
         while((tempStr=in.readLine())!=null){   
         content.append(tempStr);   
         }   
         return  content.toString();
         
         }else{   
         throw new Exception("请求出现了问题!");  
         }  
         } catch (IOException e) {  
         e.printStackTrace();   
         }finally{ 
        	 if(in!=null){
        		 in.close();   
                 out.close();   
                 httpConn.disconnect();   
        	 }
         }    
         return null;   
       
      }   
	 
	 /**
	  * 忘记密码
	  * 发送给用户短信<BR>
	  * 方法名：forgetPassWordpost<BR>
	  * 创建人：feifan <BR>
	  * 时间：2017年7月2日-上午12:04:21
	  * @param phoneNumber
	  * @param password
	  * @return
	  * @throws Exception String
	  * @exception 
	  * @since  1.0.0
	  */
	 public static String forgetPassWordpost(String phoneNumber,String password) throws Exception{ 
		 
		String  params="id="+loginName+"&pwd="+passWord+"&to="+phoneNumber+"&content="+URLEncoder.encode("您好，你的密码是"+password+",请保护好自己的密码"+"【中正云通信】","gb2312")+"&time="+TmStringUtils.getStrTime(TmStringUtils.getTimestampTime());
		 
         BufferedReader in=null;
         PrintWriter out=null;  
         
         HttpURLConnection httpConn = null;
         try {                 
         URL url=new URL(serviceURL);
         httpConn=(HttpURLConnection)url.openConnection(); 
         httpConn.setRequestMethod("POST"); 
         httpConn.setDoInput(true);
         httpConn.setDoOutput(true); 
      
         out=new PrintWriter(httpConn.getOutputStream());
         out.println(params);
         out.flush();
                                               
    if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){  
         StringBuffer content=new StringBuffer(); 
         String tempStr="";  
         in=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));   
         while((tempStr=in.readLine())!=null){   
         content.append(tempStr);   
         }   
         return content.toString();
         }else{   
         throw new Exception("请求出现了问题!");  
         }  
         } catch (IOException e) {  
         e.printStackTrace();   
         }finally{   
         in.close();   
         out.close();   
         httpConn.disconnect();  
         }    
         return null;   
	 } 
	 
	 
	 public static void main(String[] args) throws Exception {
		 
//		 String zz= HTTPPOST("http://api.kaijiangtong.com/lottery/?name=sd&format=json&uid=743362&token=70ece3eedfe88729358657bcc5048f62638f8b0e");
//		 System.out.println(zz);
	}
	 
	 
}
