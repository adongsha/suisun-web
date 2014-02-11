/**
 * 版权所有 2013 efuture Company, Inc. 保留所有权
 */
package cn.suisun.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import cn.suisun.service.AlbumDirectoryService;
import cn.suisun.service.AlbumPicService;
import cn.suisun.service.AlbumService;
import cn.suisun.service.IndustryService;
import cn.suisun.service.RecommendService;
import cn.suisun.service.SampleService;
import cn.suisun.service.UserService;
import cn.suisun.utils.GlobalConstants;
import cn.suisun.utils.JsonUtil;
import cn.suisun.utils.PropertiesBean;
import cn.suisun.utils.PropertiesUtils;
import cn.suisun.vos.SampleVo;


/**
 * SampleService 对 SampleApi的实现
 * 
 * @author wanghj
 * @data 2013-05-23
 *
 */

@Component
@Path("/sampleService/")
public class SampleResource {

	@Resource
	SampleService sampleService;
	
	
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
	
	
	/**
	 * {@inheritDoc}
	 * 访问路径：http://localhost:8080/e-zero/rs/sampleService/getSample/3
	 */
	@GET
	@Path("getSample/{sampleId}")
    @Produces(MediaType.APPLICATION_JSON)
	public SampleVo getSample(@PathParam("sampleId")String sampleId) {
		System.out.println("--->sampleDao:"+sampleService);
		System.out.println("-------------------->"+sampleId);
		System.out.println("sampleService-->"+sampleService.getSample(sampleId));

		return new SampleVo("1","wanghj");
	}
	
	@POST
	@Path("postdemo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public SampleVo postdemo(SampleVo sampleVo){
		System.out.println("come....");
	//	System.out.println("id:"+id);
	//	System.out.println("name"+name);
	//	SampleVo sampleVo = new SampleVo("asd", "asdf");
		return sampleVo;
	}
	
	
	/**
	 * 范例用表单提交
	 * @param id
	 * @param name
	 * @return
	 */
	@POST
	@Path("postdemo1")
	 @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<SampleVo> postdemo1(@FormParam("id")String id, @FormParam("name")String name){
		System.out.println("come....");
		System.out.println("id:"+id);
		System.out.println("name"+name);
		List<SampleVo> list = new ArrayList<SampleVo>();
		for(int i=0;i<5;i++){
			SampleVo sampleVo = new SampleVo("asd", "asdf");
         			list.add(sampleVo);
		}
		return list;
	}
	
	@GET
	@Path("getdemo")
	 @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public SampleVo getdemo(@QueryParam("id")String id, @QueryParam("name")String name){
		System.out.println("come....");
		System.out.println("id:"+id);
		System.out.println("name"+name);
		SampleVo sampleVo = new SampleVo("asd", "asdf");
		return sampleVo;
	}
	
	
	@POST
	@Path("postdemo2")
	 @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public SampleVo postdemo2(String id, String name){
		System.out.println("come....");
		System.out.println("id:"+id);
		System.out.println("name"+name);
		SampleVo sampleVo = new SampleVo("asd", "asdf");
		return sampleVo;
	}

	@POST
	@Path("getSampleforpost")
    @Produces(MediaType.APPLICATION_JSON)
    public SampleVo getSamplePost(SampleVo sampleVo){
		System.out.println("---------->"+sampleVo.getName());
    	return sampleVo;
    }
	
	@POST
	@Path("postString")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String postString(SampleVo samleVo){
		System.out.println("------------------come");
		return samleVo.toString();
	}

}


