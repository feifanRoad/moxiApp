package com.moxi.web.discuss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moxi.dao.discuss.IDiscussDao;
import com.moxi.model.Discuss;
import com.moxi.util.TmStringUtils;

/**
 * 评论接口<BR>
 * DiscussController<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-下午1:12:39
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("/discuss")
public class DiscussController {
	
	@Autowired
	private IDiscussDao discussDao;

	
	/**
	 * 添加新闻评论接口<BR>
	 * 方法名：discuss<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午1:33:05
	 * @param request
	 * @param response
	 * @return Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/addDiscuss")
	@ResponseBody
	public Object discuss(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String newsId = request.getParameter("newsId");
			String content = request.getParameter("content");
			String userId = request.getParameter("userId");

			if (TmStringUtils.isEmpty(newsId)) {
				result.put("result", "");
				result.put("error", "新闻id不能为空");
				result.put("errorcode", "1");
				return result;
			}

			if (TmStringUtils.isEmpty(content)) {
				result.put("result", "");
				result.put("error", "评论内容不能为空!");
				result.put("errorcode", "2");
				return result;
			}

			if (TmStringUtils.isEmpty(userId)) {
				result.put("result", "");
				result.put("error", "用户id不能为空!");
				result.put("errorcode", "3");
				return result;
			}

			Discuss discuss = new Discuss();
			
			Integer newsid = Integer.parseInt(newsId);
			Integer userid = Integer.parseInt(userId);
			
			discuss.setNewsId(newsid);
			discuss.setContent(content);
			discuss.setUserId(userid);
			// 保存评论
			discussDao.saveNewsDiscuss(discuss);
			result.put("result", "");
			result.put("error", "评论成功");
			result.put("errorcode", "0");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.put("result", "");
			result.put("error", "数据库异常");
			result.put("errorcode", "4");
			return result;
		}
		return result;
	}
	
	
	/**
	 * <BR>
	 * 方法名：findAllDiscuss<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午4:41:21
	 * @param request
	 * @param response
	 * @return Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/findAllDiscuss")
	@ResponseBody
	public Object findAllDiscuss(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String newsId = request.getParameter("newsId");
		try {
			if (TmStringUtils.isEmpty(newsId)) {
				result.put("result", "");
				result.put("error", "新闻id不能为空");
				result.put("errorcode", "1");
				return result;
			}
			// 加载评论
			List<HashMap<String, Object>> discussList = discussDao.findAllDiscuss(Integer.parseInt(newsId));
			result.put("result", discussList);
			result.put("error", "");
			result.put("errorcode", "0");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.put("result", "");
			result.put("error", "数据库异常");
			result.put("errorcode", "4");
			return result;
		}
		return result;
	}
	
	
	
}
