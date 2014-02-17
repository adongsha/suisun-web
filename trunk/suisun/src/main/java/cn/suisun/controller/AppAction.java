package cn.suisun.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.suisun.beans.AppUpdate;
import cn.suisun.service.AppUpdateService;

@SuppressWarnings("all")
@Controller
@RequestMapping({ "/u/appAction.htm" })
public class AppAction {

	@Resource
	AppUpdateService appUpdateService ;

	@RequestMapping(params = { "method=forwardApp" }, method = RequestMethod.GET)
	public String forwardApp(ModelMap map) {
		map.put("apps", this.appUpdateService.getAllApp()) ;
		return "/admin/app_update_list";
	}
	
	@RequestMapping(params = { "method=addApp" }, method = RequestMethod.POST)
	public String addApp(@ModelAttribute("AppUpdate") AppUpdate app,ModelMap map) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;
		app.setCreateTime(format.format(new Date())) ;
		this.appUpdateService.save(app) ;
		map.put("flag", "success") ;
		return "/admin/app_update_add";
	}
	
	@RequestMapping(params = { "method=forwardAddApp" }, method = RequestMethod.GET)
	public String forwardAddApp(ModelMap map) {
		map.put("AppUpdate", new AppUpdate()) ;
		return "/admin/app_update_add";
	}
	
	@RequestMapping(params = { "method=forwardUpdateApp" }, method = RequestMethod.GET)
	public String forwardUpdateApp(@RequestParam("uuid") String uuid,ModelMap map) {
		map.put("AppUpdate", this.appUpdateService.getAppById(uuid)) ;
		return "/admin/app_update_update";
	}
	
	@RequestMapping(params = { "method=updateApp" }, method = RequestMethod.POST)
	public String updateApp(@ModelAttribute("AppUpdate") AppUpdate app,ModelMap map) {
		this.appUpdateService.update(app) ;
		map.put("flag", "success") ;
		return "/admin/app_update_update";
	}
	
	@RequestMapping(params = { "method=deleteApp" }, method = RequestMethod.POST)
	public void deleteApp(HttpServletResponse response,@RequestParam("uuid") String uuid,ModelMap map) {
		this.appUpdateService.deleteApp(uuid) ;
		try {
			response.getWriter().write("") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// APP下载
	@RequestMapping(params = { "method=download" }, method = RequestMethod.GET)  
	public void download(HttpServletRequest request,HttpServletResponse res,@RequestParam("url") String url,@RequestParam("version") String version) throws IOException {
		// 文件路径
		String filePath = request.getRealPath("") + "/" + url ;
		// APP名称
		String appName = "suisun" + version + filePath.substring(filePath.lastIndexOf("."), filePath.length()) ;
	    OutputStream os = res.getOutputStream();  
	    try {  
	        res.reset();  
	        res.setHeader("Content-Disposition", "attachment; filename=" + appName);  
	        res.setContentType("application/octet-stream; charset=utf-8");  
	        os.write(FileUtils.readFileToByteArray(new File(filePath)));  
	        os.flush();  
	    } catch (Exception e) {
	    	
		} finally {  
	        if (os != null) {  
	            os.close();  
	        }  
	    }  
	}  
}
