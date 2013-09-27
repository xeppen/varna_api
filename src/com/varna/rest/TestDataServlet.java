package com.varna.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import com.varna.Warning;
import com.varna.sql.DBManager;

@Path("/data")
public class TestDataServlet {
	DBManager manager = new DBManager();
	
	@POST
	@Path("/warnings")
	@Consumes("application/json") 
	public Response postWarning(Warning war) {
		manager.insertWarning(war);
		return Response.ok(200).build();
	}
	
	
	@GET
	@Path("/warning")
	public Response getWarning() throws Exception{
		Warning war = new Warning();
		war = manager.getWarning();
		return Response.ok(200).entity(war).build();
	}
	
	@GET
	@Path("/warnings")
	public Response getWarnings(@DefaultValue("5") @QueryParam("amount") int amount) throws Exception{
		
		List<Warning> wars = new ArrayList<Warning>();
		wars = manager.getWarnings(amount);
		return Response.ok(200).entity(wars).build();
	}
	/*
	
	@GET
	@Path("/users")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUser(@QueryParam("type") String type,
			@DefaultValue("1") @QueryParam("count") Integer count,
			@QueryParam("fname") String fname,
			@QueryParam("lname") String lname) throws Exception {

		User user = new User();
		user = fetchUser(type,count,fname,lname);
		if(user == null){
			String response = "No matching content found!";
			return Response.status(404).entity(response).build();
		}
		
		return Response.ok(200).entity(user).build();
	}
	private User fetchUser(String type, Integer count, String fname, String lname) throws Exception {
		User user = new User();
			
		DBManager manager = new DBManager();
		user = manager.getUser(type, fname, lname);
		return user;
	}
	 
	@GET
	@Path("/ads")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAd(@QueryParam("name") String name,
			@QueryParam("platform") String platform,
			@QueryParam("adType") String adType,
			@QueryParam("target") String target,
			@QueryParam("cat") String cat) throws Exception {

		Ad ad = new Ad();
		
		ad = fetchAd(name, platform, adType, target, cat);
		if(ad == null){
			String response = "No matching content found!";
			return Response.status(404).entity(response).build();
		}
		return Response.ok(200).entity(ad).build();
	}
	
	@POST
	@Path("/ads")
	@Consumes("application/json") 
	public Response postAd(Ad a) {
		insertAd(a);
		return Response.ok(200).build();
	}
	private void insertAd(Ad a) {
		DBManager manager = new DBManager();
		manager.insertAd(a);
	}
	private Ad fetchAd(String name, String platform, String adType, String target, String cat) throws Exception {
		Ad ad = new Ad();
			
		DBManager manager = new DBManager();
		ad = manager.getAd(name, platform, adType, target, cat);
		
		System.out.println("ad.Resource: " + ad.getResource().size());
		return ad;
	}
	
	@GET
	@Path("/adspaces")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAdSpace(@QueryParam("name") String name,
			@QueryParam("platform") String platform,
			@QueryParam("intType") String intType,
			@QueryParam("appType") String appType,
			@DefaultValue("1") @QueryParam("testmode") int testmode,
			@QueryParam("country") String country,
			@QueryParam("channel") String channel) throws Exception {

		AdSpace adspace = new AdSpace();
		
		adspace = fetchAdSpace(name, platform, appType, intType, testmode, country, channel);
		if(adspace == null){
			String response = "No matching content found!";
			return Response.status(404).entity(response).build();
		}
		return Response.ok(200).entity(adspace).build();
	}
	private AdSpace fetchAdSpace(String name, String platform, String appType, String intType, int testmode, String country, String channel) throws Exception {
		AdSpace adspace = new AdSpace();
			
		DBManager manager = new DBManager();
		adspace = manager.getAdSpace(name, platform, appType, intType, testmode, country, channel);
		
		return adspace;
	}
	
	public void insertUser(User u){
		DBManager manager = new DBManager();
		manager.insertUser(u);
	}
	*/
}
