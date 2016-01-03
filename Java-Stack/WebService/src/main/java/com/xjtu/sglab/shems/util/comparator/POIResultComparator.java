package com.xjtu.sglab.shems.util.comparator;

import java.util.Comparator;

import com.xjtu.sglab.shems.model.baidumap.ResultWithKey;

public class POIResultComparator implements Comparator<ResultWithKey> {

	@Override
	public int compare(ResultWithKey result1, ResultWithKey result2) {
		// TODO Auto-generated method stub
		int distanceResult1=result1.getResult().getDetailInfo().getDistance();
		int distanceResult2=result2.getResult().getDetailInfo().getDistance();
		if(distanceResult1<distanceResult2)
			return -1;
		else if(distanceResult1==distanceResult2)
			return 0;
		else
			return 1;
	}

	 

}

