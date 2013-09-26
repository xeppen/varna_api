package com.widespace;

import java.util.ArrayList;
import java.util.List;

public class AdSpace {
	String name 		= "";
	String platform 	= "";
	String intType 		= "";
	String dim 			= "";
	int testmode 		= 1;
	String country 		= "";
	String channel 		= "";
	String timezone 	= "";
	String url 			= "";
	List<Format> formats = new ArrayList<Format>();
	
	public void addFormat(Format f){
		this.formats.add(f);
	}
	public List<Format> getFormat(){
		return this.formats;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getIntType() {
		return intType;
	}
	public void setIntType(String intType) {
		this.intType = intType;
	}
	public String getDim() {
		return dim;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public void setDim(String dim) {
		this.dim = dim;
	}
	public int getTestmode() {
		return testmode;
	}
	public void setTestmode(int testmode) {
		this.testmode = testmode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
