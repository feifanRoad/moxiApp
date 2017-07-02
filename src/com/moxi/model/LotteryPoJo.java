package com.moxi.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 各彩种开奖结果实体<BR>
 * LotteryPoJo<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午8:39:19
 * @version 1.0.0
 *
 */
public class LotteryPoJo implements Serializable {

	private static final long serialVersionUID = 1L;
	// 期号
	private String qihao;
	// 抓取信息时间
	private String dateline;
	// 中奖号码
	private String number;
	// 彩票类型
	private String type;
	// 创建时间
	private Timestamp createtime;
	// ？
	private Timestamp syscreatetime;
	// 是否推送
	private Integer isNoti;

	// getter/setter
	public String getQihao() {
		return qihao;
	}

	public void setQihao(String qihao) {
		this.qihao = qihao;
	}

	public String getDateline() {
		return dateline;
	}

	public void setDateline(String dateline) {
		this.dateline = dateline;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getSyscreatetime() {
		return syscreatetime;
	}

	public void setSyscreatetime(Timestamp syscreatetime) {
		this.syscreatetime = syscreatetime;
	}

	public Integer getIsNoti() {
		return isNoti;
	}

	public void setIsNoti(Integer isNoti) {
		this.isNoti = isNoti;
	}

}
