package com.xq.utils;

import com.xq.pojo.User;

public class UserThreadLocal {

	private static ThreadLocal<User> userLocalThread = new ThreadLocal<>();

	public static void setUser(User user) {
		userLocalThread.set(user);
	}

	public static User getUser() {
		return userLocalThread.get();
	}
	
	public static void remove() {
		userLocalThread.remove();
	}
}
