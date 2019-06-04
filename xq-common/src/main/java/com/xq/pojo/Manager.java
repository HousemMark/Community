package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName("xq_manager")
public class Manager extends User{
	private static final long serialVersionUID = -5417876919143573620L;
	private Long jobId;
}
