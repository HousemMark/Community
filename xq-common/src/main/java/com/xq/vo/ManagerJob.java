package com.xq.vo;

import com.xq.pojo.Job;
import com.xq.pojo.User;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class ManagerJob extends User{
	private static final long serialVersionUID = -8764382258002939712L;
	private Job job;
}
