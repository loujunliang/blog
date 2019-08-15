package com.hcjava.util;

import java.io.Serializable;

/**
 * 用来封装后台处理的结果集
 * 
 * @author huachuang
 *
 */
public class NoteResult implements Serializable {

	// 0表示登录成功，其他表示失败
	private Integer status;
	// 消息
	private String msg;
	// 封装返回的数据
	private Object data;

	public NoteResult() {
		super();
	}

	public NoteResult(Integer status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

}
