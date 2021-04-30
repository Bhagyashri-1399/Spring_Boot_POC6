package com.junit5.poc6.dto;

import java.util.List;

public class ResultModel {
	
	private volatile String message;
	
	private volatile Object data;
	
	private volatile List<String> msgList;
	
	public synchronized String getMessage() {
		return message;
	}
	
	public synchronized void setMessage(String message) {
		this.message = message;
	}
	
	public synchronized Object getData() {
		return data;
	}
	
	public synchronized void setData(Object data) {
		this.data = data;
	}
	
	public synchronized List<String> getMsgList() {
		return msgList;
	}
	
	public synchronized void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}

}
