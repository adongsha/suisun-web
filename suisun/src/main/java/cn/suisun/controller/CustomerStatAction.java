package cn.suisun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.suisun.beans.Album;
import cn.suisun.beans.CustomerStat;
import cn.suisun.service.AlbumService;
import cn.suisun.service.CustomerStatService;
import cn.suisun.utils.BaseAction;

@Controller
@RequestMapping({ "/u/customerStatAction.htm" })
public class CustomerStatAction extends BaseAction{
	private static final int pageSize = 10;

	@Resource
	CustomerStatService customerStatService;

	@Resource
	AlbumService albumService;

	@RequestMapping(params = { "method=forwardCusStat" }, method = RequestMethod.GET)
	public String forwardCusStat(ModelMap map) {
		List<CustomerStat> list = null;
		List<Album> aList = null;
		int amount = 0;
		int pageAll = 0;
		if(getUser().getPower() == 0 || getUser().getPower() == 1){
			 list = customerStatService.getCustomerStatByPage(1,
					pageSize, null, null, 3);
			 aList = albumService.getAlbumList();
			 amount = customerStatService.getCustomerStatAmount(null, null, 3);
			 pageAll = (int) Math.ceil((double) amount / pageSize);
		} else if(getUser().getPower() == 2){
			 list = customerStatService
						.getCustomerStatByPageById(1, pageSize, getUserId(), null, null, 3);
			 
				 aList = albumService.getAlbumListByUserId(getUserId());
				 amount = customerStatService.getCustomerStatAmountById(getUserId(), null, null, 3);
				 pageAll = (int) Math.ceil((double) amount / pageSize);
			
		}
		
		map.put("pageAll", pageAll);
		map.put("currentPage", 1);
		map.put("pageSize", pageSize);
		map.put("csList", list);
		map.put("alList", aList);
		map.put("albumType", "");
		map.put("album", "");
		map.put("orderBy", 3);
		return "admin/customer_statics";
	}

	@RequestMapping(params = { "method=page" }, method = RequestMethod.GET)
	public String page(@RequestParam("currentPage") String currentPage,
			@RequestParam("albumType") String albumType,
			@RequestParam("album") String album,
			@RequestParam("orderBy") String orderBy, ModelMap map) {
		
		List<CustomerStat> list = null;
		List<Album> aList = null;
		int amount = 0;
		int pageAll = 0;
		
		if(getUser().getPower() == 0 || getUser().getPower() == 1){
			 list = customerStatService.getCustomerStatByPage(Integer.parseInt(currentPage),
					pageSize, albumType, album, Integer.parseInt(orderBy));
			aList = albumService.getAlbumList();
			 amount = customerStatService.getCustomerStatAmount(albumType, album, Integer.parseInt(orderBy));
			 pageAll = (int) Math.ceil((double) amount / pageSize);
			
		} else if(getUser().getPower() == 2){
			 list = customerStatService.getCustomerStatByPageById(Integer.parseInt(currentPage),
						pageSize,getUserId(), albumType, album, Integer.parseInt(orderBy));
			 aList = albumService.getAlbumListByUserId(getUserId());
			 amount = customerStatService.getCustomerStatAmountById(getUserId(),albumType, album, Integer.parseInt(orderBy));
			 pageAll = (int) Math.ceil((double) amount / pageSize);
			
		}

		map.put("pageAll", pageAll);
		map.put("currentPage", currentPage);
		map.put("pageSize", pageSize);
		map.put("csList", list);
		map.put("alList", aList);
		
		map.put("albumType", albumType);
		map.put("album", album);
		map.put("orderBy", orderBy);
		return "admin/customer_statics";
	}
	
	
	
}
