package com.varna;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Warning {
	String town = "";
	String station = "";
	String type = "";
	String decs = "";
	String created = "";
	
	//SimpleDateFormat dfm = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    //Date created;
    
	public String getTown() {
		return town;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDecs() {
		return decs;
	}
	public void setDecs(String decs) {
		this.decs = decs;
	}
}
