package com.moxi.dao.news;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moxi.model.AppParams;
import com.moxi.model.News;
import com.moxi.model.NewsThumbs;

/**
 * 新闻接口<BR>
 * INewsDao<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午1:01:43
 * @version 1.0.0
 *
 */
public interface INewsDao {

	/**
	 * 查询新闻一览<BR>
	 * 方法名：findNews<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午3:27:54
	 * @param appParams
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> findNews(AppParams appParams);
	
	/**
	 * 根据新闻id查询单条新闻信息<BR>
	 * 方法名：getNews<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午3:27:32
	 * @param appParams
	 * @return HashMap<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public HashMap<String, Object> getNews(@Param("newsId")Integer newsId);
	
	/**
	 * 根据新闻id查询结果存入实体<BR>
	 * 方法名：getNewsById<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午5:29:29
	 * @param newsId
	 * @return News
	 * @exception 
	 * @since  1.0.0
	 */
	public News getNewsById(@Param("newsId")Integer newsId);
	
	/**
	 * 查询点赞信息<BR>
	 * 方法名：getNewsThumbs<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午3:44:24
	 * @param userId
	 * @param newsId
	 * @return NewsThumbs
	 * @exception 
	 * @since  1.0.0
	 */
	public NewsThumbs getNewsThumbs(@Param("userId")Integer userId, @Param("newsId")Integer newsId);
	
	/**
	 * 更新新闻信息<BR>
	 * 方法名：updateNewsinfo<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午5:38:02
	 * @param news void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateNewsinfo(News news);
	
}
