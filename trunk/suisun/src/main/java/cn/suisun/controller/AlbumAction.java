package cn.suisun.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.suisun.beans.Album;
import cn.suisun.service.AlbumService;
import cn.suisun.service.UserService;
import cn.suisun.utils.BaseAction;

/**
 * @author pei
 * 画册管理控制器
 */
@Controller
@RequestMapping({ "/u/albumsAction.htm" })
public class AlbumAction extends BaseAction{

	private static final int pageSize = 10;
	
	// 画册管理接口
	@Resource
	private AlbumService albumService ;
	
	// 用户接口
	@Resource
	private UserService userService ;

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
		// 保存信息
		map.put("albums", albums) ;
		map.put("albumName", "") ;
		map.put("enterpriseName", "") ;
		return "/admin/album_list";
	}
	
	// 跳转至新增画册
	@RequestMapping(params = { "method=forwardAddAlbum" }, method = RequestMethod.GET)
	public String forwardAddAlbum(ModelMap map) {
		// 保存信息
		map.put("album", new Album()) ;
		return "admin/album_add";
	}
		
	// 新增画册
	@RequestMapping(params = { "method=addAlbum" }, method = RequestMethod.POST)
	public String addAlbum(@ModelAttribute("album") Album album,ModelMap map) {
		// 保存画册信息
		album.setUserId(super.getUserId()) ;
		album.setCreateTime(new Date()) ;
		this.albumService.Save(album) ;
		return "admin/album_add" ;
	}
	
	// 跳转至画册修改界面
	@RequestMapping(params = { "method=forwardUpdateAlbum" }, method = RequestMethod.GET)
	public String forwardUpdateAlbum(@RequestParam("uuid") String uuid,ModelMap map) {
		System.out.println("************ update") ;
		// 获取画册信息
		map.put("album", this.albumService.getAlbumById(uuid)) ;
		return "admin/album_update";
	}
}
