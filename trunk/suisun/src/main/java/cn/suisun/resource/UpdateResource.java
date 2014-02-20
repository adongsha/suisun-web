package cn.suisun.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.suisun.beans.AppUpdate;
import cn.suisun.service.AppUpdateService;

@Component
@Path("/update/")
public class UpdateResource {

	 @Resource
	 AppUpdateService appUpdateService;
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateVersion(@QueryParam("version") String version,
			@QueryParam("platform") String platform) {
		AppUpdate app = appUpdateService.getAppUpdateByPlatform(platform);
		JSONObject data = null;
		if(!StringUtils.isEmpty(app)){
			if(Float.parseFloat(app.getVersionCode())>Float.parseFloat(version)){
				data = JSONObject.fromObject(app);
			}
		}
		JSONObject json = new JSONObject();
		json.put("statuCode", 200);
		json.put("data", data);
		return json.toString();
	}
}
