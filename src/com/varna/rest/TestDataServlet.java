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

import com.varna.Station;
import com.varna.Stations;
import com.varna.Warning;
import com.varna.sql.DBManager;

@Path("/data")
public class TestDataServlet {
	DBManager manager = new DBManager();

	@POST
	@Path("/warning")
	@Consumes("application/json")
	public Response postWarning(Warning war) {
		manager.insertWarning(war);
		return Response.ok(200).build();
	}

	@GET
	@Path("/warning/{id}")
	public Response getWarning(@QueryParam("id") String id) throws Exception {
		Warning war = new Warning();
		war = manager.getWarning(id);
		return Response.ok(200).entity(war).build();
	}

	@GET
	@Path("/warnings")
	public Response getWarnings(
			@DefaultValue("5") @QueryParam("amount") int amount, @QueryParam("stations") String s)
			throws Exception {
		String[] _stations = parseStations(s);
		List<Warning> wars = new ArrayList<Warning>();
		wars = manager.getWarnings(amount, _stations);
		return Response.ok(200).entity(wars).build();
	}

	@GET
	@Path("/stations")
	public Response getStations(@QueryParam("name") String name,
			@QueryParam("town") String town, @QueryParam("type") String type,
			@QueryParam("line") String line) throws Exception {
		List<Station> _stations = new ArrayList<Station>();
		_stations = manager.getStations(name, town, type, line);
		return Response.ok(200).entity(_stations).build();
	}
	
	private String[] parseStations(String stations){
		String[] _stations = stations.split(",");
		return _stations;
	}

}
