package cn.suisun.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 项目名称：微信运营平台 
 * 类名称：UploadHelper 
 * 类描述：文件上传工具类 
 * 创建者：严植培 
 * 创建时间：2013-6-9 下午07:31:43
 */
@SuppressWarnings("all")
public class UploadHelper {

	/** 文件上传 **/
	public static String uploadFile(HttpServletRequest request) throws Exception{
		String responseStr = "";
		String imagePath = "" ;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile file11 = multipartRequest.getFile("Filedata");
		String ctxPath = request.getRealPath("") + "/ui/upload/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		ctxPath += ymd + "/";
		imagePath = "ui/upload/" + ymd  + "/";
		// 创建文件夹
		File file = new File(ctxPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		String path = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件名
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String strEnc = MD5.getDigestedString(fileName);// 加密字符串,返回String的密文
			String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
			String suffix = fileName.indexOf(".") != -1 ? fileName.substring(
					fileName.lastIndexOf("."), fileName.length()) : null;
			String newFileName = strEnc + "-" + uuid
					+ (suffix != null ? suffix : "");// 构成新文件名。
			File uploadFile = new File(ctxPath + newFileName);
			imagePath += newFileName ;
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				path = ctxPath + newFileName;
				responseStr = "上传成功";
			} catch (IOException e) {
				responseStr = "上传失败";
				e.printStackTrace();
			}
		}
		System.out.println("图片上传地址 : " + imagePath);
		return imagePath;
	}

}