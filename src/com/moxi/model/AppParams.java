package com.moxi.model;

/**
 * 额外参数实体<BR>
 * AppParams<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-下午1:39:41
 * @version 1.0.0
 *
 */
public class AppParams {

	private Integer userId;
	private Integer newsId;
	private Integer pageNo;
	private Integer pageSize;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
