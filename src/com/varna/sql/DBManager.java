package com.varna.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.varna.Station;
import com.varna.Stations;
import com.varna.Warning;

public class DBManager {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private String DATABASEURL = "mysql://xeppen.zapto.org:3306/varna";
	private String DBUSER = "varna";
	private String DBUSERPW = "VarnaAPI28108";

	public DBManager() {
	}

	public void insertWarning(Warning w) {
		System.out.println("[DBManager] Inserting warning!");
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
			}

			try {
				connect = DriverManager.getConnection("jdbc:" + DATABASEURL,
						DBUSER, DBUSERPW);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
			}

			if (connect != null) {
				System.out
						.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}

			// --- Build query ---//

			String town = w.getTown();
			String name = w.getStation();
			String type = w.getType();
			String desc = w.getDecs();

			String query = "('" + town + "', '" + name + "', '" + type + "', '"
					+ desc + "')";
			// -------------------//

			// --- Generate random user ---//
			statement = connect.createStatement();
			String eQuery = "INSERT INTO Warnings (town, station, type, descript) VALUES ";
			eQuery = eQuery + query;
			statement.execute(eQuery);
			System.out.println("[DBManager] Warning submitted");
			// ----------------------------//

		} catch (Exception e) {
			System.out.println("[DBManager] Something wrong: insertWarning()!");
			System.out.println(e);
		} finally {
			close();
		}

	}

	public Warning getWarning(String id) throws Exception {
		System.out.println("[DBManager] Fetching warning!");
		try {
			Warning war = new Warning();

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return war;
			}

			try {
				connect = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/varna",
								"root", "WW2a69ka");

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return war;
			}

			if (connect != null) {
				System.out
						.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}

			// --- Build query ---//
			String query = "";
			if (id != null) {
				query = "WHERE id = " + id;
			}
			// -------------------//

			// --- Fetch data ---//
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			String eQuery = "SELECT * FROM Warnings";
			eQuery = eQuery + query;
			resultSet = statement.executeQuery(eQuery);
			int i = 1;
			while (resultSet.next()) {
				war.setTown(resultSet.getString(2));
				war.setStation(resultSet.getString(3));
				war.setType(resultSet.getString(4));
				war.setDecs(resultSet.getString(5));
				i++;
			}
			// -------------------//
			return war;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	public List<Warning> getWarnings(int amount, String[] s) throws Exception {
		System.out.println("[DBManager] Fetching warnings!");
		try {
			List<Warning> wars = new ArrayList<Warning>();

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return wars;
			}

			try {
				connect = DriverManager.getConnection("jdbc:" + DATABASEURL,
						DBUSER, DBUSERPW);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return wars;
			}

			if (connect != null) {
				System.out
						.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}

			// --- Build query --- //
			String query = "";
			if (s.length > 0) {
				query = " WHERE station LIKE '" + s[0] + "'";
				if (s.length > 1) {
					for (int i = 1; i < s.length; i++) {
						query = query + " OR station LIKE '" + s[i] + "'";
					}
				}
			}

			// ------------------- //

			// --- Fetch data ---//
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			String eQuery = "SELECT * FROM Warnings" + query
					+ " ORDER BY created DESC";
			System.out.println("eQuery: " + eQuery);
			resultSet = statement.executeQuery(eQuery);
			int p = 0;
			while (resultSet.next() && p < amount) {
				Warning war = new Warning();
				war.setTown(resultSet.getString(2));
				war.setStation(resultSet.getString(3));
				war.setType(resultSet.getString(4));
				war.setDecs(resultSet.getString(5));
				war.setCreated(resultSet.getString(6));
				wars.add(war);
				System.out.println("war.getStation(): " + war.getStation());
				p++;
			}
			// -------------------//
			return wars;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	public List<Station> getStations(String name, String town, String type,
			String line) throws Exception {
		System.out.println("[DBManager] Fetching stations");
		try {
			List<Station> _stations = new ArrayList<Station>();
			Station station = new Station();

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return _stations;
			}

			try {
				connect = DriverManager.getConnection("jdbc:" + DATABASEURL,
						DBUSER, DBUSERPW);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return _stations;
			}

			if (connect != null) {
				System.out
						.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}

			// --- Build query ---//
			String query = "";
			boolean first = true;
			if (name != null) {
				if (first) {
					query = query + " WHERE station LIKE '" + name + "'";
					first = false;
				}
			}
			if (type != null) {
				if (first) {
					query = query + " WHERE type LIKE '" + type + "'";
					first = false;
				} else {
					query = query + " AND type LIKE '" + type + "'";
				}
			}
			if (town != null) {
				if (first) {
					query = query + " WHERE town LIKE '" + town + "'";
					first = false;
				} else {
					query = query + " AND town LIKE '" + town + "'";
				}
			}
			if (line != null) {
				if (first) {
					query = query + " WHERE line LIKE '" + line + "'";
					first = false;
				} else {
					query = query + " AND line LIKE '" + line + "'";
				}
			}
			// -------------------//

			// --- Fetch data ---//
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			String eQuery = "SELECT * FROM Stations";
			eQuery = eQuery + query;
			System.out.println("eQuery: " + eQuery);
			resultSet = statement.executeQuery(eQuery);
			while (resultSet.next()) {
				Station s = new Station();
				s.setStation(resultSet.getString(2));
				s.setType(resultSet.getString(3));
				s.setTown(resultSet.getString(4));
				s.setLine(resultSet.getString(5));
				_stations.add(s);
			}
			// -------------------//
			System.out.println("F�rsta: " + _stations.get(2).getStation());
			System.out.println("Andra: " + _stations.get(3).getStation());
			System.out.println("Tredje: " + _stations.get(4).getStation());
			System.out.println("Fj�rde: " + _stations.get(5).getStation());
			return _stations;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
	/*
	 * public User getUser(String type, String fname, String lname) throws
	 * Exception { System.out.println("#####   FETCHING USER   #####"); try {
	 * User user = new User();
	 * 
	 * try { Class.forName("com.mysql.jdbc.Driver"); } catch
	 * (ClassNotFoundException e) {
	 * System.out.println("Where is your MySQL JDBC Driver?");
	 * e.printStackTrace(); return user; }
	 * 
	 * try { connect = DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3306/widespace", "root", "WW2a69ka");
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Connection Failed! Check output console");
	 * e.printStackTrace(); return user; }
	 * 
	 * if (connect != null) { System.out
	 * .println("You made it, take control your database now!"); } else {
	 * System.out.println("Failed to make connection!"); }
	 * 
	 * // --- Build query ---// String query = ""; boolean first = true; if
	 * (fname != null) { if (first) { query = query + " WHERE fname LIKE '" +
	 * fname + "'"; first = false; } } if (type != null) { if (first) { query =
	 * query + " WHERE type LIKE '" + type + "'"; first = false; } else { query
	 * = query + " AND type LIKE '" + type + "'"; } } if (lname != null) { if
	 * (first) { query = query + " WHERE lname LIKE '" + lname + "'"; first =
	 * false; } else { query = query + " AND lname LIKE '" + lname + "'"; } } //
	 * -------------------//
	 * 
	 * // --- Generate random user ---// statement = connect.createStatement();
	 * String eQuery = "SELECT COUNT(*) AS AvailableUsers FROM Users"; eQuery =
	 * eQuery + query; resultSet = statement.executeQuery(eQuery);
	 * 
	 * Integer AvailableUsers = 0; if (resultSet.next()) { AvailableUsers =
	 * Integer.parseInt(resultSet.getString(1)); } Integer count = (int)
	 * Math.round(Math.random() (AvailableUsers - 1) + 1); //
	 * ----------------------------//
	 * 
	 * // --- Fetch data ---// if (AvailableUsers > 0) { // Statements allow to
	 * issue SQL queries to the database statement = connect.createStatement();
	 * // Result set get the result of the SQL query eQuery =
	 * "SELECT * FROM Users"; eQuery = eQuery + query; resultSet =
	 * statement.executeQuery(eQuery); int i = 1; while (resultSet.next()) { if
	 * (i == count) { user.setEmail(resultSet.getString(2)); // email
	 * user.setFname(resultSet.getString(3)); // fname
	 * user.setLname(resultSet.getString(4)); // fname
	 * user.setType(resultSet.getString(5)); // fname
	 * user.setPassword(resultSet.getString(6)); // password } i++; } //
	 * -------------------// return user; } else { return null; } } catch
	 * (Exception e) { throw e; } finally { close(); } }
	 * 
	 * public Ad getAd(String name, String platform, String adType, String
	 * target, String cat) throws Exception {
	 * System.out.println("#####   FETCHING AD   #####"); try { Ad ad = new
	 * Ad();
	 * 
	 * try { Class.forName("com.mysql.jdbc.Driver"); } catch
	 * (ClassNotFoundException e) {
	 * System.out.println("Where is your MySQL JDBC Driver?");
	 * e.printStackTrace(); return ad; }
	 * 
	 * try { connect = DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3306/widespace", "root", "WW2a69ka");
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Connection Failed! Check output console");
	 * e.printStackTrace(); return ad; }
	 * 
	 * if (connect != null) { System.out
	 * .println("You made it, take control your database now!"); } else {
	 * System.out.println("Failed to make connection!"); }
	 * 
	 * // --- Build query ---// String query = ""; boolean first = true; if
	 * (name != null) { if (first) { query = query + " WHERE name LIKE '" + name
	 * + "'"; first = false; } } if (platform != null) { if (first) { query =
	 * query + " WHERE platform LIKE '" + platform + "'"; first = false; } else
	 * { query = query + " AND platform LIKE '" + platform + "'"; } } if (adType
	 * != null) { if (first) { query = query + " WHERE adType LIKE '" + adType +
	 * "'"; first = false; } else { query = query + " AND adType LIKE '" +
	 * adType + "'"; } } if (target != null) { if (first) { query = query +
	 * " WHERE target LIKE '" + target + "'"; first = false; } else { query =
	 * query + " AND target LIKE '" + target + "'"; } } if (cat != null) { if
	 * (first) { query = query + " WHERE cat LIKE '" + cat + "'"; first = false;
	 * } else { query = query + " AND cat LIKE '" + cat + "'"; } } //
	 * -------------------//
	 * 
	 * // --- Generate random user ---// statement = connect.createStatement();
	 * String eQuery = "SELECT COUNT(*) AS AvailableAds FROM Ad"; eQuery =
	 * eQuery + query; resultSet = statement.executeQuery(eQuery);
	 * 
	 * Integer AvailableAds = 0; if (resultSet.next()) { AvailableAds =
	 * Integer.parseInt(resultSet.getString(1)); } Integer count = (int)
	 * Math.round(Math.random() * (AvailableAds - 1) + 1); //
	 * ----------------------------//
	 * 
	 * // --- Fetch data ---// if (AvailableAds > 0) { // Statements allow to
	 * issue SQL queries to the database statement = connect.createStatement();
	 * // Result set get the result of the SQL query eQuery =
	 * "SELECT * FROM Ad"; eQuery = eQuery + query; resultSet =
	 * statement.executeQuery(eQuery); int i = 1; String id; while
	 * (resultSet.next()) { if (i == count) { id = resultSet.getString(1);
	 * ad.setName(resultSet.getString(2)); // email
	 * ad.setPlatform(resultSet.getString(3)); // fname
	 * ad.setType(resultSet.getString(4)); // fname
	 * ad.setTarget(resultSet.getString(5)); // fname
	 * ad.setCategory(resultSet.getString(6)); // password Resource r = new
	 * Resource();
	 * 
	 * eQuery = "SELECT * FROM Ad_Resource WHERE adId = " + id; resultSet =
	 * statement.executeQuery(eQuery); while (resultSet.next()) { Resource res =
	 * new Resource(); res.setResourceType(resultSet.getString(3));
	 * res.setResourceUrl(resultSet.getString(6));
	 * res.setResourceWidth(resultSet.getString(9));
	 * res.setResourceHeight(resultSet.getString(10));
	 * res.setMimeType(resultSet.getString(13)); ad.addResource(res); } } i++; }
	 * // -------------------// return ad; } else { return null; } } catch
	 * (Exception e) { throw e; } finally { close(); } }
	 * 
	 * public AdSpace getAdSpace(String name, String platform, String appType,
	 * String intType, int testmode, String country, String channel) throws
	 * Exception { System.out.println("#####   FETCHING ADSPACE   #####"); try {
	 * AdSpace adspace = new AdSpace();
	 * 
	 * try { Class.forName("com.mysql.jdbc.Driver"); } catch
	 * (ClassNotFoundException e) {
	 * System.out.println("Where is your MySQL JDBC Driver?");
	 * e.printStackTrace(); return adspace; }
	 * 
	 * try { connect = DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3306/widespace", "root", "WW2a69ka");
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Connection Failed! Check output console");
	 * e.printStackTrace(); return adspace; }
	 * 
	 * if (connect != null) { System.out
	 * .println("You made it, take control your database now!"); } else {
	 * System.out.println("Failed to make connection!"); }
	 * 
	 * // --- Build query ---// String query = ""; boolean first = true; if
	 * (name != null) { if (first) { query = query + " WHERE name LIKE '" + name
	 * + "'"; first = false; } } if (platform != null) { if (first) { query =
	 * query + " WHERE platform LIKE '" + platform + "'"; first = false; } else
	 * { query = query + " AND platform LIKE '" + platform + "'"; } } if
	 * (appType != null) { if (first) { query = query + " WHERE appType LIKE '"
	 * + appType + "'"; first = false; } else { query = query +
	 * " AND appType LIKE '" + appType + "'"; } } if (intType != null) { if
	 * (first) { query = query + " WHERE intType LIKE '" + intType + "'"; first
	 * = false; } else { query = query + " AND intType LIKE '" + intType + "'";
	 * } } if (country != null) { if (first) { query = query +
	 * " WHERE country LIKE '" + country + "'"; first = false; } else { query =
	 * query + " AND country LIKE '" + country + "'"; } } if (channel != null) {
	 * if (first) { query = query + " WHERE channel LIKE '" + channel + "'";
	 * first = false; } else { query = query + " AND channel LIKE '" + channel +
	 * "'"; } } if (testmode != 1) { if (first) { query = query +
	 * " WHERE testmode = 0"; first = false; } else { query = query +
	 * " AND testmode = 0"; } } // -------------------//
	 * 
	 * // --- Generate random adspace ---// statement =
	 * connect.createStatement(); String eQuery =
	 * "SELECT COUNT(*) AS AvailableAdSpaces FROM AdSpace"; eQuery = eQuery +
	 * query; System.out.println("eQuery = " + eQuery); resultSet =
	 * statement.executeQuery(eQuery);
	 * 
	 * Integer AvailableAdSpaces = 0; if (resultSet.next()) { AvailableAdSpaces
	 * = Integer.parseInt(resultSet.getString(1)); } Integer count = (int)
	 * Math.round(Math.random() (AvailableAdSpaces - 1) + 1); //
	 * ----------------------------//
	 * 
	 * // --- Fetch data ---// if (AvailableAdSpaces > 0) { // Statements allow
	 * to issue SQL queries to the database statement =
	 * connect.createStatement(); // Result set get the result of the SQL query
	 * eQuery = "SELECT * FROM AdSpace"; eQuery = eQuery + query; resultSet =
	 * statement.executeQuery(eQuery); int i = 1; String id; while
	 * (resultSet.next()) { if (i == count) { id = resultSet.getString(1);
	 * adspace.setName(resultSet.getString(2));
	 * adspace.setPlatform(resultSet.getString(3)); // platform
	 * adspace.setIntType(resultSet.getString(4)); // inttype
	 * adspace.setDim(resultSet.getString(5)); // dim
	 * adspace.setTestmode(resultSet.getInt(6)); // testmode
	 * adspace.setCountry(resultSet.getString(7)); // country
	 * adspace.setTimezone(resultSet.getString(8)); // timezone
	 * adspace.setChannel(resultSet.getString(9)); // channel
	 * adspace.setUrl(resultSet.getString(10)); // url Resource r = new
	 * Resource();
	 * 
	 * eQuery = "SELECT * FROM AdSpace_Format WHERE adspaceId = " + id;
	 * resultSet = statement.executeQuery(eQuery); while (resultSet.next()) {
	 * Format form = new Format(); form.setFormat(resultSet.getString(3));
	 * adspace.addFormat(form); } } i++; } // -------------------// return
	 * adspace; } else { return null; } } catch (Exception e) { throw e; }
	 * finally { close(); } }
	 * 
	 * public void insertUser(User u) {
	 * System.out.println("#####   INSERTING USER   #####"); try { try {
	 * Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException
	 * e) { System.out.println("Where is your MySQL JDBC Driver?");
	 * e.printStackTrace(); }
	 * 
	 * try { connect = DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3306/widespace", "root", "WW2a69ka");
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Connection Failed! Check output console");
	 * e.printStackTrace(); }
	 * 
	 * if (connect != null) { System.out
	 * .println("You made it, take control your database now!"); } else {
	 * System.out.println("Failed to make connection!"); }
	 * 
	 * // --- Build query ---// String fname = u.getFname(); String email =
	 * u.getEmail(); String lname = u.getLname(); String pass = u.getPassword();
	 * String type = u.getType(); String query = "('" + email + "', '" + fname +
	 * "', '" + lname + "', '" + type + "', '" + pass + "')";
	 * 
	 * // -------------------//
	 * 
	 * // --- Generate random user ---// statement = connect.createStatement();
	 * String eQuery =
	 * "INSERT INTO Users (email, fname, lname, type, password) VALUES "; eQuery
	 * = eQuery + query; System.out.println("eQuery: " + eQuery);
	 * statement.execute(eQuery);
	 * 
	 * // ----------------------------//
	 * 
	 * } catch (Exception e) {
	 * System.out.println("Something wrong: insertUser()!");
	 * System.out.println(e); } finally { close(); }
	 * 
	 * }
	 * 
	 * 
	 * public void insertAd(Ad a) {
	 * System.out.println("#####   INSERTING AD   #####"); try { try {
	 * Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException
	 * e) { System.out.println("Where is your MySQL JDBC Driver?");
	 * e.printStackTrace(); }
	 * 
	 * try { connect = DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3306/widespace", "root", "WW2a69ka");
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Connection Failed! Check output console");
	 * e.printStackTrace(); }
	 * 
	 * if (connect != null) { System.out
	 * .println("You made it, take control your database now!"); } else {
	 * System.out.println("Failed to make connection!"); }
	 * 
	 * // --- Build query ---// Integer id = 0; String name = a.getName();
	 * String platform = a.getPlatform(); String type = a.getType(); String url
	 * = a.getUrl(); String target = a.getTarget(); String category =
	 * a.getCategory(); List<Resource> r = a.getResource(); String query = "('"
	 * + name + "', '" + platform + "', '" + type + "', '" + url + "', '" +
	 * target + "', '" + category + "')"; // -------------------//
	 * 
	 * // --------- Insert ad --------// statement = connect.createStatement();
	 * String eQuery =
	 * "INSERT INTO Ad (name, platform, adType, url, target, category) VALUES ";
	 * eQuery = eQuery + query; statement.execute(eQuery); //
	 * ----------------------------//
	 * 
	 * // ----- Get ad id ------// eQuery =
	 * "SELECT id FROM Ad WHERE name LIKE '" + name + "' AND platform LIKE '" +
	 * platform + "' AND url LIKE '" + url + "'"; resultSet =
	 * statement.executeQuery(eQuery);
	 * 
	 * if (resultSet.next()) { id = Integer.parseInt(resultSet.getString(1)); }
	 * // ----------------------//
	 * 
	 * // ----- Insert resources -----// eQuery =
	 * "INSERT INTO Ad_Resource (adId, resourceType, resourceUrl, resourceWidth, resourceHeight, mimeType) VALUES "
	 * ; for(int i = 0; i < r.size(); i++){ String rT =
	 * r.get(i).getResourceType(); String rU = r.get(i).getResourceUrl(); String
	 * rW = r.get(i).getResourceWidth(); String rH =
	 * r.get(i).getResourceHeight(); String mT = r.get(i).getMimeType(); query =
	 * "(" + id + ", '" + rT + "', '" + rU + "', '" + rW + "', '" + rH + "', '"
	 * + mT + "')"; eQuery = eQuery + query; System.out.println("r" + i + ": " +
	 * eQuery); statement.execute(eQuery); } // ----------------------------//
	 * 
	 * } catch (Exception e) { System.out.println("Error: " + e); } finally {
	 * close(); }
	 * 
	 * }
	 */

}
