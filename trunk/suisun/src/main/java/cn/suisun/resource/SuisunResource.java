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
import cn.suisun.service.AlbumDirectoryService;
import cn.suisun.service.AlbumPicService;
import cn.suisun.service.AlbumService;
import cn.suisun.service.UserService;
import cn.suisun.utils.GlobalConstants;
import cn.suisun.utils.JsonUtil;
import cn.suisun.utils.PropertiesUtils;

@Component
@Path("/m/album/")
public class SuisunResource {
	
	@Resource
	UserService userService;
	
	@Resource
	AlbumService albumService;
	
	@Resource
	AlbumDirectoryService albumDirectoryService;
	
	@Resource
	AlbumPicService albumPicService;

	@GET
	@Path("getAlbumFormId")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAlbumFormId(@QueryParam("albumId")String albumId){
		if(StringUtils.isEmpty(albumId)){
			return JsonUtil.msg(-1);
		}
		String albumUrl = PropertiesUtils.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_COVER_PATH);
		Album album = albumService.getAlbumById(albumId);
		JSONObject result = new JSONObject();
		JSONObject data = JSONObject.fromObject(album);
		data.put("albumCover", albumUrl+album.getAlbumCover());
		JSONArray directoryList = new JSONArray();
		List<AlbumDirectory> dList = albumDirectoryService.getAlbumDirectoryByAlbumId(albumId);
		StringBuffer picUrl = new StringBuffer(PropertiesUtils.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_PIC_PATH));
		for(AlbumDirectory ad : dList){
			JSONObject adSub = JSONObject.fromObject(ad);
			List<AlbumPic> aPicList = albumPicService.getAlbumPicListByADId(ad.getUuid());
			JSONArray photoList = new JSONArray();
			for(AlbumPic pic : aPicList){
				JSONObject jsonPic = JSONObject.fromObject(pic);
				jsonPic.put("picUrl", picUrl.append(pic.getPicUrl()));
				photoList.add(jsonPic);
			}
			adSub.put("photoList", photoList);
			directoryList.add(adSub);
		}
		data.put("directoryList", directoryList);
		JSONObject user = JSONObject.fromObject(userService.getUserByUid(album.getUserId()));
		StringBuffer logo = new StringBuffer(PropertiesUtils.getProperty(GlobalConstants.CONFIG_NAME, GlobalConstants.ALBUM_HCIMG_PATH));
		logo.append(user.get("logoUrl"));
		user.put("logoUrl", logo.toString());
		result.put("statuCode", 200);
		result.put("data", data);
		result.put("user", user);
		return result.toString();
	}
	
	@GET
	@Path("getAlbumFromAlias")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAlbumFromAlias(@QueryParam("albumAlias")String albumAlias){
		if(StringUtils.isEmpty(albumAlias)){
			return JsonUtil.msg(-1);
		}
		
		
		return "";
	}
}
