package com.moxi.dao.discuss;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moxi.model.Discuss;

/**
 * 评论接口<BR>
 * IDiscussDao<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-下午1:27:23
 * @version 1.0.0
 *
 */
public interface IDiscussDao {

	/**
	 * 保存新闻评论<BR>
	 * 方法名：saveUser<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年6月30日-下午11:57:06
	 * 
	 * @return User
	 * @exception
	 * @since 1.0.0
	 */
	public void saveNewsDiscuss(Discuss discuss);
	
	/**
	 * 查询新闻对应所有评论接口<BR>
	 * 方法名：findAllDiscuss<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午4:39:32
	 * @return List<HashMap<Stirng,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> findAllDiscuss(@Param("newsId")Integer newsId);
	
}
