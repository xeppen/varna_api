package com.widespace;

import com.widespace.sql.DBManager;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DBManager manager = new DBManager();
			User user = manager.getUser("test", "test", "test");
		} catch (Exception e) {

		}
	}

}
