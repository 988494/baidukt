package com.bdkt.core.utils;

import org.apache.http.HttpStatus;

public class ReturnEntity<Entity> {
	
	private int code;
	
	private String msg;
	
	private Entity data;
	
	public ReturnEntity() {
		this.code = 0;
		this.msg = "success";
	}

	public ReturnEntity(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ReturnEntity(Entity entity)
	{
		this();
		this.data = entity;
	}
	
	public ReturnEntity<Entity> ok(Entity entity) {
        this.data = entity;
		return this;
	}
	
	public ReturnEntity ok() {
		return this;
	}
	
	/**
	 * 程序异常
	 * @param msg
	 * @return
	 */
	public ReturnEntity error(String msg) {
		return new ReturnEntity(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	/**
	 * 业务逻辑错误
	 * @param msg
	 * @return
	 */
	public ReturnEntity errorLogic(String msg) {
		return new ReturnEntity(1, msg);
	}
	
	public ReturnEntity<Entity> put(Entity entity) {
		this.data=entity;
		return this;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Entity getData() {
		return data;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(Entity data) {
		this.data = data;
	}
	
}
