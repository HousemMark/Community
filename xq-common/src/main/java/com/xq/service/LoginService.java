package com.xq.service;

import com.xq.pojo.Guest;
import com.xq.pojo.Manager;
import com.xq.pojo.User;

public interface LoginService {

	public String confirmUser(User user);

	public String getToken();

	public Guest findGuestByName(String username);

	public Manager findManagerByName(String username);

}
