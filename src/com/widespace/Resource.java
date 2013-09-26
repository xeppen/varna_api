package com.widespace;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resource {
	String resourceType = "";
	String resourceUrl = "";
	String resourceWidth = "";
	String resourceHeight = "";
	String mimeType = "";
		
	public String getResourceWidth() {
		return resourceWidth;
	}
	public void setResourceWidth(String resourceWidth) {
		this.resourceWidth = resourceWidth;
	}
	public String getResourceHeight() {
		return resourceHeight;
	}
	public void setResourceHeight(String resourceHeight) {
		this.resourceHeight = resourceHeight;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceData) {
		this.resourceUrl = resourceData;
	}
}
