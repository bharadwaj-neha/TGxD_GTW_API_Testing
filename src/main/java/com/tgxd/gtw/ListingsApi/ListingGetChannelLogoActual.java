package com.tgxd.gtw.ListingsApi;

import org.json.simple.JSONObject;


public class ListingGetChannelLogoActual {
	
	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static String getData(String response)
	{
		String[] data;
			
		data=response.split("Data");
		System.out.println("Data is"+data[1]);

		return data[1];
	}


}
