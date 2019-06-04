package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName("xq_salary_fee")
public class SalaryFee extends BasePojo{
	private static final long serialVersionUID = -9214203729936930558L;
	private Long id;
	private String type;
	private Double sum;
	private Integer deptId;
	private String status;
}
