package cn.suisun.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import cn.suisun.beans.Industry;
import cn.suisun.service.IndustryService;

@Component
@Path("/m/industry/")
public class IndustryResource {

	@Resource
	IndustryService industryService;
	
	@GET
	@Path("getIndustry")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIndustry(){
		List<Industry> list = industryService.getIndustry();
		JSONObject result = new JSONObject();
		result.put("statuCode", 200);
		return "";
	}
}
