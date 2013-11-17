package com.lovo.service;

import java.util.List;

import com.lovo.exception.ServiceException;
import com.lovo.pojo.Hobby;

public interface IHobbyService {

	List<Hobby> queryAllHobby(int userId, Integer pageNo, Integer pageSize);

	int queryRowTotal();

	void deleteHobby(int pid) throws ServiceException;
}
