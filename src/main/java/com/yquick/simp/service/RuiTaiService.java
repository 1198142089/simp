package com.yquick.simp.service;

import com.alibaba.fastjson.JSONObject;

public interface RuiTaiService {
	String getAuth(JSONObject data) throws Exception;

	String getInfo(JSONObject data) throws Exception;

	Object sendEmail(JSONObject data);
}
