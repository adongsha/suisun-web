package cn.suisun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.suisun.beans.Album;
import cn.suisun.service.AlbumService;
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

	// 显示画册列表
	@RequestMapping(params = { "method = showAlbums" }, method = RequestMethod.POST)
	public String showAlbums(@RequestParam("albumName") String albumName,@RequestParam("enterpriseName") String enterpriseName,ModelMap map) {
		// 获取画册信息
		List<Album> albums = this.albumService.getAlbumList(albumName, enterpriseName) ;
		map.put("albums", albums) ;
		return "/admin/album_list";
	}
	
	// 显示画册列表
	@RequestMapping(params = { "method = forwardAlbums" }, method = RequestMethod.POST)
	public String forwardAlbums(ModelMap map) {
		// 获取画册信息
		List<Album> albums = this.albumService.getAlbumListByUserId(super.getUserId()) ;
		map.put("albums", albums) ;
		return "/admin/album_list";
	}
}
