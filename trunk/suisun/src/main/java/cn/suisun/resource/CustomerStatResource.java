package cn.suisun.resource;

import java.util.Date;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.suisun.beans.CustomerStat;
import cn.suisun.service.CustomerStatService;

@Component
@Path("/custom/")
public class CustomerStatResource {
	
	@Resource
	CustomerStatService customerStatService;

	@POST
	@Path("statisticsLook")
	@Produces("text/plain") 
	public String statisticsLook(@FormParam("phoneType")String phoneType
			,@FormParam("latitude")String latitude,@FormParam("longitude")String longitude
			,@FormParam("albumId")String albumId, @FormParam("albumType")String albumType
			,String area){
		CustomerStat c = new CustomerStat();
		c.setPhoneType(phoneType);
		c.setAlbumId(albumId);
		c.setAlbumLook("1");
		c.setAlbumType(albumType);
		c.setArea(area);
		c.setLastTimeLook(new Date());
		if(!StringUtils.isEmpty(longitude) && !StringUtils.isEmpty(latitude)){
			c.setLatitude(Double.parseDouble(latitude));
			c.setLongitude(Double.parseDouble(longitude));
		}
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(customerStatService.save(c))){
			json.put("statuCode", -1);
		} else {
			json.put("statuCode", 200);
		}
		return json.toString();
	}
	
	@POST
	@Path("statisticsDownload")
	@Produces("text/plain") 
	public String statisticsDownload(@FormParam("phoneType")String phoneType
			,@FormParam("latitude")String latitude,@FormParam("longitude")String longitude
			,@FormParam("albumId")String albumId, @FormParam("albumType")String albumType
			,String area){
		CustomerStat c = new CustomerStat();
		c.setPhoneType(phoneType);
		c.setAlbumId(albumId);
		c.setAlbumLook("2");
		c.setAlbumType(albumType);
		c.setArea(area);
		c.setDownloadTime(new Date());
		if(!StringUtils.isEmpty(longitude) && !StringUtils.isEmpty(latitude)){
			c.setLatitude(Double.parseDouble(latitude));
			c.setLongitude(Double.parseDouble(longitude));
			
		}
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(customerStatService.save(c))){
			json.put("statuCode", -1);
		} else {
			json.put("statuCode", 200);
		}
		return json.toString();
	}
}
