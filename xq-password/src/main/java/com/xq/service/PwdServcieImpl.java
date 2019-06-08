package com.xq.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.xq.exception.ServiceException;
import com.xq.mapper.GuestMapper;
import com.xq.mapper.ManagerMapper;
import com.xq.pojo.Guest;
import com.xq.pojo.Manager;
import com.xq.pojo.User;

@Service
public class PwdServcieImpl implements PwdService{

	@Autowired
	private GuestMapper guestMapper;

	@Autowired
	private ManagerMapper managerMapper;

	//住户修改密码业务
	@Override
	public Integer updatedGuestPwd(Guest guest, String pwd, String newPwd, String cfgPwd) {
		//合法性判断
		if(!judge(guest, pwd, newPwd, cfgPwd))
			throw new ServiceException("验证错误");
		//根据guest的id查找密码，进行比对
		Guest realGuest = guestMapper.selectById(guest.getId());
		String salt = realGuest.getSalt();
		//对输入的密码全部加盐操作
		String spwd = new SimpleHash("md5", pwd, salt, 1).toHex();
		if(!spwd.equals(realGuest.getPassword()))
			throw new ServiceException("请重新输入密码");
		String sNewPwd = new SimpleHash("md5", newPwd, salt, 1).toHex();
		//插入进行更新操作
		realGuest.setPassword(sNewPwd);
		int res = guestMapper.updateById(realGuest);
		return res;
	}
	
	//管理员进行更新密码业务
	@Override
	public Integer updatedManagerPwd(Manager manager, String pwd, String newPwd, String cfgPwd) {
		//合法性判断
		if(!judge(manager, pwd, newPwd, cfgPwd))
			throw new ServiceException("验证错误");
		//根据guest的id查找密码，进行比对
		Manager realManager = managerMapper.selectById(manager.getId());
		String salt = realManager.getSalt();
		//对输入的密码全部加盐操作
		String spwd = new SimpleHash("md5", pwd, salt, 1).toHex();
		if(!spwd.equals(realManager.getPassword()))
			throw new ServiceException("请重新输入密码");
		String sNewPwd = new SimpleHash("md5", newPwd, salt, 1).toHex();
		//插入进行更新操作
		realManager.setPassword(sNewPwd);
		int res = managerMapper.updateById(realManager);
		return res;
	}

	private boolean judge(User user, String pwd, String newPwd, String cfgPwd) {
		//合法性判断
		if(pwd == null || newPwd == null || cfgPwd == null)
			throw new ServiceException("输入不能为空");
		if(user == null)
			throw new ServiceException("用户信息为空");
		if(!newPwd.equals(cfgPwd))
			throw new ServiceException("请重新输入确认密码");
		return true;
	}
}
