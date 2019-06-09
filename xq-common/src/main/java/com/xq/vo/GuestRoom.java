package com.xq.vo;

import com.xq.pojo.Room;
import com.xq.pojo.User;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class GuestRoom extends User{
	private static final long serialVersionUID = 1746517317621073695L;
	private Room room;
}
