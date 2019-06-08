package com.xq.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.exception.ServiceException;
import com.xq.mapper.FeeMapper;
import com.xq.mapper.RoomMapper;
import com.xq.pojo.BaseFee;
import com.xq.pojo.Room;
import com.xq.utils.PageUtil;
import com.xq.vo.PageObject;
import com.xq.vo.WebResult;

@Service
public class ManagerFeeServiceImpl implements FeeService {

	@Autowired
	private FeeMapper feeMapper;

	@Autowired
	private RoomMapper roomMapper;
	
	/*物业查所有缴费记录*/
	@Override
	public WebResult findObjects(Integer pageCurrent,String time) {
		Integer pageSize = 10;
		Integer startIndex = (pageCurrent-1)*pageSize;
		List<BaseFee> fee = feeMapper.findAllObjects(time,startIndex,pageSize);
		Integer rowCount = feeMapper.getRowCounts(time);
		PageObject<BaseFee> obj = PageUtil.newInstance(pageCurrent,rowCount,pageSize,fee);
		return WebResult.ok(obj);
	}

	@Override
	@Transactional
	public Integer saveObject(BaseFee fee,Room room) {
		// 新增费用账单
		//根据栋数/房间号查出roomId
		QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", room.getName());
		queryWrapper.eq("building", room.getBuilding());
		queryWrapper.eq("room", room.getRoom());
		Room realRoom = roomMapper.selectOne(queryWrapper);
		if(realRoom == null)
			throw new ServiceException("输入住户有误");
		fee.setCreatedTime(new Date()).setUpdatedTime(new Date());
		fee.setRoomId(realRoom.getId());
		int row = feeMapper.insert(fee);
		return row;
	}

	@Override
	public Integer deleteObject(Integer id) {
		// 删除账单操作
		int row = feeMapper.deleteById(id);
		return row;
	}

}
