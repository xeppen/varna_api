package com.varna;

import javax.xml.bind.annotation.XmlElement;

public class Stations {
	@XmlElement(name = "stations")
	private String[] _stations;
	
	public Stations(){}
	
	final public void print() {
		for (String str : _stations) {
			System.out.println("Town: " + str);
		}
	}
	
	final public int size(){
		return _stations.length;
	}
	
	final public String get(int i){
		return _stations[i];
	}
	
	final public String[] getAll(){
		return _stations;
	}

}
