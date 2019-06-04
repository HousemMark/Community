package com.xq.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/*单例模式创建对象*/
@Data
@Accessors(chain=true)
public class WebResult implements Serializable{
	private static final long serialVersionUID = -8028016556098435759L;
	private Integer status=1;
	private String message="ok!";
	private Object data;
	
	private WebResult() {
	}
	private WebResult(Integer status,String message,Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * 为了满足用户调用需求，重载方法
	 */
	public static WebResult ok() {
		return new WebResult(1,"调用方法成功",null);
	}
	public static WebResult ok(Object data) {
		return new WebResult(1,"调用方法成功",data);
	}
	public static WebResult ok(String message ,Object data) {
		return new WebResult(1,message,data);
	}
	public static WebResult fail() {
		return new WebResult(0,"调用方法成功",null);
	}
	public static WebResult fail(Object data) {
		return new WebResult(0,"调用方法成功",data);
	}
	public static WebResult fail(String message ,Object data) {
		return new WebResult(0,message,data);
	}
}
