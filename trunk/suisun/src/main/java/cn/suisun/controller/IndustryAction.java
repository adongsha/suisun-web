package cn.suisun.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.suisun.beans.Industry;
import cn.suisun.service.IndustryService;
import cn.suisun.utils.BaseAction;

@Controller
@RequestMapping({ "/u/industryAction.htm" })
public class IndustryAction extends BaseAction{
	
	private static final int pageSize = 10;
	
	@Resource
	IndustryService industryService;

	
	@RequestMapping(params={"method=industry"},method = RequestMethod.GET)
	public String forwardIndustry(ModelMap map){
		List<Industry> list = industryService.getIndustryPage(1, pageSize);
		int amount = industryService.getIndustryListByPageAmount();
		int pageAll = (int) Math.ceil((double)amount/pageSize);
		map.put("pageAll", pageAll);
		map.put("currentPage", 1);
		map.put("pageSize", pageSize);
		map.put("industryList", list);
		return "admin/industry";
	}

	@RequestMapping(params={"method=page"},method = RequestMethod.GET)
	public String page(@RequestParam("currentPage")String currentPage, ModelMap map){
		if(StringUtils.isEmpty(currentPage)){
			return msg(-1, "");
		}
		List<Industry> list = industryService.getIndustryPage(Integer.parseInt(currentPage), pageSize);
		int amount = industryService.getIndustryListByPageAmount();
		int pageAll = (int) Math.ceil((double)amount/pageSize);
		map.put("pageAll", pageAll);
		map.put("currentPage", currentPage);
		map.put("pageSize", pageSize);
		map.put("industryList", list);
		return "admin/industry";
	}
	
	@RequestMapping(params={"method=add"}, method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestParam("industryName")String industryName, @RequestParam("note")String note){
		Industry i = new Industry();
		i.setIndustryName(industryName);
		i.setNote(note);
		Serializable s = industryService.add(i);
		if(StringUtils.isEmpty(s)){
			return msg(-1,"");
		}
		return msg(1,"");
	}
	
	@RequestMapping(params={"method=update"}, method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestParam("industryName")String industryName, @RequestParam("note")String note,@RequestParam("id")String id){
		Industry i = new Industry();
		i.setIndustryName(industryName);
		i.setNote(note);
		i.setUuid(id);
		industryService.update(i);
		return msg(1,"");
	}
	
	@RequestMapping(params={"method=delete"}, method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam("industryName")String industryName, @RequestParam("note")String note,@RequestParam("id")String id){
		Industry i = new Industry();
		i.setIndustryName(industryName);
		i.setNote(note);
		i.setUuid(id);
		industryService.delete(i);
		return msg(1,"");
	}
}
