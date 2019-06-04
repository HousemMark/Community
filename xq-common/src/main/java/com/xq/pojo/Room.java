package com.xq.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName("xq_room")
public class Room implements Serializable{
	private static final long serialVersionUID = 3794778091616414707L;
	private Long id;
	private String name;
	private Integer building;
	private Integer floor;
	private Integer room;
}
