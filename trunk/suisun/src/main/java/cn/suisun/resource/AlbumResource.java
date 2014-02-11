package cn.suisun.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import cn.suisun.beans.Recommend;
import cn.suisun.beans.User;
import cn.suisun.service.AlbumDirectoryService;
import cn.suisun.service.AlbumPicService;
import cn.suisun.service.AlbumService;
import cn.suisun.service.IndustryService;
import cn.suisun.service.RecommendService;
import cn.suisun.service.UserService;
import cn.suisun.utils.GlobalConstants;
import cn.suisun.utils.JsonUtil;
import cn.suisun.utils.PropertiesBean;
import cn.suisun.vos.SampleVo;

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
	
	
	@GET
	@Path("getSample/{sampleId}")
    @Produces(MediaType.APPLICATION_JSON)
	public SampleVo getSample(@PathParam("sampleId")String sampleId) {
		System.out.println("--->sampleDao:");
		System.out.println("-------------------->"+sampleId);
		System.out.println("sampleService-->");

		return new SampleVo("1","wanghj");
	}

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
				jsonPic.put("picUrl", picUrl.append(pic.getPicUrl()));
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
				jsonPic.put("picUrl", picUrl.append(pic.getPicUrl()));
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
			@QueryParam("pageSize") String pageSize) {

		if (StringUtils.isEmpty(pageSize) || StringUtils.isEmpty(currentPage)) {
			return JsonUtil.msg(-1);
		}

		int pageSizeInt = Integer.parseInt(pageSize);

		JSONObject result = new JSONObject();
		List<Recommend> reList = recommendService.getRecommendPage(
				Integer.parseInt(currentPage), pageSizeInt);
		JSONArray albumList = new JSONArray();
		StringBuffer cover = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH));
		StringBuffer picUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH));
		StringBuffer logoUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH));
		for (Recommend r : reList) {
			Album album = albumService.getAlbumById(r.getAlbumId());
			album.setAlbumCover(cover.append(album.getAlbumCover()).toString());
			JSONObject alJson = JSONObject.fromObject(album);
			JSONArray directoryList = new JSONArray();
			List<AlbumDirectory> adList = albumDirectoryService
					.getAlbumDirectoryByAlbumId(album.getUserId());
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
			alJson.put("directoryList", directoryList);
			User user = userService.getUserByAlbumId(r.getAlbumId());
			user.setLogoUrl(logoUrl.append(user.getLogoUrl()).toString());
			JSONObject userJson = JSONObject.fromObject(user);
			Industry industry = industryService.getIndustryById(user
					.getIndustryId());
			JSONObject industryJson = JSONObject.fromObject(industry);
			userJson.put("industry", industryJson);
			alJson.put("user", userJson);
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
		StringBuffer cover = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH));
		StringBuffer picUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH));
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
			albumList.add(albumJson);
		}
		data.put("albumList", albumList);
		result.put("statuCode", 200);
		result.put("data", data);
		return result.toString();
	}
	
	@GET
	@Path("getAlbumFormKey")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlbumFormKey(@QueryParam("keyword")String keyword,@QueryParam("currentPage")String currentPage,
			@QueryParam("pageNum")String pageNum){

		if(StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(pageNum)){
			return JsonUtil.msg(-1);
		}
		StringBuffer cover = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH));
		StringBuffer picUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH));
		StringBuffer logoUrl = new StringBuffer(propertiesBean.getProperty(
				GlobalConstants.CONFIG_NAME, GlobalConstants.LOGO_IMG_PATH));
		JSONObject result = new JSONObject();
		List<Album> alList = albumService.getAlbumByKeyword(keyword, Integer.parseInt(currentPage), Integer.parseInt(pageNum));
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
    	int maxPage = (int) Math.ceil((double) (albumService.getAlbumByKeywordAmount(keyword)/ Integer.parseInt(pageNum)));
		result.put("statuCode", 200);
		result.put("data", data);
		result.put("maxPage", maxPage);
		result.put("currentPage", currentPage);
		result.put("pageNum", pageNum);
		return result.toString();
	}

	
}
