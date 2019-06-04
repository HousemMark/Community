package com.xq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xq.pojo.BaseFee;

public interface FeeMapper extends BaseMapper<BaseFee>{

	List<BaseFee> findObjects(
			@Param("time") String time, 
			@Param("startIndex") Integer startIndex, 
			@Param("pageSize") Integer pageSize);

	Integer getRowCounts(@Param("time") String time);

}
