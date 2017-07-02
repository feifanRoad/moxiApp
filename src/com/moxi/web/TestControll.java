package com.moxi.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.moxi.dao.profit.IProfitDao;

@Controller
public class TestControll {

//	@Autowired
//	private IProfitDao profitDao;
	
	/**
	 * 返回视图<BR>
	 * 方法名：index<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月1日-上午9:16:00
	 * @param request
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		String username = request.getParameter("username");
		System.out.println("进入spring的第一个方法");
		System.out.println(username);
		return "index";
	}
	
	/**
	 * 返回字符串<BR>
	 * 方法名：loadData<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月1日-上午9:15:50
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/loadData")
	@ResponseBody
	public String loadData(){
		return "大家好";
	}
	
	/**
	 * 返回map对象<BR>
	 * 方法名：loadProfits<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月1日-上午9:15:39
	 * @return List<HashMap<String,Object>>
	 * @throws JSONException 
	 * @exception 
	 * @since  1.0.0
	 */
//	@RequestMapping("/loadProfits")
//	@ResponseBody
//	public String loadProfits() throws JSONException{
//		HashMap<String, Object> map = new HashMap<String,Object>();
//		List<HashMap<String, Object>> lists = profitDao.findProfits();
//		map.put("result", lists);
//		return JSONUtil.serialize(map);
//	}
}
