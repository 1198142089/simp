package com.yquick.simp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yquick.simp.service.RuiTaiService;

@RestController
public class RuiTaiController {

	@Autowired
	private RuiTaiService ruiTaiService;

	@PostMapping("/getAuth")
	public String getAuth(@RequestBody JSONObject data) {
		String resultStr="";
		try {
			resultStr = ruiTaiService.getAuth(data);
		} catch (Exception e) {
			e.printStackTrace();
			resultStr="获取瑞泰验证手机号接口异常";
		}
		return resultStr;
	}

	@PostMapping("/getInfo")
	public String getInfo(@RequestBody JSONObject data) {
		String resultStr="";
		try {
			resultStr = ruiTaiService.getInfo(data);
		} catch (Exception e) {
			e.printStackTrace();
			resultStr="获取瑞泰保单信息接口异常";
		}
		return resultStr;
	}

	@PostMapping("/sendEmail")
	public Object sendEmail(@RequestBody JSONObject data) {
		return ruiTaiService.sendEmail(data);
	}

}
