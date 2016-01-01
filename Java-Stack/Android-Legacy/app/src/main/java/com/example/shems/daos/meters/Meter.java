package com.example.shems.daos.meters;

public class Meter {
    public Connector connector = null;
    public String passwd = "";

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public Connector getConnector() {
        return this.connector;
    }
}
