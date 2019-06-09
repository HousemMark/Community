package com.xq.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName("xq_job")
public class Job implements Serializable{
	private static final long serialVersionUID = 5444120408047340808L;
	@TableId(type=IdType.AUTO)
	private Long id;
	private String name;
	private Long deptId;
	private Double salary;
}
