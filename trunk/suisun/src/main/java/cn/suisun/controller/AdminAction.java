package cn.suisun.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.suisun.beans.Industry;
import cn.suisun.beans.User;
import cn.suisun.service.IndustryService;
import cn.suisun.service.UserService;
import cn.suisun.utils.BaseAction;
import cn.suisun.utils.GlobalConstants;
import cn.suisun.utils.MD5Util;
import cn.suisun.utils.PropertiesUtils;
import cn.suisun.vos.AlbumUpdateVO;
import cn.suisun.vos.UserVo;

@Controller
@RequestMapping({ "/u/adminAction.htm" })
public class AdminAction extends BaseAction {

	private static final int pageSize = 10;
	@Resource
	UserService userService;

	@Resource
	IndustryService industryService;

	@RequestMapping(params = { "method=admin" }, method = RequestMethod.GET)
	public String forwardAdmin(ModelMap map) {
        map.put("user", getUser());	
        List<User> list = null;
        int pageAll = 0;
        if(getUser().getPower() == 0){
        	list = userService.getPageUser(1, pageSize, "3", "");  //超级管理员查看全部
        	pageAll = (int) Math.ceil((double) (userService.getAountUser("3",
    				"") / pageSize));
        	map.put("power", 3);
        } else if(getUser().getPower() == 1){
        	list = userService.getPageUser(1, pageSize, "2", "");  //普通管理员只看用户
        	pageAll = (int) Math.ceil((double) (userService.getAountUser("2",
    				"") / pageSize));
        }
		map.put("userList", list);
		map.put("pageAll", pageAll);
		map.put("currentPage", 1);
		map.put("pageSize", pageSize);
		map.put("search", "");
		return "/admin/main";
	}
	
	@RequestMapping(params = { "method=page" }, method = RequestMethod.GET)
	public String page(@RequestParam("currentPage") String currentPage,
			@RequestParam("power") String power,
			@RequestParam("search") String search, ModelMap map) {
		if (StringUtils.isEmpty(currentPage)) {
			return msg(-1, "");
		}
		List<User> list = userService.getPageUser(
				Integer.parseInt(currentPage), pageSize, power, search);
		int pageAll = (int) Math.ceil((double) userService.getAountUser(power,
				search) / pageSize);
		
		map.put("pageAll", pageAll);
		map.put("currentPage", currentPage);
		map.put("pageSize", pageSize);
		map.put("userList", list);
		map.put("power", power);
		map.put("search", search);
		return "/admin/main";
	}

	@RequestMapping(params = { "method=delUser" }, method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam("uid") String uid) {
		User user = userService.getUserByUid(uid);
		if (StringUtils.isEmpty(user)) {
			return msg(-1, "删除失败..");
		}
		userService.deleteUser(user);
		return msg(1, "");
	}

	@RequestMapping(params = { "method=forwardAddUser" }, method = RequestMethod.GET)
	public String forwardAddUser(ModelMap map) {
		List<Industry> list = industryService.getIndustry();
		map.put("user", getUser());
		map.put("inList", list);
		return "/admin/user_add";
	}
	
	@RequestMapping(params = { "method=isExitAccount" }, method = RequestMethod.GET)
	@ResponseBody
	public String isExitAccount(@RequestParam("account")String account){
		User u = userService.getUserByAccount(account);
		if(StringUtils.isEmpty(u)){
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping(params = { "method=addUser" }, method = RequestMethod.POST)
	public String addUser(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "logo", required = false) MultipartFile logo,
			@ModelAttribute("userVo") UserVo userVo, ModelMap map) {
		//String orfileName = logo.getOriginalFilename();
		//System.out.println("fileName:"+orfileName);
        //String fileName = PropertiesUtils.getProperty(CONFIG_NAME, "picUrl");
		
		System.out.println("-------->userVo:"+userVo.toString());
		String fileName = "";
		
		if(!logo.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("hc-img");
			StringBuffer s = new StringBuffer(String.valueOf(new Date().getTime())).append(".jpg");
			fileName = s.toString();
			File temp = new File(path, fileName);
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(logo.getInputStream());
				out = new BufferedOutputStream(new FileOutputStream(temp));
				int t = 0;
				byte[] b = new byte[1024];
				while((t = in.read(b)) != -1){
					out.write(b, 0, t);
				}
			} catch (IOException e) {
				e.printStackTrace();
				fileName = "";
				map.put("code", -1);
			} finally{
				try {
					out.close();
					in.close();
				} catch (IOException e) {
					fileName ="";
					e.printStackTrace();
				}
			}
		}
		
		User user = new User();
		user.setAccount(userVo.getAccount());
		user.setAddress(userVo.getAddress());
		user.setEmail(userVo.getEmail());
		user.setEnglishLinkMan(userVo.getEnglishLinkMan());
		user.setEnterpriseEnglish(userVo.getEnterpriseEnglish());
		user.setEnterpriseInfo(userVo.getEnterpriseInfo());
		user.setEnterpriseName(userVo.getEnterpriseName());
		user.setFax(userVo.getFax());
		user.setIndustryId(userVo.getIndustry());
		user.setLinkman(userVo.getLinkman());
		user.setLogoUrl(fileName);
		user.setPassword(MD5Util.string2MD5(userVo.getPassword()));
		user.setPhone(userVo.getPhone());
		user.setPower(userVo.getPower());
		user.setRegistDate(new Date());
		user.setShortEnglish(userVo.getShortEnglish());
		user.setShortName(userVo.getShortName());
		user.setTelephone(userVo.getTelephone());
		user.setWebsite(userVo.getWebsite());
		if(StringUtils.isEmpty(userService.add(user))){
			map.put("code", -1);
		} else {
			map.put("code", 1);
		}

		return "admin/tip";
	}
	
