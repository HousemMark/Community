package com.xq.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	
	//实现对象与JSON互转的ObjectMapper对象，final确保线程安全
		private static final ObjectMapper mapper = new ObjectMapper();
		
		public static String toJSON(Object target){
			//对象转化为JSON串
			String userJson;
			try {
				userJson = mapper.writeValueAsString(target);
				return userJson;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		//传入T类型，返回T类型
		public static <T> T getObject(String json, Class<T> targetClass){
			T target = null;
			try {
				target = mapper.readValue(json, targetClass);
				return target;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		//传入什么类型，返回都是Object
		public static Object toObject(String json, Class<?> targetClass){
			try {
				Object target = mapper.readValue(json, targetClass);
				return target;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
}
