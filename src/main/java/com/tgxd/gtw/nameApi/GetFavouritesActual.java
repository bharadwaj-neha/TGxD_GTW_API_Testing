package com.tgxd.gtw.nameApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class GetFavouritesActual {
	
	public static long getStatus(JSONObject status)
	{
			return (Long)status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}
	
	public static String getID(JSONObject data)
	{
		String serviceId="null";
		JSONArray itemArray = (JSONArray)data.get("Names");
		int element = GeneralUtilities.generateRandomNumber(itemArray.size()); 
		JSONObject obj = (JSONObject) itemArray.get(element);		
		serviceId=(String)obj.get("Id");

		return serviceId;
	}
	
	public static boolean verifyNotNull(JSONObject data, String dataItem, String [] notNullFields)
	{
		boolean isNull = GeneralUtilities.verifyNotNull(data,dataItem,notNullFields);
		if (isNull == true){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public static boolean verifyDetailUrl(JSONObject data, String serviceID)
	{
		String detailUrl="";
		JSONArray itemArray = (JSONArray)data.get("Names");		
		int element = GeneralUtilities.generateRandomNumber(itemArray.size()); 
		JSONObject obj = (JSONObject) itemArray.get(element);		
		detailUrl=(String)obj.get("DetailUrl");

		if(detailUrl.contains(serviceID)){
			return true;
		}
		else{
			return false;
		}
	}

}
