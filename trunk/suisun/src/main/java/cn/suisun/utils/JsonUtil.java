package cn.suisun.utils;

import net.sf.json.JSONObject;

public class JsonUtil {
	
	public static String msg(int code){
		JSONObject json = new JSONObject();
		json.put("code", code);
		return json.toString();
	}

}
