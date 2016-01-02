package com.xjtu.sglab.gateway.comm.meter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EPM7000GEDAOFactory implements IMeterDAOFactory{
	private static EPM7000GEDAOFactory geMeterFactory;
	Map<String,IMeterDAO> ipStrMeterDAOMap=new HashMap(10);
	
	private EPM7000GEDAOFactory(){
		
	}
	
	@Override
	public IMeterDAO getGEDAO(String ip) {
		// TODO Auto-generated method stub
		IMeterDAO iMeterDAO;
		if(ipStrMeterDAOMap.containsKey(ip)){
			iMeterDAO=ipStrMeterDAOMap.get(ip);
		}
		else
		{	
			iMeterDAO=new EPM7000GEDAO(ip);
			ipStrMeterDAOMap.put(ip, iMeterDAO);
		}
		return iMeterDAO;
		
	}
	
	public static IMeterDAOFactory getInstance() {
		// TODO Auto-generated method stub
		if(geMeterFactory==null)
		{
			geMeterFactory=new EPM7000GEDAOFactory();
		}
		return geMeterFactory;
	}

}
