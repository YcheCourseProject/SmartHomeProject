package com.example.shems.models.load_info;

import android.R.string;

public class LoadEvent {
	string loadname;
	int onhour ;
	int offhour;
	double energy;
	double duration;
	string description;
	public string getLoadname() {
		return loadname;
	}

	public void setLoadname(string loadname) {
		this.loadname = loadname;
	}

	public int getOnhour() {
		return onhour;
	}

	public void setOnhour(int onhour) {
		this.onhour = onhour;
	}

	public int getOffhour() {
		return offhour;
	}

	public void setOffhour(int offhour) {
		this.offhour = offhour;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public string getDescription() {
		return description;
	}

	public void setDescription(string description) {
		this.description = description;
	}

	public LoadEvent(string loadname, int onhour, int offhour, double energy,
                     double duration, string description) {
		super();
		this.loadname = loadname;
		this.onhour = onhour;
		this.offhour = offhour;
		this.energy = energy;
		this.duration = duration;
		this.description = description;
	}



}
