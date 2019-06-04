package com.xq.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.mapper.GuestMapper;
import com.xq.mapper.ManagerMapper;
import com.xq.pojo.Guest;
import com.xq.pojo.Manager;
import com.xq.pojo.User;
import com.xq.utils.ObjectMapperUtil;
import com.xq.utils.UserThreadLocal;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private Jedis jedis;
	
	@Autowired
	private ManagerMapper managerMapper;
	@Autowired
	private GuestMapper guestMapper;
	
	//定义一个全局的user变量
	private static User user;
	
	/*判断是否为物管人员*/
	private boolean isManager(User user) {
		QueryWrapper<Manager> qw1 = new QueryWrapper<>();
		qw1.eq("username", user.getUsername());
		Manager manager = managerMapper.selectOne(qw1);
		if(manager!=null) {
			String salt = manager.getSalt();
			SimpleHash hashpwd = new SimpleHash
					("md5", user.getPassword(), salt);
			if(hashpwd.toHex().equals(manager.getPassword())) {
				this.user = manager;
				return true;
			}
		}
		return false;
	}
	
	/*判断是否为住户人员*/
	private boolean isGuest(User user) {
		QueryWrapper<Guest> qw2 = new QueryWrapper<>();
		qw2.eq("username", user.getUsername());
		Guest guest = guestMapper.selectOne(qw2);
		if(guest!=null) {
			String salt = guest.getSalt();
			SimpleHash hashpwd = new SimpleHash
					("md5", user.getPassword(), salt);
			if(hashpwd.toHex().equals(guest.getPassword())) {
				this.user = guest;
				return true;
			}
		}
		return false;
	}
	
	/*对用户输入的信息进行判断并返回角色*/
	@Override
	public String confirmUser(User user) {
		if(isManager(user)) {
			return "物管";
		}
		else {
			if(isGuest(user))
				return "住户";
		}
		throw new RuntimeException("没有该用户");
	}
	
	/**
	 * 实现单点登录操作
	 */
	@Override
	public String getToken() {
		user.setPassword("123456");
		String userJson = ObjectMapperUtil.toJSON(user);
		String token = "XQ_TICKET_"+System.currentTimeMillis()+user.getUsername();
		token = DigestUtils.md5DigestAsHex(token.getBytes());
//		JedisCluster jedis = redisConfig.getCluster();
		//把user存进threadLocal中，以便后续操作
		UserThreadLocal.setUser(user);
		if(isGuest(user)) {
			//存入redis中
			jedis.setex("Guest"+token, 7*24*3600, userJson);
			return "Guest"+token;
		}
		jedis.setex("Manager"+token, 7*24*3600, userJson);
		return "Manager"+token;
	}
}
