package com.tgxd.gtw.utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RequestResponseParserUtility {

		public static JSONObject parseResponseData(String response)
		{			
			JSONObject jsonObject = getResponse(response);
			JSONObject data=(JSONObject) jsonObject.get("Data");
			return data;
		}


		public static JSONObject parseResponseStatus(String response)
		{
			JSONObject jsonObject = getResponse(response);
			JSONObject status=(JSONObject) jsonObject.get("Status");
			return status;
		}
		
		public static String response_GET(String resourceIdentifier) {
			Client client = Client.create();
			WebResource webResource = client.resource(resourceIdentifier);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			String output = response.getEntity(String.class);
			
			return output;
		}
		
		public static String response_POST(String resourceIdentifer,String postData){
			Client client = Client.create();
			WebResource webResource = client.resource(resourceIdentifer);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, postData);
			String output = response.getEntity(String.class);
			
			return output;
			
		}
		
		private static JSONObject getResponse(String response) {
			JSONParser jsonParser=new JSONParser();
			JSONObject jsonObject = null;
			try {
				jsonObject = (JSONObject)jsonParser.parse(response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return jsonObject;
		}

	}



