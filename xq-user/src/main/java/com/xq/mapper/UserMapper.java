package com.xq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xq.pojo.Guest;
import com.xq.pojo.Manager;

public interface UserMapper {

	Integer getGuestRowCounts(@Param("username")String username);

	List<Guest> findGuests(
			@Param("username")String username, 
			@Param("pageSize")Integer pageSize, 
			@Param("startIndex")Integer startIndex);

	Integer getManagerRowCounts(@Param("username")String username);

	List<Manager> findManagers(
			@Param("username")String username, 
			@Param("pageSize")Integer pageSize, 
			@Param("startIndex")Integer startIndex);
	
	
}
