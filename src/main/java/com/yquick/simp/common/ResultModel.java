package com.yquick.simp.common;

import java.io.Serializable;

/**
 *
 * 文件名称： com.bootdo.speech.common.ResultModel.java<br/>
 * 初始作者： mishaoqi<br/>
 * 创建日期： 2018年8月31日<br/>
 * 功能说明： 返回数据封装类 <br/>
 *
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 *
 *
 * ================================================<br/>
 * Copyright (c) 2010-2011 .All rights reserved.<br/>
 */
public class ResultModel<T> implements Serializable {

	private static final long serialVersionUID = 7251433606219732795L;
	private int code = 0;
	private String msg = "请求成功";
	private T result;
	private Long execTime = 0L;
	private int count = 0;
	private String url;
	private boolean flag;

	public int getCode() {

		return code;
	}

	public void setCode(int errCode) {

		this.code = errCode;
	}

	public String getMsg() {

		return msg;
	}

	public void setMsg(String msg) {

		this.msg = msg == null ? "" : msg.trim();
	}

	public T getResult() {

		return result;
	}

	public void setResult(T result) {

		this.result = result;
	}

	public Long getExecTime() {

		return execTime;
	}

	public void setExecTime(Long execTime) {

		this.execTime = execTime == null ? 0 : execTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ResultModel [code=" + code + ", msg=" + msg + ", result=" + result + ", execTime=" + execTime
				+ ", count=" + count + ", url=" + url + ", flag=" + flag + "]";
	}

}
