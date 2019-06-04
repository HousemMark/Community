package com.xq.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class User extends BasePojo{
	private static final long serialVersionUID = -3578745313853786454L;
	private Long id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String idCard;
	private String createdUser;
	private String updatedUser;
}
