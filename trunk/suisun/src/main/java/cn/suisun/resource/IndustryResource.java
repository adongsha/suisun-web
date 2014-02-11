package cn.suisun.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.suisun.beans.Album;
import cn.suisun.beans.AlbumDirectory;
import cn.suisun.beans.AlbumPic;
import cn.suisun.beans.Industry;
import cn.suisun.beans.User;
import cn.suisun.service.AlbumDirectoryService;
import cn.suisun.service.AlbumPicService;
import cn.suisun.service.AlbumService;
import cn.suisun.service.IndustryService;
import cn.suisun.service.UserService;
import cn.suisun.utils.GlobalConstants;
import cn.suisun.utils.JsonUtil;
import cn.suisun.utils.PropertiesBean;
@Component
@Path("/m/industry/")
public class IndustryResource {

	
	public IndustryResource() {
	}

	@Resource
	UserService userService;
	
	@Resource
	IndustryService industryService;

	@Resource
	AlbumService albumService;
	
	@Resource
	AlbumDirectoryService albumDirectoryService;

	@Resource
	AlbumPicService albumPicService;
	
	@Resource
	PropertiesBean propertiesBean;

	@GET
	@Path("getIndustry")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIndustry() {
		List<Industry> list = industryService.getIndustry();
		JSONArray data = new JSONArray();
		for (Industry i : list) {
			JSONObject iJson = JSONObject.fromObject(i);
			data.add(iJson);
		}
		JSONObject result = new JSONObject();
		result.put("statuCode", 200);
		result.put("data", data);
		return result.toString();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list(@QueryParam("currentPage") String currentPage,
			@QueryParam("pageNum") String pageNum,
			@QueryParam("industryId") String industryId) {
		if (StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(pageNum)
				|| StringUtils.isEmpty(industryId)) {
			return JsonUtil.msg(-1, "参数不对");
		}
		StringBuffer cover = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH));
		StringBuffer picUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH));
		StringBuffer logoUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH));
		List<Album> alList = albumService.getAlbumListByIndustryId(industryId,
				Integer.parseInt(currentPage), Integer.parseInt(pageNum));
		
		JSONArray data = new JSONArray();
		for (Album album : alList) {
			album.setAlbumCover(cover.append(album.getAlbumCover()).toString());
			JSONObject albumJson = JSONObject.fromObject(album);
			
			List<AlbumDirectory> adList = albumDirectoryService
					.getAlbumDirectoryByAlbumId(album.getUserId());
			JSONArray directoryList = new JSONArray();
			for (AlbumDirectory a : adList) {
				JSONObject adJson = JSONObject.fromObject(a);
				List<AlbumPic> picList = albumPicService
						.getAlbumPicListByADId(a.getUuid());
				JSONArray photoList = new JSONArray();
				for (AlbumPic pic : picList) {
					pic.setPicUrl(picUrl.append(pic.getPicUrl()).toString());
					JSONObject picJson = JSONObject.fromObject(pic);
					photoList.add(picJson);
				}
				adJson.put("photoList", photoList);
				directoryList.add(adJson);
			}
			albumJson.put("directoryList", directoryList);
			User user = userService.getUserByAlbumId(album.getUuid());
			user.setLogoUrl(logoUrl.append(user.getLogoUrl()).toString());
			Industry industry = industryService.getIndustryById(user.getIndustryId());
			JSONObject inJson = JSONObject.fromObject(industry);
			JSONObject jsonUser = JSONObject.fromObject(user);
			jsonUser.put("industry", inJson);
			albumJson.put("user", jsonUser);
			data.add(albumJson);
		}
		JSONObject result = new JSONObject();
    	int maxPage = (int) Math.ceil((double) (albumService.getAlbumListByIndustryIdAmouint(industryId)/ Integer.parseInt(pageNum)));
		result.put("statuCode", 200);
		result.put("data", data);
		result.put("maxPage", maxPage);
		result.put("currentPage", currentPage);
		result.put("pageNum", pageNum);
		return result.toString();
	}

}
