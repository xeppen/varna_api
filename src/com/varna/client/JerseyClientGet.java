package com.varna.client;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JerseyClientGet {

	public static void main(String[] args) {
		try {
			ClientResponse response;
			WebResource webResource;
			Client client = Client.create();
			ClientConfig cfg = new DefaultClientConfig();
			cfg.getClasses().add(JacksonJsonProvider.class);
			client = Client.create(cfg);
			System.out.println("Startat!");
			
			/*
			//String input = "{\"town\":\"Stockholm\",\"station\":\"Gullmarsplan\",\"type\":\"Biljettkontrollant\",\"decs\":\"4-5 kontrollanter står i östra uppgången!\"}";
			String input = "{\"town\":\"Stockholm\",\"station\":\"T-Centralen\",\"type\":\"Ordningsvakt\",\"decs\":\"5-6 ordningsvakter plockar in bus på gröna perrongen!\"}";
			
			webResource = client.resource("http://localhost:8080/Varna/rest/data/warnings");
			
			// POST method
	        response = webResource.accept("application/json")
	                .type("application/json").post(ClientResponse.class, input);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			*/
			// GET method
			webResource = client
					.resource("http://localhost:8080/Varna/rest/data/stations");
			
			response = webResource.accept("application/json")
					.get(ClientResponse.class);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			
			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);
			
			
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}