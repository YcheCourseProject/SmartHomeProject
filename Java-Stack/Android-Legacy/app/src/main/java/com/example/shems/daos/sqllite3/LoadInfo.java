package com.example.shems.daos.sqllite3;


public class LoadInfo {
    String loadname;
    int onhour ;
    int offhour;
    double energy;
    double duration;
    String description;
    public String getLoadname() {
        return loadname;
    }

    public void setLoadname(String loadname) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LoadInfo(String loadname, int onhour, int offhour, double energy,
                    double duration, String description) {
        super();
        this.loadname = loadname;
        this.onhour = onhour;
        this.offhour = offhour;
        this.energy = energy;
        this.duration = duration;
        this.description = description;
    }



}
