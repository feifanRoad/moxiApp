package com.moxi.dao.Thumbs;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moxi.model.Discuss;
import com.moxi.model.NewsThumbs;

/**
 * 点赞接口<BR>
 * IThumbsDao<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-下午5:10:47
 * @version 1.0.0
 *
 */
public interface IThumbsDao {

	/**
	 * 保存点赞信息<BR>
	 * 方法名：saveNewsDiscuss<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午5:11:33
	 * @param discuss void
	 * @exception 
	 * @since  1.0.0
	 */
	public void saveNewsThumbs(NewsThumbs newsThumbs);
	
	
}
