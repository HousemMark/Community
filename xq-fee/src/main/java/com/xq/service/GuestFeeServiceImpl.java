package com.xq.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.xq.mapper.FeeMapper;
import com.xq.pojo.BaseFee;
import com.xq.pojo.Guest;
import com.xq.utils.PageUtil;
import com.xq.vo.PageObject;
import com.xq.vo.WebResult;

@Service
public class GuestFeeServiceImpl implements GuestFeeService{
	@Autowired
	private FeeMapper feeMapper;
	
	@Override
	public WebResult findObjectsByUser(Guest guest, Integer pageCurrent, String time) {
		Integer pageSize = 10;
		Integer startIndex = (pageCurrent-1)*pageSize;
		Long roomId = guest.getRoomId();
		List<BaseFee> fee = feeMapper.findObjects(roomId,time,startIndex,pageSize);
		Integer rowCount = feeMapper.getRowCounts(time);
		PageObject<BaseFee> obj = PageUtil.newInstance(pageCurrent,rowCount,pageSize,fee);
		return WebResult.ok(obj);
	}

	@Override
	public BaseFee findObjectByid(Long id) {
		BaseFee fee = feeMapper.selectById(id);
		return fee;
	}

	@Override
	@Transactional
	public Integer updatedObject(String fee_id, String fee_type, String fee_sum) {
		BaseFee fee = new BaseFee();
		long feeId = Long.parseLong(fee_id);
		fee.setId(feeId).setStatus("已缴费").setUpdatedTime(new Date());
		int row = feeMapper.updateById(fee);
		return row;
	}
}
