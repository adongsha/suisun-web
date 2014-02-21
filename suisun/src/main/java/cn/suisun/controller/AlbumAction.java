package cn.suisun.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.suisun.beans.Album;
import cn.suisun.beans.AlbumDirectory;
import cn.suisun.beans.AlbumPic;
import cn.suisun.beans.AlbumUpdate;
import cn.suisun.service.AlbumDirectoryService;
import cn.suisun.service.AlbumPicService;
import cn.suisun.service.AlbumService;
import cn.suisun.service.UserService;
import cn.suisun.utils.AlbumAlias;
import cn.suisun.utils.BaseAction;
import cn.suisun.utils.GlobalConstants;
import cn.suisun.utils.PropertiesBean;
import cn.suisun.vos.AlbumUpdateVO;

/**
 * @author pei
 * 画册管理控制器
 */
@Controller
@RequestMapping({ "/u/albumsAction.htm" })
public class AlbumAction extends BaseAction{
	
	// 画册管理接口
	@Resource
	private AlbumService albumService ;
	
	// 画册目录
	@Resource
	private AlbumDirectoryService directoryService ;
	
	// 画册图片接口
	@Resource
	private AlbumPicService picService ;
	
	// 用户接口
	@Resource
	private UserService userService ;
	
	@Resource
	PropertiesBean propertiesBean;

	// 显示画册列表
	@RequestMapping(params = { "method=showAlbums" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String showAlbums(@RequestParam("albumName") String albumName,@RequestParam("enterpriseName") String enterpriseName,ModelMap map) {
		// 获取画册信息
		List<Album> albums = this.albumService.getAlbumList(albumName, enterpriseName) ;
		
		// 超级管理员、管理员提供企业名称列表选择
		if(super.getUser().getPower() != 2){
			// 保存所有企业名称
			map.put("enterprises", this.userService.getAllEnterprise()) ;
		}
		
		if(albums != null && !albums.isEmpty()){
			String albumUrl = propertiesBean.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH) ;
			for(Album album : albums){
				album.setAlbumCover(albumUrl + album.getAlbumCover()) ;
				System.out.println(album.getAlbumCover()) ;
			}
		}
		
		// 保存信息
		map.put("albums", albums) ;
		map.put("albumName", albumName) ;
		map.put("enterpriseName", enterpriseName) ;
		return "/admin/album_list";
	}
	
	// 跳转至画册列表
	@RequestMapping(params = { "method=forwardAlbums" }, method = RequestMethod.GET)
	public String forwardAlbums(ModelMap map) {
		// 获取画册信息
		List<Album> albums = this.albumService.getAlbumListByUserId(super.getUserId()) ;
		
		// 超级管理员、管理员提供企业名称列表选择
		if(super.getUser().getPower() != 2){
			// 保存所有企业名称
			map.put("enterprises", this.userService.getAllEnterprise()) ;
		}
		
		if(albums != null && !albums.isEmpty()){
			String albumUrl = propertiesBean.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH) ;
			for(Album album : albums){
				album.setAlbumCover(albumUrl + album.getAlbumCover()) ;
			}
		}
		
		// 保存信息
		map.put("albums", albums) ;
		map.put("albumName", "") ;
		map.put("enterpriseName", "") ;
		return "/admin/album_list";
	}
	
	// 跳转至新增画册
	@RequestMapping(params = { "method=forwardAddAlbum" }, method = RequestMethod.GET)
	public String forwardAddAlbum(ModelMap map) {
		Album album = new Album() ;
		// 别名
		album.setAlbumAlias(AlbumAlias.getAlbumAlist()) ;
		
		// 保存信息
		map.put("album", album) ;
		return "admin/album_add";
	}
		
	// 新增画册
	@RequestMapping(params = { "method=addAlbum" }, method = RequestMethod.POST)
	public String addAlbum(@ModelAttribute("album") Album album,ModelMap map) {
		// 保存画册信息
		album.setUserId(super.getUserId()) ;
		album.setCreateTime(new Date()) ;
		this.albumService.Save(album) ;
		// 保存信息
		map.put("flag", "success") ;
		return "admin/album_add" ;
	}

	// 修改画册信息
	@RequestMapping(params = { "method=updateAlbum" }, method = RequestMethod.POST)
	public String updateAlbum(@ModelAttribute("album") Album album,ModelMap map) {
		// 保存画册信息
		album.setUserId(super.getUserId()) ;
		this.albumService.update(album) ;
		// 保存信息
		map.put("flag", "success") ;
		return "admin/album_update" ;
	}
		
	// 跳转至画册修改界面
	@RequestMapping(params = { "method=forwardUpdateAlbum" }, method = RequestMethod.GET)
	public String forwardUpdateAlbum(@RequestParam("uuid") String uuid,ModelMap map) {
		// 获取画册信息
		Album album = this.albumService.getAlbumById(uuid) ;
		String albumUrl = propertiesBean.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH) ;
		
