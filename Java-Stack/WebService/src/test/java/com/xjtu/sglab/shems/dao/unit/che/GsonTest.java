package com.xjtu.sglab.shems.dao.unit.che;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.google.gson.Gson;
import com.xjtu.sglab.shems.util.GsonUtil;

public class GsonTest {

	@Test
	public void testArrayGson() {
		Integer[] integers = new Integer[10];
		integers[0] = 2;
		Gson gson = GsonUtil.create();
		String str = gson.toJson(integers);
		System.out.println(str);
	}

	@Test
	public void testSetGson() {
		Set<Integer> set = new HashSet();
		set.add(1);
		set.add(2);
		Gson gson = GsonUtil.create();
		String str = gson.toJson(set);
		System.out.println(str);
	}

	@Test
	public void testFromArrayGson() {
		String str = "[2,null,null,null,null,null,null,null,null,null]";
		Gson gson = GsonUtil.create();
		Integer[] integers = gson.fromJson(str, Integer[].class);
		System.out.println(Arrays.toString(integers));
	}
}
