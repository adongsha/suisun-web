package cn.suisun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.suisun.utils.UploadHelper;

// 照片上传
@Controller
@RequestMapping({ "/u/uploadAction.htm" })
public class UploadAction {

	// 照片上传
	@RequestMapping(params = "method=upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 照片上传
		String path = UploadHelper.uploadFile(request) ;
		response.getWriter().write(path) ;
	}
	
	// APP上传
	@RequestMapping(params = "method=uploadApp", method = RequestMethod.POST)
	public void uploadApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// APP上传
		String path = UploadHelper.uploadApp(request) ;
		response.getWriter().write(path) ;
	}
}
