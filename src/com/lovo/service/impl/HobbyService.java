package com.lovo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovo.dao.ICommonDao;
import com.lovo.exception.ServiceException;
import com.lovo.pojo.Hobby;
import com.lovo.service.IHobbyService;
import com.lovo.utils.PageUtil;

@Service
public class HobbyService implements IHobbyService {
	private static String namespace = "hobby.";

	@Autowired
	private ICommonDao<Hobby> commonDao;

	@Override
	public List<Hobby> queryAllHobby(int userId, Integer pageNo, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("userId", userId);
		map.put("pageNo", PageUtil.getCurrentRow(pageNo));
		map.put("pageSize", pageSize);

		return commonDao.findByMapList(namespace + "queryAllHobby", map);
	}

	@Override
	public int queryRowTotal() {
		return (Integer) commonDao.findByFieldObject(namespace + "queryRowTotal", null);
	}

	@Override
	public void deleteHobby(int pid) throws ServiceException {
		try {
			commonDao.deleteByField(namespace + "deleteHobby", pid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
