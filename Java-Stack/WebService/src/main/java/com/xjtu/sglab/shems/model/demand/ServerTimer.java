package com.xjtu.sglab.shems.model.demand;


import java.util.Timer;

public class ServerTimer extends Timer {
	
	public enum TimerType {  
		  DETECT, CONTROL_DETAIL
	} 
	
	private TimerType timerType;	
	private int uid;
	
	public ServerTimer(){
		super();
	}
	
	public ServerTimer(int id, TimerType type){
		super();
		uid = id;
		timerType = type;		
	}
	
	public int getUid(){
		return uid;
	}
	
	public void setUid(int uid){
		this.uid = uid;
	}
	
	public TimerType getTimerType(){
		return timerType;
	}
	
	public void setTimerType(TimerType timerType){
		this.timerType = timerType;
	}
	
	@Override
	public void cancel(){
		super.cancel();
	}
	
	
}
