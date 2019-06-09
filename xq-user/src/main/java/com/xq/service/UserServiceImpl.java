package com.xq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.xq.exception.ServiceException;
import com.xq.mapper.UserMapper;
import com.xq.pojo.Guest;
import com.xq.pojo.Manager;
import com.xq.utils.PageUtil;
import com.xq.vo.PageObject;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;

	@Override
	public PageObject<Guest> findGuests(String username, Integer pageCurrent) {
		//合法性判断
		if(pageCurrent == null)
			throw new ServiceException("页面有误");
		Integer pageSize = 20;
		Integer startIndex = (pageCurrent-1)*pageSize;
		Integer rowCount = userMapper.getGuestRowCounts(username);
		if(rowCount == null)
			throw new ServiceException("无搜索信息");
		//查询信息列表
		List<Guest> records = userMapper.findGuests(username, pageSize, startIndex);
		return PageUtil.newInstance(pageCurrent, rowCount, pageSize, records);
	}

	@Override
	public PageObject<Manager> findManagers(String username, Integer pageCurrent) {
		//合法性判断
		if(pageCurrent == null)
			throw new ServiceException("页面有误");
		Integer pageSize = 20;
		Integer startIndex = (pageCurrent-1)*pageSize;
		Integer rowCount = userMapper.getManagerRowCounts(username);
		if(rowCount == null)
			throw new ServiceException("无搜索信息");
		//查询信息列表
		List<Manager> records = userMapper.findManagers(username, pageSize, startIndex);
		return PageUtil.newInstance(pageCurrent, rowCount, pageSize, records);
	}
}
