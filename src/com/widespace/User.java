package com.widespace;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	String fname 		= null;
	String lname 		= null;
	String password 	= null;
	String email 		= null;
	String type		 	= null;
	
	public void setFname(String fname){
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
