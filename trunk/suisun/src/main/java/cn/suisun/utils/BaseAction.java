package cn.suisun.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import cn.suisun.beans.User;

@Component
@Controller
public class BaseAction extends SimpleFormController {

	@Resource
	HttpServletRequest request;

	public User getUser() {
		return (User) (request.getSession()
				.getAttribute(GlobalConstants.SESSION_USER));
	}

	public String getUserId() {
		return ((User) (request.getSession()
				.getAttribute(GlobalConstants.SESSION_USER))).getUuid();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getParameter(String paramName, String defaultValue) {
		String param = getRequest().getParameter(paramName);
		if (param == null) {
			return param = defaultValue;
		}
		return param;
	}

	public int getParameter(String paramName, int defaultValue) {
		String param = getRequest().getParameter(paramName);
		try {
			return Integer.parseInt(param);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	public void setAttribute(String paramName, Object object) {
		getRequest().getSession().setAttribute(paramName, object);
	}

	public Object getAttribute(String paramName, Object defaultValue) {
		Object object = getRequest().getSession().getAttribute(paramName);
		if (object == null) {
			return defaultValue;
		}
		return object;
	}

	public String msg(int code, String msg) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		return json.toString();
	}

	public String msg(int code) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		return json.toString();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}
