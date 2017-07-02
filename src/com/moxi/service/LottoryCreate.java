package com.moxi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moxi.dao.lottery.ILotteryDao;
import com.moxi.model.LotteryPoJo;
import com.moxi.util.SendMassageUtil;
import com.moxi.util.Utils;

import net.sf.json.JSONObject;

//*/5 * * * * ? 五秒执行一次
@Component
public class LottoryCreate {

	@Autowired
	private ILotteryDao lotteryDao;

	protected static final Logger logger = LoggerFactory
			.getLogger(LottoryCreate.class);

	// 每天的0点、13点、18点点都执行一次( 0 0 0,13,18 * * )
	@Scheduled(cron = "0 0 0,13,18 * * ?")
	public void task_shuangseqiu() {
		String lottoryType = "shuangseqiu";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 1.双色球
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);

			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}
			if (content != null && !content.equals("")) {
				List<LotteryPoJo> list = Utils.manipulation(content,
						"shuangseqiu", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("shuangseqiu_test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data,
						"shuangseqiu", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【双色球】定时任务刷新数据");
		System.out.println("zhixingle=======");
	}

	// 3D 每日开奖 0 0 0,9,22 * * ?
	@Scheduled(cron = "0 0 0,9,22 * * ?")
	public void task_3D() {
		String lottoryType = "3D";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 2.3D
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);

			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}
			if (content != null && !content.equals("")) {
				List<LotteryPoJo> list = Utils.manipulation(content, "3D", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("3dTestData");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data, "3D", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【3D】定时任务刷新数据");
	}

	// 3D试机号 每次开奖 ====0 0 3,18,23 * * ?
	// 每周:7 期,开奖星期:每日（每天开奖一次）
	@Scheduled(cron = "0 0 3,18,23 * * ?")
	public void task_3Dshijihao() {
		String lottoryType = "3Dshijihao";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 3.3Dshijihao
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);

			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}
			if (content != null && !content.equals("")) {
				List<LotteryPoJo> list = Utils.manipulation(content,
						"3Dshijihao", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("3Dshijihao_test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data,
						"3Dshijihao", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【3Dshijihao】定时任务刷新数据");
	}

	// 七乐彩 每周:3 期,开奖星期:二、四、日 0 0 8,17,19 * * ?
	@Scheduled(cron = "0 0 8,17,19 * * ?")
	public void task_qilecai() {
		String lottoryType = "qilecai";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 4.qilecai
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);

			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}
			if (content != null && !content.equals("")) {
				List<LotteryPoJo> list = Utils.manipulation(content, "qilecai",
						9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("qilecai_Test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data,
						"qilecai", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【七乐彩】定时任务刷新数据");
	}

	// 5.daletou 每周:3 期,开奖星期:一、三、六
	@Scheduled(cron = "0 0 8,18,20 * * ?")
	// 0 0 8,18,20 * * ?
	public void task_dalexiu() {
		String lottoryType = "daletou";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 4.daletou
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);

			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}

			if (content != null && !content.equals("")) {
				List<LotteryPoJo> list = Utils.manipulation(content, "daletou",
						9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("daletou_Test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data,
						"daletou", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【大乐透】定时任务刷新数据");
	}

	// 6.pailiesan 每周:7 期,开奖星期:每日
	@Scheduled(cron = "0 0 3,7,23 * * ?")
	// 0 0 3,7,23 * * ?
	public void task_pailiesan() {
		String lottoryType = "pailiesan";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 6.pailiesan
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);

			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}

			if (content != null && !content.equals("")) {
				List<LotteryPoJo> list = Utils.manipulation(content,
						"pailiesan", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("pailiesan_Test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data,
						"pailiesan", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【排列三】定时任务刷新数据");
	}

	// 每周:7 期,开奖星期:每日(每天一次开奖)
	@Scheduled(cron = "0 0 4,6,11 * * ?")
	// 0 0 4,6,11 * * ?
	public void task_pailiewu() {
		String lottoryType = "pailiewu";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 7.pailiewu
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);
			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}

			if (content != null && !content.equals("")) {
				List<LotteryPoJo> list = Utils.manipulation(content,
						"pailiewu", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("pailiewu_Test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data,
						"pailiewu", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【排列五】定时任务刷新数据");
	}

	// 每周:3 期,开奖星期:二、五、日
	@Scheduled(cron = "0 0 3,10,13 * * ?")
	// 0 0 3,10,13 * * ?
	public void task_qixingcai() {
		String lottoryType = "qixingcai";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 8.qixingcai
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);
			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}

			if (content != null && !content.equals("")) {
				List<LotteryPoJo> list = Utils.manipulation(content,
						"qixingcai", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("qixingcai_Test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data,
						"qixingcai", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【七星彩】定时任务刷新数据");
	}

	// 重庆时时彩
	@Scheduled(cron = "0 0 1,5,16 * * ?")
	// 0 0 3,10,13 * * ? ----*/5 * * * * ?
	public void chongqing() {
		String lottoryType = "chongqing_shishi";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 10.
																		// 重庆时时彩
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);

			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}

			if (content != null && !content.equals("") && retunCode != null
					&& !retunCode.equals("403")) {
				List<LotteryPoJo> list = Utils.manipulation(content,
						"chongqing_shishi", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils
						.readProperryFile("chongqing_shishi_Test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data,
						"chongqing_shishi", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【重庆时时彩】定时任务刷新数据");
	}

	// 安徽11选5
	@Scheduled(cron = "0 0 4,14,17 * * ?")
	// 0 0 4,14,17 * * ?
	public void anhui() {
		String lottoryType = "anhui";
		String shuangseqiu_url = Utils.readProperryFile(lottoryType);// 11.安徽11选5
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("type", lottoryType);
			lotteryDao.deleteLotteryRecord(map);// 以前保存的数据删除
			String content = SendMassageUtil.HTTPPOST(shuangseqiu_url);
			JSONObject obj = new JSONObject().fromObject(content); // 返回的里面的code
			JSONObject objsum = (JSONObject) obj.get("status");
			String retunCode = null;
			if (objsum != null) {
				retunCode = (String) objsum.get("code");// 返回的里面的code
			}

			if (content != null && !content.equals("") && retunCode != null
					&& !retunCode.equals("403")) {
				List<LotteryPoJo> list = Utils
						.manipulation(content, "anhui", 9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			} else {
				String test_data = Utils.readProperryFile("anhui_Test");// 获取url
				List<LotteryPoJo> list = Utils.manipulation(test_data, "anhui",
						9);
				// 数据保存
				for (int i = 0; i < list.size(); i++) {
					LotteryPoJo bean = list.get(i);
					bean.setSyscreatetime(Utils.syscreateTimeChange(bean
							.getDateline()));
					lotteryDao.saveLotteryPoJo(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(Utils.getStrTime(Utils.getTimestampTime())
				+ "================执行了【安徽11选5】定时任务刷新数据");
	}
}
