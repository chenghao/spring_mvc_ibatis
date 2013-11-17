package com.lovo.service;

import java.util.List;

import com.lovo.exception.ServiceException;
import com.lovo.pojo.User;

public interface IUserService {

	/**
	 * 注册
	 * @param user
	 * @return
	 * @throws ServiceException 
	 */
	int register(User user) throws ServiceException;
	
	/**
	 * 登录
	 */
	User login(User user);
	
	/**
	 * 查询
	 * @return
	 */
	List<User> queryUser(User user);
	
	/**
	 * 查询总行数
	 * @param userName
	 * @return
	 */
	int queryRowTotal(String userName);
	
	/**
	 * 保存SessionId
	 * @param loginName
	 * @param sessionId
	 * @throws ServiceException 
	 */
	void saveSessionId(String loginName, String sessionId) throws ServiceException;
	
	/**
	 * 删除SessionId
	 * @param loginName
	 * @throws ServiceException 
	 */
	void delSessionId(String loginName) throws ServiceException;
	/**
	 * 是否可以自动登录
	 * @param loginName
	 * @param sessionId
	 * @return
	 */
	User queryUserAutoLogin(String loginName, String sessionId);
}
