package com.kj.commdityinfo.security.utils;

import java.util.HashMap;
import java.util.Map;

import com.zhenzi.sms.ZhenziSmsClient;

public class ZhenziSmsUtils {
	//域名
	private static final String apiUrl = "https://sms_developer.zhenzikj.com";
	//应用名称
	private static final String appId = "106082";
	//密钥
	private static final String appSecret = "NTk3YTVkYTctYWQ3OS00NzUwLThjYjYtYTE1M2RlNGM0ZWFi";
	//短信模板
	private static final String templateId = "682";
	//有效时间
	private static final String time = "1分钟";
	
	private static ZhenziSmsClient zhenziSmsClient = null;
	
	private static ZhenziSmsClient getClient() {
		if(zhenziSmsClient == null) {
			zhenziSmsClient = new ZhenziSmsClient(apiUrl, appId, appSecret);
		}
		return zhenziSmsClient;
	}
	
	public static String sendSMS(String telephone, String code){
		Map<String, Object> params = new HashMap<String, Object>();
		String[] templateParams = new String[] {code,time};
		params.put("number", telephone);
		params.put("templateId", templateId);
		params.put("templateParams", templateParams);
		String result = null;
		try {
			result = getClient().send(params);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public static String sendMessage(String telephone, String code){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("number", telephone);
		String message = "物品比价网 当前验证码" + code + ",该验证码" + time + "有效。";
		params.put("message", message);
		params.put("templateId", templateId);
		String result = null;
		try {
			result = getClient().send(params);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
