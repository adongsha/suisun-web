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
	
	// 画册封面上传
	@RequestMapping(params = "method=uploadAlbum", method = RequestMethod.POST)
	public void uploadAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 照片上传
		String path = UploadHelper.uploadAlbum(request) ;
		response.getWriter().write(path) ;
	}
	
	// 画册图片上传
	@RequestMapping(params = "method=uploadAlbumPic", method = RequestMethod.POST)
	public void uploadAlbumPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 照片上传
		String path = UploadHelper.uploadAlbumPic(request) ;
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
