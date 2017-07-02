package com.moxi.util;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.moxi.model.LotteryPoJo;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

public class Utils {
	
	private static String fileName="service.properties";
	
	public static Timestamp getTimestampTime(){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		return time;
	}

	public static String getStrTime(Timestamp time){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String strtime=dateFormat.format(time);
		return strtime;
	}
	
     public static String getFilePath(Timestamp time){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	    String strtime=dateFormat.format(time);
		return strtime;
	}
	
     public static String getStrTimeBySign(Timestamp time){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String strtime=dateFormat.format(time);
		return strtime;
	}
	
    public static Timestamp changeStrTimeToTimeStamp(String strtime) throws ParseException{
		Timestamp time=Timestamp.valueOf(strtime);
		return time;
	}
	
	public static String writeFile(CommonsMultipartFile file) throws IOException{
	    //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        //System.out.println("fileName："+file.getOriginalFilename());
        String newFilePath="E:/"+new Date(startTime).getTime()+file.getOriginalFilename();
        try {
            //获取输出流
            OutputStream os=new FileOutputStream(newFilePath);
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
           os.flush();
           os.close();
           is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long  endTime=System.currentTimeMillis();
        //System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
		return newFilePath;
	}
	
	public static StringBuilder createRandomNumber(){
		String str="0123456789";
		StringBuilder sb=new StringBuilder(4);
		for(int i=0;i<4;i++)
		{
			char ch=str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		return sb;
	}

    public static String  readProperryFile(String key){
      String value="";
  	  Properties prop = new Properties(); 
      try{
         //读取属性文件a.properties
         InputStream in = SendMassageUtil.class.getClassLoader().getResourceAsStream(fileName);  
         prop.load(in);//加载属性列表
         value = prop.getProperty(key); 
         //保存属性到b.properties文件
         FileOutputStream oFile = new FileOutputStream("b.properties", true);//true表示追加打开
         oFile.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
    	return value;
    }
    
	/**
	 * 第三方法接口返回的数据处理,auther jack 2017,4.15
	 * @param content
	 * @param shaungseqiu
	 * @param size
	 * @return
	 */
	public static List<LotteryPoJo> manipulation(String content,String shaungseqiu,int size){
		
	    int countlength=content.toString().length()-1;
		content=content.substring(1, countlength);
//		System.out.println(content);
		String str_content="";
		List<LotteryPoJo> list= new ArrayList<LotteryPoJo>();
		for(int i=0;i<size;i++){
			LotteryPoJo pojo= new LotteryPoJo();
			if(i==0){
				 str_content=content;
				 String qihao=str_content.substring(0,str_content.indexOf(":"));
					
					String line=content.substring(str_content.indexOf(":")+1,str_content.indexOf("}")+1);
//					System.out.println(qihao);
//					System.out.println(line);
					str_content=str_content.substring(str_content.indexOf("}")+1,str_content.length());
				    JSONObject obj = JSONObject.fromObject(line);
				    pojo = (LotteryPoJo)JSONObject.toBean(obj, LotteryPoJo.class);
				    pojo.setType(shaungseqiu);
					pojo.setQihao(qihao.substring(1,qihao.length()-1));
					pojo.setCreatetime(Utils.getTimestampTime());
					
				    list.add(pojo);
			}else{
				String qihao=str_content.substring(1,str_content.indexOf(":"));
				
				String line=str_content.substring(str_content.indexOf(":")+1,str_content.indexOf("}")+1);
//				System.out.println(qihao);
//				System.out.println(line);
				str_content=str_content.substring(str_content.indexOf("}")+1,str_content.length());
			    JSONObject obj = JSONObject.fromObject(line);
			    pojo = (LotteryPoJo)JSONObject.toBean(obj, LotteryPoJo.class);
			    pojo.setType(shaungseqiu);
				pojo.setQihao(qihao.substring(1,qihao.length()-1));
				pojo.setCreatetime(Utils.getTimestampTime());
			    list.add(pojo);
//			    content=str_content;
			}
		}
		return list;
	}
	
	
	
	
	//获取全部
	public static List<LotteryPoJo> getParentList(List<LotteryPoJo> list,List<LotteryPoJo> list_D3,List<LotteryPoJo> list_D3shijihao,List<LotteryPoJo> list_qilecai,List<LotteryPoJo> list_dalexiu,List<LotteryPoJo> list_pailiesan,List<LotteryPoJo> list_pailiewu,List<LotteryPoJo> list_qixingcai){
		List<LotteryPoJo> Parent= new ArrayList<LotteryPoJo>();
		try{
		
		for(int i=0;i<list.size();i++){//1
			Parent.add(list.get(i));
		}
//		
		for(int i=0;i<list_D3.size();i++){//2
			Parent.add(list_D3.get(i));
		}
		for(int i=0;i<list_D3shijihao.size();i++){//3
			Parent.add(list_D3shijihao.get(i));
		}
		for(int i=0;i<list_qilecai.size();i++){//4
			Parent.add(list_qilecai.get(i));
		}
		for(int i=0;i<list_dalexiu.size();i++){//5
			Parent.add(list_dalexiu.get(i));
		}
		for(int i=0;i<list_pailiesan.size();i++){//6
			Parent.add(list_pailiesan.get(i));
		}
		for(int i=0;i<list_pailiewu.size();i++){//7
			Parent.add(list_pailiewu.get(i));
		}
		for(int i=0;i<list_qixingcai.size();i++){//8
			Parent.add(list_qixingcai.get(i));
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Parent;
	}
	public static java.util.Date getDate(String strTime){
		
		java.util.Date date=null;
	
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:MM:SS");  
		    try {
				 date = sdf.parse(strTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return (Date) date;
		
	}
	
	
	public static String getorderID(){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	    String strtime=dateFormat.format(time);
		return strtime;
		
	}
	
	
	public static Timestamp syscreateTimeChange(String time){
		
		Timestamp ts=Timestamp.valueOf(time);
	    
		return ts;
		
	}
	
	
	
    public  static String randomLine(int size){
    	
    	
    	  int max=31;
          int min=1;
          Random random = new Random();
          
          StringBuilder randimLine= new StringBuilder();
          
          for(int i=0;i<size;i++){
        	  int s = random.nextInt(max)%(max-min+1) + min;
        	  randimLine.append(s);
        	  randimLine.append(",");
          }
        
    	
    	return randimLine.substring(0, randimLine.length()-1);
    }
    
    
	public static boolean sendImg(String imgStr, String path) {
		BASE64Decoder decoder = new BASE64Decoder();

		try {
			// 解密

			byte[] b = decoder.decodeBuffer(imgStr);

			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formateDate(Date date, String format) {
		if (date == null)
			return "";
		String ds = new SimpleDateFormat(format).format(date);
		return ds;
	}
	
	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String str) {
		return null == str || str.length() == 0 || "".equals(str)
				|| str.matches("\\s*");
	}
	
	/**
	 * 非空判断
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
}
