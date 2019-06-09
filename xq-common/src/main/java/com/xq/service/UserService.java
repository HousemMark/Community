package com.xq.service;

import com.xq.pojo.Guest;
import com.xq.pojo.Manager;
import com.xq.vo.PageObject;

public interface UserService {

	PageObject<Guest> findGuests(String username, Integer pageCurrent);

	PageObject<Manager> findManagers(String username, Integer pageCurrent);

}
