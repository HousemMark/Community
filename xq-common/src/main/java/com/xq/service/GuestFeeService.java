package com.xq.service;

import com.xq.pojo.BaseFee;
import com.xq.pojo.Guest;
import com.xq.vo.WebResult;

public interface GuestFeeService {

	WebResult findObjectsByUser(Guest guest, Integer pageCurrent, String time);

	BaseFee findObjectByid(Long id);

	Integer updatedObject(String fee_id, String fee_type, String fee_sum);

}
