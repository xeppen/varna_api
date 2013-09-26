package com.widespace.client;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JerseyClientGet {

	public static void main(String[] args) {
		try {

			Client client = Client.create();
			ClientConfig cfg = new DefaultClientConfig();
			cfg.getClasses().add(JacksonJsonProvider.class);
			client = Client.create(cfg);
			
			WebResource webResource = client
					.resource("http://localhost:8080/SimpleRestWeb/rest/data/ads");
			
			String input = "{\"name\":\"Interstitial Ad - Mr Universe\",\"platform\":\"Mobile\",\"type\":\"Interstitial\",\"url\":\"http://www.widespace.com\",\"target\":\"internal\",\"category\":\"Fitness and health\",\"resource\":[{\"resourceType\":\"image\",\"resourceUrl\":\"http://playground.w-s.nu/seb/pic/arnold_640x960.jpg\",\"resourceWidth\":\"640\",\"resourceHeight\":\"960\",\"mimeType\":\"image/jpeg\"}]}";
			
			// POST method
	        ClientResponse response = webResource.accept("application/json")
	                .type("application/json").post(ClientResponse.class, input);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			
			// GET method
			webResource = client
					.resource("http://localhost:8080/SimpleRestWeb/rest/data/ads?type=Interstitial");
			
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