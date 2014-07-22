package com.tgxd.gtw.ListingsApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class ListingGetChannelLineUpsActual {
	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}
	

	public static String getPostalCode(JSONObject data)
	{
		String postalCode="";
		JSONArray itemArray = (JSONArray)data.get("Services");		
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		JSONObject obj = (JSONObject) itemArray.get(element);		
		postalCode=(String)obj.get("PostalCode");

		return postalCode;
	}
	
	public static boolean isNotNull_N(JSONObject data, String dataItem, String [] notNullFields)
	{
		boolean isNull = GeneralUtilities.verifyNotNull(data,dataItem,notNullFields);
		if (isNull == true){
			return true;
		}
		else{
			return false;
		}
	}

	public static String getPostalCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
