package cn.suisun.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.suisun.beans.User;
import cn.suisun.service.UserService;
import cn.suisun.utils.BaseAction;
import cn.suisun.utils.GlobalConstants;

@Controller
@RequestMapping({ "/loginAction.htm" })
public class LoginAction extends BaseAction{
	
	@Resource
	UserService userService;
	
	@RequestMapping(params={"method=forwardLogin"}, method = RequestMethod.GET)
	public String forwardLogin(){
		return "/login";
	}

	@RequestMapping(method = RequestMethod.POST, params = {"method=login"})
	@ResponseBody
	public String login(@ModelAttribute("User") User user, ModelMap map){
		//user.setPassword(MD5Util.string2MD5(user.getPassword()));
		User u = userService.Login(user);
		if(u != null){
			setAttribute(GlobalConstants.SESSION_USER, u);
			u.setLastTime(new Date());
			userService.update(u);
			if(u.getPower() == 2){
				return msg(2);  //普通用户
			}
			return msg(1,"");
		} else {
		    return msg(-1, "用户名或密码错误..");
		}
		
	}
	
	@RequestMapping(params={"method=outLogin"}, method=RequestMethod.GET)
	public String outLogin(){
		setAttribute(GlobalConstants.SESSION_USER, "");
		System.out.println("注销成功..");
		return "redirect:loginAction.htm?method=forwardLogin";
	}
	

}
