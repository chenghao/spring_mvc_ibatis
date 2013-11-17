package com.lovo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovo.dao.ICommonDao;
import com.lovo.exception.ServiceException;
import com.lovo.pojo.User;
import com.lovo.service.IUserService;
import com.lovo.utils.Crypto;

@Service
public class UserService implements IUserService {

	private static String namespace = "user.";

	@Autowired
	private ICommonDao<User> commonDao;

	@Override
	public int register(User user) throws ServiceException {
		User u = commonDao.findByField(namespace + "queryUserByLoginName", user.getLoginName());
		if (u == null) {
			try {
				user.setPassword(Crypto.MD5_SHA(user.getPassword()));
				return commonDao.insert(namespace + "register", user);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		} else {
			return -1;
		}
	}

	@Override
	public User login(User user) {
		user.setPassword(Crypto.MD5_SHA(user.getPassword()));
		User u = commonDao.findByEntity(namespace + "login", user);
		return u;
	}

	@Override
	public List<User> queryUser(User user) {
		List<User> users = commonDao.findByEntityList(namespace + "queryUser", user);
		return users;
	}

	@Override
	public int queryRowTotal(String userName) {
		return (Integer) commonDao.findByFieldObject(namespace + "queryRowTotal", userName);
	}

	@Override
	public void saveSessionId(String loginName, String sessionId) throws ServiceException {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("loginName", loginName);
		map.put("sessionId", sessionId);

		try {
			commonDao.updateByMap(namespace + "saveSessionId", map);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delSessionId(String loginName) throws ServiceException {
		try {
			commonDao.updateByField(namespace + "delSessionId", loginName);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User queryUserAutoLogin(String loginName, String sessionId) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("loginName", loginName);
		map.put("sessionId", sessionId);

		return commonDao.findByMap(namespace + "queryUserAutoLogin", map);

	}

}
