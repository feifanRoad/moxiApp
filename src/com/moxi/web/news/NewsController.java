package com.moxi.web.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moxi.dao.Thumbs.IThumbsDao;
import com.moxi.dao.discuss.IDiscussDao;
import com.moxi.dao.news.INewsDao;
import com.moxi.dao.user.IUserDao;
import com.moxi.model.AppParams;
import com.moxi.model.Discuss;
import com.moxi.model.News;
import com.moxi.model.NewsThumbs;
import com.moxi.model.Token;
import com.moxi.model.User;
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
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private INewsDao newsDao;

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IThumbsDao thumbsDao;
	
	
	/**
	 * 资讯一览接口<BR>
	 * 方法名：newsList<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午1:33:05
	 * @param request
	 * @param response
	 * @return Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/newsList")
	@ResponseBody
	public Object newsList(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		AppParams appParams = new AppParams();
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		try {

			if (TmStringUtils.isEmpty(pageNo)) {
				result.put("result", "");
				result.put("error", "分页起始参数不能为空");
				result.put("errorcode", "1");
				return result;
			}

			if (TmStringUtils.isEmpty(pageSize)) {
				result.put("result", "");
				result.put("error", "分页条数不能为空!");
				result.put("errorcode", "2");
				return result;
			}
			
			appParams.setPageNo(Integer.parseInt(pageNo));
			appParams.setPageSize(Integer.parseInt(pageSize));

			List<HashMap<String, Object>> newsList = newsDao.findNews(appParams);
			result.put("result", newsList);
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
	
	
	/**
	 * 资讯详情接口<BR>
	 * 方法名：newsDetail<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午3:12:54
	 * @param request
	 * @param response
	 * @return Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/newsDetail")
	@ResponseBody
	public Object newsDetail(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String newsId = request.getParameter("newsId");
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		try {

			if (TmStringUtils.isEmpty(newsId)) {
				result.put("result", "");
				result.put("error", "资讯id不能为空");
				result.put("errorcode", "1");
				return result;
			}

			if (TmStringUtils.isEmpty(userId)) {
				result.put("result", "");
				result.put("error", "用户id不能为空!");
				result.put("errorcode", "2");
				return result;
			}
			
			if (TmStringUtils.isEmpty(token)) {
				result.put("result", "");
				result.put("error", "用户token不能为空!");
				result.put("errorcode", "3");
				return result;
			}
			
			//拿到token信息
			Token tokenInfo = userDao.getTokenInfo(Integer.parseInt(userId));
			if (tokenInfo!=null) {
				if (tokenInfo.getToken().equals(token)) {
					
					//获取新闻信息
					HashMap<String, Object> newsDetail = newsDao.getNews(Integer.parseInt(newsId));
					
					//判断是否点赞
					NewsThumbs newsThumbs = newsDao.getNewsThumbs(Integer.parseInt(userId), Integer.parseInt(newsId));
					
					if (newsThumbs==null) {
						//没点赞
						newsDetail.put("isHit", "0");
					}else {
						newsDetail.put("isHit", "1");
					}
					
					result.put("result", newsDetail);
					result.put("error", "");
					result.put("errorcode", "0");
					
				}else {
					result.put("result", "");
					result.put("error", "token不正确");
					result.put("errorcode", "4");
					return result;
				}
			}else {
				result.put("result", "");
				result.put("error", "该用户token为空");
				result.put("errorcode", "5");
				return result;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result.put("result", "");
			result.put("error", "数据库异常");
			result.put("errorcode", "6");
			return result;
		}
		return result;
	}
	
	/**
	 * 点赞接口<BR>
	 * 方法名：givethumbs<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午5:00:29
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException Object
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping("/givethumbs")
	public Object givethumbs(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		
		String userId = request.getParameter("userId");
		String newsId = request.getParameter("newsId");
		try {
			User user= new User();
			
			if(TmStringUtils.isEmpty(userId)){
				result.put("result", "");
				result.put("error", "电话号码不能为空");
				result.put("errorcode", "1");
				return result;
			}
			
			if(TmStringUtils.isEmpty(newsId)){
				result.put("result", "");
				result.put("error", "forumid不能为空");
				result.put("errorcode", "2");
				return result;
			}
			//查询用户是否存在
			user=userDao.checkUserByUserId(Integer.parseInt(userId));
			
			if(user==null){
				result.put("result", "");
				result.put("error", "用户不存在");
				result.put("errorcode", "3");
				return result;
			}
			
			NewsThumbs  newsThumbs= new NewsThumbs();
			
			newsThumbs.setUserId(Integer.parseInt(userId));
			newsThumbs.setNewsId(Integer.parseInt(newsId));
			
			//检查是否已经点赞
			NewsThumbs  thumbs = newsDao.getNewsThumbs(Integer.parseInt(userId), Integer.parseInt(newsId));
			
			if(thumbs!=null){
				result.put("result", "");
				result.put("error", "该新闻已经被点赞了");
				result.put("errorcode", "4");
				return result;	
			}
			//保存点赞信息
			thumbsDao.saveNewsThumbs(newsThumbs);
			
			//获取新闻信息
		    News news = newsDao.getNewsById(Integer.parseInt(newsId));
		    
		    if (news!=null) {
		    	Integer totalThumbs = news.getTotalHit();
				Integer id = news.getId();
				News upNews = new News();
				upNews.setId(id);
				upNews.setTotalHit(totalThumbs+1);//点赞总数加1
				newsDao.updateNewsinfo(upNews);
				
				result.put("result", "");
				result.put("error", "点赞成功");
				result.put("errorcode", "0");
			}else {
				result.put("result", "");     
				result.put("error", "新闻为空");
				result.put("errorcode", "5");
				return result;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			result.put("result", "");     
			result.put("error", "数据操作异常");
			result.put("errorcode", "6");
			return result;
		}
		return result;
	}
}
