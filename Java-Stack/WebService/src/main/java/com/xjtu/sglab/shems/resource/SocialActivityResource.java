package com.xjtu.sglab.shems.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xjtu.sglab.shems.dao.IGpsInfoDAO;
import com.xjtu.sglab.shems.dao.ILocationTypeDAO;
import com.xjtu.sglab.shems.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.shems.entity.GpsInfo;
import com.xjtu.sglab.shems.entity.SocialInfo;
import com.xjtu.sglab.shems.model.demand.EventToDemand;
import com.xjtu.sglab.shems.service.IOperateSocialService;

@Component
@Path("socialInfo")
public class SocialActivityResource {
	@Resource
	IOperateSocialService operateSocialService;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveSocialInfo(SocialInfo socialInfo) {
		try {
			operateSocialService.saveSocialInfo(socialInfo);
			class MySocialInfoRunnable implements Runnable{
				SocialInfo socialIfo;
				public MySocialInfoRunnable(SocialInfo socialIfo) {
					super();
					this.socialIfo = socialIfo;
				}
				@Override
				public void run() {
					try {
						EventToDemand.handleSocialInfo(this.socialIfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Thread thread = new Thread(new MySocialInfoRunnable(socialInfo));
			thread.start();
			return Response.status(201)
					.entity("A new socialinfo record has been created").build();
		} catch (Exception e) {
			return Response.status(202).entity("creation error").build();
		}

	}

	@POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response saveSocialInfo(List<SocialInfo> socialInfoList) {
		try {
			operateSocialService.saveSocialInfo(socialInfoList);
			for(SocialInfo socialInfo:socialInfoList){
				class MySocialInfoRunnable implements Runnable{
					SocialInfo socialIfo;
					public MySocialInfoRunnable(SocialInfo socialIfo) {
						super();
						this.socialIfo = socialIfo;
					}
					@Override
					public void run() {
						try {
							EventToDemand.handleSocialInfo(this.socialIfo);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				Thread thread = new Thread(new MySocialInfoRunnable(socialInfo));
				thread.start();
			}
			return Response.status(201)
					.entity("New socialinfo list records has been created")
					.build();
		} catch (Exception e) {
			return Response.status(202).entity("creation error").build();
		}

	}
	
	@GET
	@Path("/query")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response querySocialInfo() {
		try {
			SocialInfo socialInfo = operateSocialService.querySocialInfo();		
			socialInfo.getActivityType().setSocialActivityToDemands(null);
			socialInfo.getActivityType().setSocialInfos(null);
			socialInfo.getSocialSource().setSocialInfos(null);
			return Response.status(201).entity(socialInfo).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return Response.status(202).entity("query error").build();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
