package cn.suisun.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import cn.suisun.beans.AlbumUpdate;
import cn.suisun.beans.Industry;
import cn.suisun.beans.Recommend;
import cn.suisun.beans.User;
import cn.suisun.dao.AlbumUpdateDao;
import cn.suisun.service.AlbumDirectoryService;
import cn.suisun.service.AlbumPicService;
import cn.suisun.service.AlbumService;
import cn.suisun.service.AlbumUpdateService;
import cn.suisun.service.IndustryService;
import cn.suisun.service.RecommendService;
import cn.suisun.service.UserService;
import cn.suisun.utils.GlobalConstants;
import cn.suisun.utils.JsonUtil;
import cn.suisun.utils.PropertiesBean;

@Component
@Path("/album/")
public class AlbumResource {


	@Resource
	UserService userService;

	@Resource
	AlbumService albumService;

	@Resource
	AlbumDirectoryService albumDirectoryService;

	@Resource
	AlbumPicService albumPicService;

	@Resource
	RecommendService recommendService;

	@Resource
	IndustryService industryService;
	
	@Resource
	PropertiesBean propertiesBean;
	
	@Resource
    AlbumUpdateService albumUpdateService;	
	/**
	 * 根据画册ID获取单个画册的所有信息接口。
	 * 
	 * @param albumId
	 * @return
	 */
	@GET
	@Path("getAlbumFormId")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlbumFormId(@QueryParam("albumId") String albumId) {
		if (StringUtils.isEmpty(albumId)) {
			return JsonUtil.msg(-1);
		}
		Album album = albumService.getAlbumById(albumId);
		String albumUrl = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH);
		JSONObject result = new JSONObject();
		JSONObject data = JSONObject.fromObject(album);
		data.put("albumCover", albumUrl + album.getAlbumCover());
		JSONArray directoryList = new JSONArray();
		List<AlbumDirectory> dList = albumDirectoryService
				.getAlbumDirectoryByAlbumId(albumId);
		StringBuffer picUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH));
		for (AlbumDirectory ad : dList) {
			JSONObject adSub = JSONObject.fromObject(ad);
			List<AlbumPic> aPicList = albumPicService.getAlbumPicListByADId(ad
					.getUuid());
			JSONArray photoList = new JSONArray();
			for (AlbumPic pic : aPicList) {
				JSONObject jsonPic = JSONObject.fromObject(pic);
				jsonPic.put("picUrl", picUrl.append(pic.getPicUrl()).toString());
				photoList.add(jsonPic);
			}
			adSub.put("photoList", photoList);
			directoryList.add(adSub);
		}
		data.put("directoryList", directoryList);
		JSONObject user = JSONObject.fromObject(userService.getUserByUid(album
				.getUserId()));
		StringBuffer logo = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH));
		logo.append(user.get("logoUrl"));
		user.put("logoUrl", logo.toString());
		result.put("statuCode", 200);
		result.put("data", data);
		result.put("user", user);
		return result.toString();
	}

    /**
	 * 根据画册别号ailas获取单个画册的所有信息接口。
	 * 
	 * @param albumAlias
	 * @return
	 */
	@GET
	@Path("getAlbumFromAlias")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlbumFromAlias(@QueryParam("albumAlias") String albumAlias) {
		if (StringUtils.isEmpty(albumAlias)) {
			return JsonUtil.msg(-1);
		}
		Album album = albumService.getAlbumByAlias(albumAlias);
		String albumUrl = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH);
		JSONObject result = new JSONObject();
		JSONObject data = JSONObject.fromObject(album);
		data.put("albumCover", albumUrl + album.getAlbumCover());
		JSONArray directoryList = new JSONArray();
		List<AlbumDirectory> dList = albumDirectoryService
				.getAlbumDirectoryByAlbumId(album.getUuid());
		StringBuffer picUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH));
		for (AlbumDirectory ad : dList) {
			JSONObject adSub = JSONObject.fromObject(ad);
			List<AlbumPic> aPicList = albumPicService.getAlbumPicListByADId(ad
					.getUuid());
			JSONArray photoList = new JSONArray();
			for (AlbumPic pic : aPicList) {
				JSONObject jsonPic = JSONObject.fromObject(pic);
				jsonPic.put("picUrl", picUrl.append(pic.getPicUrl()).toString());
				photoList.add(jsonPic);
			}
			adSub.put("photoList", photoList);
			directoryList.add(adSub);
		}
		data.put("directoryList", directoryList);
		JSONObject user = JSONObject.fromObject(userService.getUserByUid(album
				.getUserId()));
		StringBuffer logo = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH));
		logo.append(user.get("logoUrl"));
		user.put("logoUrl", logo.toString());
		result.put("statuCode", 200);
		result.put("data", data);
		result.put("user", user);
		return result.toString();
	}

	/**
	 * 推荐模块获取指定页的画册数组信息接口。
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path("recommendation")
	@Produces(MediaType.APPLICATION_JSON)
	public String recommendation(@QueryParam("currentPage") String currentPage,
			@QueryParam("pageNum") String pageSize) {

		if (StringUtils.isEmpty(pageSize) || StringUtils.isEmpty(currentPage)) {
			return JsonUtil.msg(-1);
		}

		int pageSizeInt = Integer.parseInt(pageSize);

		JSONObject result = new JSONObject();
		List<Recommend> reList = recommendService.getRecommendPage(
				Integer.parseInt(currentPage), pageSizeInt);
		JSONArray albumList = new JSONArray();
		String cover = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH);
		String picUrl = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH);
		String logoUrl = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH);
		for (Recommend r : reList) {
			Album album = albumService.getAlbumById(r.getAlbumId());
			album.setAlbumCover(cover+album.getAlbumCover());
			JSONObject alJson = JSONObject.fromObject(album);
			JSONArray directoryList = new JSONArray();
			List<AlbumDirectory> adList = albumDirectoryService
					.getAlbumDirectoryByAlbumId(album.getUuid());
			for (AlbumDirectory a : adList) {
				JSONObject adJson = JSONObject.fromObject(a);
				List<AlbumPic> picList = albumPicService
						.getAlbumPicListByADId(a.getUuid());
				JSONArray photoList = new JSONArray();
				for (AlbumPic pic : picList) {
					pic.setPicUrl(picUrl+pic.getPicUrl());
					JSONObject picJson = JSONObject.fromObject(pic);
					photoList.add(picJson);
				}
				adJson.put("photoList", photoList);
				directoryList.add(adJson);
			}
			alJson.put("directoryList", directoryList);
			System.out.println("---------------->"+album.getUserId());
			User user = userService.getUserByUid(album.getUserId());
		    if(!StringUtils.isEmpty(user)){
		    	user.setLogoUrl(logoUrl+user.getLogoUrl());
		    	JSONObject userJson = JSONObject.fromObject(user);
		    	Industry industry = industryService.getIndustryById(user
		    			.getIndustryId());
		    	JSONObject industryJson = JSONObject.fromObject(industry);
		    	userJson.put("industry", industryJson);
		    	alJson.put("user", userJson);
		    }
			albumList.add(alJson);

		}
		result.put("albumList", albumList);
		result.put("statuCode", 200);
		int all = recommendService.getRecommendPageAmount();
		int maxPage = (int) Math.ceil((double) (all / pageSizeInt));
		result.put("maxPage", maxPage);
		result.put("pageSize", pageSize);
		result.put("currentPage", currentPage);
		return result.toString();
	}

	/**
	 * 查询企业用户的所有画册信息数组接口
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("getAlbumFormCompany")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlbumFormCompany(@QueryParam("userId") String userId) {
		String cover = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH);
		String picUrl = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH);
		StringBuffer logoUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH));
        JSONObject result = new JSONObject();
		User user = userService.getUserByUid(userId);
		user.setLogoUrl(logoUrl.append(user.getLogoUrl()).toString());
		Industry industry = industryService.getIndustryById(user
				.getIndustryId());
		JSONObject inJson = JSONObject.fromObject(industry);
		JSONObject data = JSONObject.fromObject(user);
		data.put("industry", inJson);
        JSONArray albumList = new JSONArray();
		List<Album> alList = albumService.getAlbumListByUserId(userId);
		for (Album album : alList) {
			album.setAlbumCover(cover+album.getAlbumCover());
			JSONObject albumJson = JSONObject.fromObject(album);
			
			List<AlbumDirectory> adList = albumDirectoryService
					.getAlbumDirectoryByAlbumId(album.getUuid());
			JSONArray directoryList = new JSONArray();
			for (AlbumDirectory a : adList) {
				JSONObject adJson = JSONObject.fromObject(a);
				List<AlbumPic> picList = albumPicService
						.getAlbumPicListByADId(a.getUuid());
				JSONArray photoList = new JSONArray();
				for (AlbumPic pic : picList) {
					pic.setPicUrl(picUrl+pic.getPicUrl());
					JSONObject picJson = JSONObject.fromObject(pic);
					photoList.add(picJson);
				}
				adJson.put("photoList", photoList);
				directoryList.add(adJson);
			}
			albumJson.put("directoryList", directoryList);
			albumList.add(albumJson);
		}
		data.put("albumList", albumList);
		result.put("statuCode", 200);
		result.put("data", data);
		return result.toString();
	}
	
	
	/**
	 * 搜索
	 * @param keyword
	 * @param currentPage
	 * @param pageNum
	 * @return
	 */
	@GET
	@Path("getAlbumFormKey")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlbumFormKey(@QueryParam("keyword")String keyword,@QueryParam("currentPage")String currentPage,
			@QueryParam("pageNum")String pageNum){

		if(StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(pageNum)){
			return JsonUtil.msg(-1);
		}
		String cover = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH);
		String picUrl = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH);
		
		JSONObject result = new JSONObject();
		List alList = albumService.getAlbumByKeyword(keyword, Integer.parseInt(currentPage), Integer.parseInt(pageNum));
		JSONArray data = new JSONArray();
		String logo = "";
		String logoUrl = propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH);
		for (int i=0; i< alList.size(); i++) {
			Object[] obj = (Object[]) alList.get(i);
			Album album = (Album) obj[0];
			User user = (User) obj[1];
			logo = user.getLogoUrl();
			album.setAlbumCover(cover+album.getAlbumCover());
			JSONObject albumJson = JSONObject.fromObject(album);
			
			List<AlbumDirectory> adList = albumDirectoryService
					.getAlbumDirectoryByAlbumId(album.getUuid());
			JSONArray directoryList = new JSONArray();
			for (AlbumDirectory a : adList) {
				JSONObject adJson = JSONObject.fromObject(a);
				List<AlbumPic> picList = albumPicService
						.getAlbumPicListByADId(a.getUuid());
				JSONArray photoList = new JSONArray();
				for (AlbumPic pic : picList) {
					pic.setPicUrl(picUrl+pic.getPicUrl());
					JSONObject picJson = JSONObject.fromObject(pic);
					photoList.add(picJson);
				}
				adJson.put("photoList", photoList);
				directoryList.add(adJson);
			}
			albumJson.put("directoryList", directoryList);
			user.setLogoUrl(logoUrl+logo);
			Industry industry = industryService.getIndustryById(user.getIndustryId());
			JSONObject inJson = JSONObject.fromObject(industry);
			JSONObject jsonUser = JSONObject.fromObject(user);
			user.setLogoUrl(logo);
			jsonUser.put("industry", inJson);
			albumJson.put("user", jsonUser);
			data.add(albumJson);
		}
    	int maxPage = (int) Math.ceil((double) (albumService.getAlbumByKeywordAmount(keyword)/ Integer.parseInt(pageNum)));
		result.put("statuCode", 200);
		result.put("data", data);
		result.put("maxPage", maxPage);
		result.put("currentPage", currentPage);
		result.put("pageNum", pageNum);
		return result.toString();
	}

	@POST
	@Path("getFeeds")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFeeds(@FormParam("data")String data){
		JSONArray ja = JSONArray.fromObject(data);
		JSONObject result = new JSONObject();
		JSONArray dataJa = new JSONArray();
		
		for(int i=0; i< ja.size(); i++){
			JSONObject json = ja.getJSONObject(i);
			String albumId = json.getString("albumId");
			long time = json.getLong("time");
			AlbumUpdate albumUpdate = albumUpdateService.getAlbumUpdateByAlbumId(albumId);
			if(StringUtils.isEmpty(albumUpdate)){
				
			} else {
				if(time < albumUpdate.getCreateTime().getTime()){
					JSONObject sub = JSONObject.fromObject(albumUpdate);
					dataJa.add(sub);
				}
			}
		}
		result.put("statuCode", 200);
		result.put("data", dataJa);
		return result.toString();
	}
	
	@GET
	@Path("picPraise")
	@Produces(MediaType.APPLICATION_JSON)
	public String picPraise(@QueryParam("picId")String picId){
		AlbumPic pic = albumPicService.getPicById(picId);
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(pic)){
			json.put("200", "没有改图片");
		} else {
			if(StringUtils.isEmpty(pic.getPraise())){
				pic.setPraise(1);
			} else {
				pic.setPraise(pic.getPraise()+1);
			}
		}
		albumPicService.update(pic);
		json.put("statuCode", 200);
		return json.toString();
	}
	
}