		// 保存信息
		map.put("album", album) ;
		map.put("albumImg", albumUrl + album.getAlbumCover()) ;
		return "admin/album_update";
	}
	
	// 跳转至新增画册
	@RequestMapping(params = { "method=forwardAddDirectory" }, method = RequestMethod.GET)
	public String forwardAddDirectory(@RequestParam("albumId") String albumId,ModelMap map) {
		AlbumDirectory directory = new AlbumDirectory() ;
		directory.setAlbumId(albumId) ;
		// 保存信息
		map.put("directory", directory) ;
		return "admin/directory_add";
	}
	
	// 新增画册目录
	@RequestMapping(params = { "method=addDirectory" }, method = RequestMethod.POST)
	public String addDirectory(@ModelAttribute("directory") AlbumDirectory directory,ModelMap map) {
		// 保存画册目录信息
		this.directoryService.save(directory) ;
		// 保存信息
		map.put("flag", "success") ;
		return "admin/directory_add" ;
	}
	
	// 跳转至画册目录列表
	@RequestMapping(params = { "method=forwardDirectory" }, method = RequestMethod.GET)
	public String forwardDirectory(@RequestParam("albumId") String albumId,ModelMap map) {
		// 获取所有画册目录
		List<AlbumDirectory> directorys = this.directoryService.getAlbumDirectoryByAlbumId(albumId) ;
		
		String directoryId = "" ;
		List<AlbumPic> picList = new ArrayList<AlbumPic>() ;
		
		if(directorys != null && !directorys.isEmpty()){
			directoryId = directorys.get(0).getUuid() ;
			// 获取画册信息
			picList = this.picService.getAlbumPicListByADId(directoryId) ;
		}
		
		if(picList != null && !picList.isEmpty()){
			String albumUrl = propertiesBean.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH) ;
			for(AlbumPic pic : picList){
				pic.setPicUrl(albumUrl + pic.getPicUrl()) ;
			}
		}
		
		// 保存信息
		map.put("albumId", albumId) ;
		map.put("directorys", directorys) ;
		map.put("picList", picList) ;
		map.put("directoryId", directoryId) ;
 		return "/admin/album_directory";
	}
	
	// 跳转至修改画册目录
	@RequestMapping(params = { "method=forwardUpdateDirectory" }, method = RequestMethod.GET)
	public String forwardUpdateDirectory(@RequestParam("uuid") String uuid,@RequestParam("albumId") String albumId,ModelMap map) {
		// 获取画册目录信息
		AlbumDirectory directory = this.directoryService.getDirectoryByAlbumId(uuid, albumId) ;
		// 保存信息
		map.put("directory", directory) ;
 		return "admin/directory_update";
	}
	
	// 修改画册目录
	@RequestMapping(params = { "method=updateDirectory" }, method = RequestMethod.POST)
	public String updateDirectory(@ModelAttribute("directory") AlbumDirectory directory,ModelMap map) {
		// 修改画册目录信息
		this.directoryService.update(directory) ;
		// 保存信息
		map.put("flag", "success") ;
 		return "admin/directory_update";
	}
	
	// 删除画册目录
	@RequestMapping(params = { "method=deleteDirectory" }, method = RequestMethod.POST)
	public void deleteDirectory(HttpServletResponse response,@RequestParam("uuid") String uuid,ModelMap map) {
		// 删除画册目录信息
		this.directoryService.deleteById(uuid) ;
		
		try {
			response.getWriter().write("") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 删除选中图片
	@RequestMapping(params = { "method=deleteAllPictrue" }, method = RequestMethod.POST)
	public void deleteAllPictrue(HttpServletResponse response,@RequestParam("id") String id,ModelMap map) {
		// 删除画册目录信息
		this.directoryService.deleteAll(id) ;
		
		try {
			response.getWriter().write("") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 删除画册目录
	@RequestMapping(params = { "method=deleteAlbum" }, method = RequestMethod.POST)
	public void deleteAlbum(HttpServletResponse response,@RequestParam("uuid") String uuid,ModelMap map) {
		// 删除画册
		this.albumService.deleteAlbum(uuid) ;
		
		try {
			response.getWriter().write("") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 跳转至画册目录列表
	@RequestMapping(params = { "method=forwardAddPicture" }, method = RequestMethod.GET)
	public String forwardAddPicture(@RequestParam("directoryId") String directoryId,ModelMap map) {
		AlbumPic pic = new AlbumPic() ;
		pic.setAlbumDirectoryId(directoryId) ;
		// 保存信息
		map.put("picture", pic) ;
 		return "/admin/picture_add";
	}
	
	// 跳转至修改画册图片
	@RequestMapping(params = { "method=forwardUpdatePicture" }, method = RequestMethod.GET)
	public String forwardUpdatePicture(@RequestParam("uuid") String uuid,ModelMap map) {
		AlbumPic pic = this.picService.getPicById(uuid) ;

		String albumUrl = propertiesBean.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH) ;
		// 保存信息
		map.put("picture", pic) ;
		map.put("picImg", albumUrl + pic.getPicUrl()) ;
 		return "/admin/picture_update";
	}
	
	// 新增图片信息
	@RequestMapping(params = { "method=addPicture" }, method = RequestMethod.POST)
	public String addPicture(@ModelAttribute("picture") AlbumPic pic,ModelMap map) {
		// 刚提交图片
		pic.setAudit(0) ;
		pic.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())) ;
		this.picService.save(pic) ;
		// 保存信息
		map.put("flag", "success") ;
 		return "/admin/picture_add";
	}
	
	// 修改图片信息
	@RequestMapping(params = { "method=updatePicture" }, method = RequestMethod.POST)
	public String updatePicture(@ModelAttribute("picture") AlbumPic pic,ModelMap map) {
		pic.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())) ;
		this.picService.update(pic) ;
		// 保存信息
		map.put("flag", "success") ;
 		return "/admin/picture_update";
	}
	
	// 显示目录图片
	@RequestMapping(params = { "method=showPicture" }, method = RequestMethod.GET)
	public String showPicture(@RequestParam("albumId") String albumId,@RequestParam("directoryId") String directoryId,ModelMap map) {
		// 获取所有画册目录
		List<AlbumDirectory> directorys = this.directoryService.getAlbumDirectoryByAlbumId(albumId) ;
		// 获取画册信息
		List<AlbumPic> picList = this.picService.getAlbumPicListByADId(directoryId) ;
		
		if(picList != null && !picList.isEmpty()){
			String albumUrl = propertiesBean.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH) ;
			for(AlbumPic pic : picList){
				pic.setPicUrl(albumUrl + pic.getPicUrl()) ;
			}
		}
		
		// 保存信息
		map.put("albumId", albumId) ;
		map.put("directorys", directorys) ;
		map.put("picList", picList) ;
		map.put("directoryId", directoryId) ;
 		return "/admin/album_directory";
	}
	
	// 跳转至画册发布更新
	@RequestMapping(params = { "method=forwardPublishInfo" }, method = RequestMethod.GET)
	public String forwardPublishInfo(@RequestParam("albumId") String albumId,ModelMap map) {
		AlbumUpdate publish = new AlbumUpdate() ;
		publish.setAlbumId(albumId) ;
		// 保存信息
		map.put("publish", publish) ;
 		return "/admin/album_publish";
	}
	
	// 跳转至画册发布更新
	@RequestMapping(params = { "method=addPublishInfo" }, method = RequestMethod.POST)
	public String addPublishInfo(@ModelAttribute("publish") AlbumUpdate publish,ModelMap map) {
		publish.setUserId(super.getUserId()) ;
		publish.setAudit(0) ;
		publish.setCreateTime(new Date()) ;
		// 保存发布信息
		this.albumService.savePublish(publish) ;
		// 保存信息
		map.put("flag", "success") ;
 		return "/admin/album_publish";
	}
	
	@RequestMapping(params = { "method=deleltePicture" }, method = RequestMethod.POST)
	public void deleltePicture(HttpServletResponse response,@RequestParam("uuid") String uuid,ModelMap map) {
		// 删除画册目录信息
		this.picService.deleteById(uuid) ;
		
		try {
			response.getWriter().write("") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 显示审批信息
	@RequestMapping(params = { "method=showAuditInfo" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String showAuditInfo(@RequestParam("albumName") String albumName,ModelMap map) {
		// 获取审批信息
		List<AlbumUpdateVO> publishList = this.userService.getAlbumUpdateInfo(0, albumName) ;
		
		// 保存信息
		map.put("albumName", albumName) ;
		map.put("publishList", publishList) ;
		return "/admin/album_audit" ;
	}
	
	// 修改审批状态
	@RequestMapping(params = { "method=changeStatus" }, method = {RequestMethod.POST})
	public void changeStatus(HttpServletResponse response,@RequestParam("uuid") String uuid,@RequestParam("audit") int audit,ModelMap map) {
		// 修改审批状态
		this.userService.changeStatus(uuid, audit) ;
		
		try {
			response.getWriter().write("") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 跳转二维码
	@RequestMapping(params = { "method=forwardErWeiMa" }, method = RequestMethod.GET)
	public String forwardErWeiMa(@RequestParam("albumId") String albumId,ModelMap map) {
		Album album = albumService.getAlbumById(albumId);
		System.out.println("alisa:"+album.getAlbumAlias());
		map.put("alisa", album.getAlbumAlias());
 		return "/admin/album_erwm";
	}
	
	// 照片上移
	@RequestMapping(params = { "method=shiftUp" }, method = RequestMethod.POST)
	public void shiftUp(HttpServletResponse response,@RequestParam("uuid") String uuid,@RequestParam("up_uuid") String up_uuid,ModelMap map) {
		this.picService.shiftUpPic(uuid, up_uuid) ;
		try {
			response.getWriter().write("") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 照片下移
	@RequestMapping(params = { "method=shiftDown" }, method = RequestMethod.POST)
	public void shiftDown(HttpServletResponse response,@RequestParam("uuid") String uuid,@RequestParam("down_uuid") String down_uuid,ModelMap map) {
		this.picService.shiftDownPic(uuid, down_uuid) ;
		try {
			response.getWriter().write("") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
