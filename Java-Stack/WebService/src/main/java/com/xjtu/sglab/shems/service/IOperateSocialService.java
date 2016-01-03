package com.xjtu.sglab.shems.service;

import java.util.List;

import com.xjtu.sglab.shems.entity.SocialInfo;

public interface IOperateSocialService {
	public void saveSocialInfo(SocialInfo socialInfo) throws Exception;
	public void saveSocialInfo(List<SocialInfo> socialInfo) throws Exception;
	
	public SocialInfo querySocialInfo() throws Exception;
}
