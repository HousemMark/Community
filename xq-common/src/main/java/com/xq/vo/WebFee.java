package com.xq.vo;

import com.xq.pojo.BasePojo;
import com.xq.pojo.Room;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class WebFee extends BasePojo{
	private static final long serialVersionUID = -9214203729936930558L;
	private Integer id;
	private String type;
	private Double sum;
	private Room room;
	private String status;
}
