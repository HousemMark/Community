package com.xq.service;

import com.xq.pojo.User;

public interface LoginService {

	public String confirmUser(User user);

	public String getToken();

}
