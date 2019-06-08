package com.xq.service;

import com.xq.pojo.Guest;
import com.xq.pojo.Manager;

public interface PwdService {

	Integer updatedGuestPwd(
			Guest guest, String pwd, 
			String newPwd, String cfgPwd);

	Integer updatedManagerPwd(
			Manager manager, String pwd, 
			String newPwd, String cfgPwd);
}
