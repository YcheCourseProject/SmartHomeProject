package com.xjtu.sglab.gateway.comm.meter;

public interface IMeterDAOFactory {
	public IMeterDAO getGEDAO(String ip);
}
