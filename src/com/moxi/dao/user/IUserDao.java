package com.moxi.dao.user;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.moxi.model.PhoneCode;
import com.moxi.model.Token;
import com.moxi.model.User;

/**
 * 用户接口<BR>
 * IUserDao<BR>
 * 创建人:feifan<BR>
 * 时间：2017年6月30日-下午11:11:51
 * 
 * @version 1.0.0
 * 
 */
public interface IUserDao {

	/**
	 * check用户是否存在<BR>
	 * 方法名：getUser<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午1:28:20
	 * 
	 * @param phoneNumber
	 * @param password
	 * @return User
	 * @exception
	 * @since 1.0.0
	 */
	public User checkUserPhoneNumberExsit(@Param("phoneNumber") String phoneNumber);
	
	/**
	 * 根据userID查询用户信息<BR>
	 * 方法名：checkUserByUserId<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-下午5:05:45
	 * @param userId
	 * @return User
	 * @exception 
	 * @since  1.0.0
	 */
	public User checkUserByUserId(@Param("userId") Integer userId);

	/**
	 * 保存用户接口<BR>
	 * 方法名：saveUser<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年6月30日-下午11:57:06
	 * 
	 * @return User
	 * @exception
	 * @since 1.0.0
	 */
	public void saveUser(User user);
	
	/**
	 * 更新用户信息<BR>
	 * 方法名：updateUserinfo<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午11:44:19
	 * @param userId
	 * @param isLogin void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateUserinfo(User user);
	
	/**
	 * 保存token<BR>
	 * 方法名：saveToken<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午10:41:29
	 * @param token void
	 * @exception 
	 * @since  1.0.0
	 */
	public void saveToken(Token token);
	
	/**
	 * 查询token信息<BR>
	 * 方法名：getTokenInfo<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午11:30:11
	 * @param userId
	 * @return Token
	 * @exception 
	 * @since  1.0.0
	 */
	public Token getTokenInfo(@Param("userId")Integer userId);

	/**
	 * 更新token信息<BR>
	 * 方法名：updateTokenInfo<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午11:35:41
	 * @param userId
	 * @param token
	 * @return Token
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateTokenInfo(@Param("userId")Integer userId, @Param("token")String token);
	
	/**
	 * 保存验证码接口<BR>
	 * 方法名：savePhoneSendCode<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午12:15:37
	 * 
	 * @param phonecode
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	public void savePhoneSendCode(PhoneCode phonecode);
	
	/**
	 * 检查验证码是否存在接口<BR>
	 * 方法名：checkCode<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午9:56:45
	 * @param phonecode
	 * @return PhoneCode
	 * @exception 
	 * @since  1.0.0
	 */
	public PhoneCode checkCode(PhoneCode phonecode);
	
	/**
	 * 检查用户名是否存在<BR>
	 * 方法名：checkUserNameExsit<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午10:10:45
	 * @param user
	 * @return User
	 * @exception 
	 * @since  1.0.0
	 */
	public User checkUserNameExsit(User user);
	
	/**
	 * 拿到注册信息<BR>
	 * 方法名：getRegisterInfo<BR>
	 * 创建人：feifan <BR>
	 * 时间：2017年7月2日-上午11:29:28
	 * @param userId
	 * @return HashMap<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public HashMap<String, Object> getRegisterInfo(@Param("userId")Integer userId);
	
}
