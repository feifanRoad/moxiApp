package com.moxi.dao.lottery;

import java.util.Map;

import com.moxi.model.LotteryPoJo;


/**
 * 彩票中奖信息接口<BR>
 * ILotteryDao<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月1日-下午10:55:15
 * @version 1.0.0
 *
 */
public interface ILotteryDao {

	/**
	 * 删除彩票信息<BR>
	 * 方法名：deleteRecord<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月1日-下午10:55:48
	 * @param map void
	 * @exception 
	 * @since  1.0.0
	 */
	void deleteLotteryRecord(Map<Object, Object> map);
	
	/**
	 * 保存彩票开奖信息<BR>
	 * 方法名：saveLottoryPoJo<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月1日-下午11:09:20
	 * @param bean void
	 * @exception 
	 * @since  1.0.0
	 */
	void saveLotteryPoJo(LotteryPoJo bean);
}
