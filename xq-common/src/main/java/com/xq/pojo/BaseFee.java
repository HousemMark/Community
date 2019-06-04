package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName("xq_manage_fee")
public class BaseFee extends BasePojo{
	private static final long serialVersionUID = -9214203729936930558L;
	@TableId(type=IdType.AUTO)
	private Long id;
	private String type;
	private Double sum;
	private Long roomId;
	private String status = "未缴费";
}