	@RequestMapping(params={"method=editUser"}, method=RequestMethod.GET)
	public String forwardEdit(@RequestParam("id")String id, ModelMap map){
		map.put("user", getUser());
		User user = userService.getUserByUid(id);
		
		if(!StringUtils.isEmpty(user.getLogoUrl())){
			StringBuffer fileName = new StringBuffer(PropertiesUtils.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH));
			String pic = fileName.append(user.getLogoUrl()).toString();
			user.setLogoUrl(pic);
		}
		List<Industry> list = industryService.getIndustry();
		map.put("inList", list);
		map.put("u", user);
		return "admin/user_edit";
	}
	
	
	@RequestMapping(params = { "method=editUser" }, method = RequestMethod.POST)
	public String editUser(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "logo", required = false) MultipartFile logo,
			@ModelAttribute("userVo") UserVo userVo, ModelMap map) {
		String fileName = "";
		User user = userService.getUserByUid(userVo.getUuid());
		if(!logo.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("hc-img");
			StringBuffer s = new StringBuffer(String.valueOf(new Date().getTime())).append(".jpg");
			fileName = s.toString();
			File temp = new File(path, fileName);
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(logo.getInputStream());
				out = new BufferedOutputStream(new FileOutputStream(temp));
				int t = 0;
				byte[] b = new byte[1024];
				while((t = in.read(b)) != -1){
					out.write(b, 0, t);
				}
				if(!StringUtils.isEmpty(user.getLogoUrl())){
					File delImg = new File(path,user.getLogoUrl());
					if(delImg.exists()){
						delImg.delete();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				fileName = "";
				map.put("code", -1);
			} finally{
				try {
					out.close();
					in.close();
				} catch (IOException e) {
					fileName ="";
					e.printStackTrace();
				}
			}
		}
		
		user.setAddress(userVo.getAddress());
		user.setEmail(userVo.getEmail());
		user.setEnglishLinkMan(userVo.getEnglishLinkMan());
		user.setEnterpriseEnglish(userVo.getEnterpriseEnglish());
		user.setEnterpriseInfo(userVo.getEnterpriseInfo());
		user.setEnterpriseName(userVo.getEnterpriseName());
		user.setFax(userVo.getFax());
		user.setIndustryId(userVo.getIndustry());
		user.setLinkman(userVo.getLinkman());
		user.setLogoUrl(fileName);
		if(!StringUtils.isEmpty(userVo.getPassword())){
			user.setPassword(MD5Util.string2MD5(userVo.getPassword()));			
		}
		user.setPhone(userVo.getPhone());
		user.setPower(userVo.getPower());
		user.setShortEnglish(userVo.getShortEnglish());
		user.setShortName(userVo.getShortName());
		user.setTelephone(userVo.getTelephone());
		user.setWebsite(userVo.getWebsite());
		userService.update(user);
		map.put("id", userVo.getUuid());
		map.put("code", 2);
		setAttribute(GlobalConstants.SESSION_USER, user);
		return "admin/tip";
	}
	
	//----------------普通用户
	
	@RequestMapping(params={"method=forwardUserMain"}, method=RequestMethod.GET)
	public String forwardUserMain(ModelMap map){
		User user = getUser();
		map.put("user",user);
		Industry industry = industryService.getIndustryById(user.getIndustryId());
		if(!StringUtils.isEmpty(industry)){
			map.put("industryName", industry.getIndustryName());
		}
		return "admin/user_main";
	}
	
	@RequestMapping(params="method=getPassword",method=RequestMethod.POST)
	@ResponseBody
	public String getPassword(@RequestParam("password")String passwrod){
		String pas = getUser().getPassword();
		String md5 = MD5Util.convertMD5(pas);
		if(passwrod.equals(md5)){
			return "true";
		} else {
			return "false";
		}
	}
	
	@RequestMapping(params={"method=forwardRecommend"}, method=RequestMethod.GET)
	public String forwardRecommend(ModelMap map){
		
		return "admin/recommend";
	}

}
