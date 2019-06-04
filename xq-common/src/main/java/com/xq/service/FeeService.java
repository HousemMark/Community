package com.xq.service;

import com.xq.pojo.BaseFee;
import com.xq.pojo.Room;
import com.xq.vo.WebResult;

public interface FeeService {

	WebResult findObjects(Integer pageCurrent, String time);

	public Integer saveObject(BaseFee fee, Room room);

	Integer deleteObject(Integer id);

}
