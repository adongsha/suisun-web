package cn.suisun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.suisun.beans.Album;
import cn.suisun.service.AlbumService;
import cn.suisun.service.RecommendService;
import cn.suisun.vos.RecommendVo;

@Controller
@RequestMapping({ "/u/recommendAction.htm" })
public class RecommendAction {

	@Resource
	AlbumService albumService;
	
	@Resource
	RecommendService recommendService;
	
	@RequestMapping(params={"method=forwardRecommend"}, method=RequestMethod.GET)
	public String forwardRecommend(ModelMap map){
		List<Album> aList = albumService.getAlbumList();
		List<RecommendVo> list = recommendService.getRecommendList();
		map.put("aList", aList);
		map.put("rList", list);
		return "admin/recommend";
	}
	
	@RequestMapping(params={"method=addRecommend"}, method=RequestMethod.POST)
	@ResponseBody
	public String addRecommend(@RequestParam("rid")String rid,ModelMap map){
		boolean flag = recommendService.addRecommend(rid);
		if(flag){
			return "3";
		} else {
			return "-3";
		}
	}
	
	@RequestMapping(params={"method=forwardTip"}, method=RequestMethod.GET)
	public String forwardTip(@RequestParam("code")String code,ModelMap map){
		map.put("code", code);
		return "admin/tip";
	}
}
