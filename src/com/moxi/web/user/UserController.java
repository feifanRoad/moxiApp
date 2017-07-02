package com.moxi.web.user;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moxi.dao.user.IUserDao;
import com.moxi.model.PhoneCode;
import com.moxi.model.Token;
import com.moxi.model.User;
import com.moxi.util.MD5Util;
import com.moxi.util.SendMassageUtil;
import com.moxi.util.TmStringUtils;
import com.moxi.util.Utils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserDao userDao;
	
	/**
	 * 获取验证码<BR>
	 * 方法名：sendCode<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午12:08:38
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException Object
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping("/sendCode")
	public Object sendCode(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();

		String phoneNumber = request.getParameter("phoneNumber");
		try {
			if (TmStringUtils.isEmpty(phoneNumber)) {
				result.put("result", "");
				result.put("error", "手机号码不能为空");
				result.put("errorcode", "103");
				return result;
			}
			//拿到随机数,生成验证码
			StringBuilder randomNumber = Utils.createRandomNumber();
			//发送验证码
			SendMassageUtil.post(phoneNumber, randomNumber);
			//验证码model
			PhoneCode phonecode = new PhoneCode();
			//给model注入电话号码和验证码
			phonecode.setPhonenumber(phoneNumber);
			phonecode.setCode(randomNumber.toString());
			//设置创建时间然后注入到model中
			Timestamp newTime = Utils.getTimestampTime();
			phonecode.setCreatetime(newTime);
			//设置当前时间(用于计算验证码的当前时间)
			String strTime = Utils.getStrTime(newTime);
			phonecode.setStrTime(strTime);
			//保存验证码
			userDao.savePhoneSendCode(phonecode);
			//返回注册信息
			result.put("result", phonecode);
			result.put("error", "");
			result.put("errorcode", "0");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.put("result", "");
			result.put("error", "短信发送异常");
			result.put("errorcode", "201");
		}
		return result;
	}
	
	/**
	 * 用户注册接口<BR>
	 * 方法名：sendPositionDetail<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午8:08:40
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException Object
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping("/register") 
	public Object register(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		// 手机号码
		String phoneNumber = request.getParameter("phoneNumber");
		//拿到验证码
		String code = request.getParameter("code");
		//获取验证码生成的时间
		String codeTime = request.getParameter("codeTime");
		// 用户名
		String username = request.getParameter("username");
		// 用户密码
		String password = request.getParameter("password");
		
		User user = new User();
		User checkUser = new User();
		try {
			if (Utils.isNotEmpty(code) && Utils.isNotEmpty(codeTime) && Utils.isNotEmpty(phoneNumber) && Utils.isNotEmpty(username) && Utils.isNotEmpty(password)) {
				PhoneCode phonecode = new PhoneCode();
				// 拿到验证码生成时间,转为timestamp格式
				Timestamp codeTimestamp = Utils.changeStrTimeToTimeStamp(codeTime);
				//当前时间减去验证码生成时间
				long timeDifference = Utils.getTimestampTime().getTime() - codeTimestamp.getTime();
				// 如果时间长于三分钟
				if (timeDifference > 180000) {
					result.put("result", "");
					result.put("error", "验证码已超时，请在三分钟之内完成验证");
					result.put("errorcode", "100");
					return result;
				}
				
				phonecode.setPhonenumber(phoneNumber);
				phonecode.setCode(code);
				phonecode.setCreatetime(codeTimestamp);
				//检查验证码是否已存在
				PhoneCode codeBean = userDao.checkCode(phonecode);
				//没有找到验证码
				if (codeBean == null) {
					result.put("result", "");
					result.put("error", "验证码不正确");
					result.put("errorcode", "101");
					return result;
				}
				//密码加密
				String MD5userpwd = MD5Util.encode(password);
				
				//开始保存用户信息
				user.setPhoneNumber(phoneNumber);
				user.setUsername(username);
				user.setPassword(MD5userpwd);
				//新用户注册送100积分
				user.setScore(100);
				user.setImgPath(null);
				user.setUserSign(null);
				user.setIsLogin(0);
				// 用户存电话号码的check
				checkUser = userDao.checkUserPhoneNumberExsit(phoneNumber);
				if (checkUser != null) {
					result.put("result", "");
					result.put("error", "该手机号码已经注册");
					result.put("errorcode", "3");
					return result;
				}
				//检查用户名是否存在
				checkUser = userDao.checkUserNameExsit(user);
				if (checkUser != null) {
					result.put("result", "");
					result.put("error", "该用户名已存在");
					result.put("errorcode", "4");
					return result;
				}
				// 存储用户数据
				userDao.saveUser(user);
				checkUser = userDao.checkUserNameExsit(user);
				//拿到当前用户的ID
				Integer userId = checkUser.getId();
				
				//一个随机的token
				String token = TmStringUtils.getRandomString(20);
				
				Token token2 = new Token();
				token2.setPhoneNumber(phoneNumber);
				token2.setToken(token);
				token2.setUserId(userId);
				//保存token
				userDao.saveToken(token2);
				
				HashMap<String, Object> map = userDao.getRegisterInfo(userId);
				
				//返回用户注册信息
				result.put("result", map);
				result.put("error", "注册成功");
				result.put("errorcode", "0");
			} else {
				result.put("result", "");
				result.put("error", "参数不能为空");
				result.put("errorcode", "5");
				return result;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result.put("result", "");
			result.put("error", "数据库操作异常");
			result.put("errorcode", "6");
			return result;
		}
		return result;
	}
	
	/**
	 * 用户登录<BR>
	 * 方法名：login<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午11:09:46
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException Object
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Object login(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();

		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		
		User upuser = new User();
		try {
			if (TmStringUtils.isNotEmpty(phoneNumber) && TmStringUtils.isNotEmpty(password)) {
				password = MD5Util.encode(password);
				User user = new User();
				user.setPhoneNumber(phoneNumber);
				user.setPassword(password);
				//查询用户
				user = userDao.checkUserPhoneNumberExsit(phoneNumber);
				if (user == null) {
					result.put("result", "");
					result.put("error", "用户不存在");
					result.put("errorcode", "1");
					return result;
				}
				if (user != null) {
					if (!password.equals(user.getPassword())) {
						result.put("result", "");
						result.put("error", "密码错误");
						result.put("errorcode", "2");
						return result;
					}else if(user.getIsLogin()==1){
						result.put("result", "");
						result.put("error", "用户已经登录");
						result.put("errorcode", "3");
						return result;
					}
					
					//拿到当前用户的ID
					Integer userId = user.getId();
					upuser.setId(userId);
					upuser.setIsLogin(1);
					if (TmStringUtils.isEmpty(user.getUserSign())) {
						upuser.setUserSign("该用户还没有签名");
					}
					userDao.updateUserinfo(upuser);
					
					Token tokenModel = userDao.getTokenInfo(userId);
					String token = tokenModel.getToken();
					if (TmStringUtils.isNotEmpty(token)) {
						//生成一个随机的token
						String newToken = TmStringUtils.getRandomString(20);
						userDao.updateTokenInfo(userId, newToken);
					}
					
					HashMap<String, Object> map = userDao.getRegisterInfo(userId);
					
					result.put("result", map);
					result.put("error", "");
					result.put("errorcode", "0");
				}
			} else {
				result.put("result", "");
				result.put("error", "用户名密码不能空");
				result.put("errorcode", "3");
				return result;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result.put("result", "");
			result.put("error", "数据库操作异常");
			result.put("errorcode", "4");
			return result;
		}
		return result;
	}
	
	/**
	 * 退出登陆<BR>
	 * 方法名：loginout<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午12:26:05
	 * @param request
	 * @param response
	 * @return Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Object loginout(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		//电话号码
		String phoneNumber = request.getParameter("phoneNumber");
		if (TmStringUtils.isEmpty(phoneNumber)) {
			result.put("result", "");
			result.put("error", "电话号码不能为空");
			result.put("errorcode", "1");
		}
		User user = userDao.checkUserPhoneNumberExsit(phoneNumber);
		if (user == null) {
			result.put("result", "");
			result.put("error", "用户不存在");
			result.put("errorcode", "1");
			return result;
		}
		
		User logoutUser = new User();
		logoutUser.setId(user.getId());
		logoutUser.setIsLogin(0);
		userDao.updateUserinfo(logoutUser);
		result.put("result", "");
		result.put("error", "退出成功");
		result.put("errorcode", "0");
		return result;
	}
	
	/**
	 * 忘记密码<BR>
	 * 方法名：updateUserPwd<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午12:29:39
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException Object
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping("/forgetPwd")
	public Object updateUserPwd(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		
		String code = request.getParameter("code"); // 验证码
		String codeTime = request.getParameter("codeTime");// 验证码生成时间
		String phoneNumber = request.getParameter("phoneNumber");// 手机号码
		String password = request.getParameter("password");// 用户密码

		User user = new User();
		User checkUser = new User();
		try {
			if (Utils.isNotEmpty(code) && Utils.isNotEmpty(codeTime) && Utils.isNotEmpty(phoneNumber) && Utils.isNotEmpty(password)) {
				// 拿到验证码生成时间
				Timestamp codeTimestamp = Utils.changeStrTimeToTimeStamp(codeTime);
				long timeDifference = Utils.getTimestampTime().getTime() - codeTimestamp.getTime();

				if (timeDifference > 180000) {// 时间长于三分钟
					result.put("result", "");
					result.put("error", "验证码过时，三分钟之内操作");
					result.put("errorcode", "1");
					return result;
				}

				PhoneCode phonecode = new PhoneCode();
				phonecode.setCode(code);
				phonecode.setPhonenumber(phoneNumber);
				phonecode.setCreatetime(codeTimestamp);
				//检查验证码对错
				PhoneCode codeBean = userDao.checkCode(phonecode);
				if (codeBean == null) {// 验证码出错
					result.put("result", "");
					result.put("error", "验证码不正确");
					result.put("errorcode", "2");
					return result;
				}
				//拿到新密码,然后加密
				String MD5password = MD5Util.encode(password);
				user.setPhoneNumber(phoneNumber);
				// 用户验证
				checkUser = userDao.checkUserPhoneNumberExsit(phoneNumber);
				if (checkUser == null) {
					result.put("result", "");
					result.put("error", "用户不存在");
					result.put("errorcode", "3");
					return result;
				}
				
				User upUser = new User();
				upUser.setId(checkUser.getId());
				upUser.setPassword(MD5password);
				// 修改用户密码
				userDao.updateUserinfo(upUser);
				//密码清空
				result.put("result", "");
				result.put("error", "密码修改成功");
				result.put("errorcode", "0");

			} else {
				result.put("result", "");
				result.put("error", "参数不能为空");
				result.put("errorcode", "5");
				return result;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result.put("result", "");
			result.put("error", "数据库操作异常");
			result.put("errorcode", "5");
			return result;
		}
		return result;
	}
	
	
	
}
