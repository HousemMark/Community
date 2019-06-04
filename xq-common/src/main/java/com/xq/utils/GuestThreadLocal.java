package com.xq.utils;

import com.xq.pojo.Guest;

public class GuestThreadLocal {

	private static ThreadLocal<Guest> guestLocalThread = new ThreadLocal<>();

	public static void setUser(Guest guest) {
		guestLocalThread.set(guest);
	}

	public static Guest getUser() {
		return guestLocalThread.get();
	}
	
	public static void remove() {
		guestLocalThread.remove();
	}
}
