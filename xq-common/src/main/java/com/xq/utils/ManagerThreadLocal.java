package com.xq.utils;

import com.xq.pojo.Manager;

public class ManagerThreadLocal {

	private static ThreadLocal<Manager> managerLocalThread = new ThreadLocal<>();

	public static void setUser(Manager manager) {
		managerLocalThread.set(manager);
	}

	public static Manager getUser() {
		return managerLocalThread.get();
	}
	
	public static void remove() {
		managerLocalThread.remove();
	}
}
