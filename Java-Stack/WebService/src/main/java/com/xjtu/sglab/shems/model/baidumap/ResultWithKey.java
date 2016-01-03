package com.xjtu.sglab.shems.model.baidumap;

public class ResultWithKey {
	MapResult result;
	String key;
	public MapResult getResult() {
		return result;
	}
	public void setResult(MapResult result) {
		this.result = result;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public ResultWithKey(MapResult result, String key) {
		super();
		this.result = result;
		this.key = key;
	}
	public ResultWithKey() {
	
	}
	
}
