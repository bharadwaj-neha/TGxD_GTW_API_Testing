package com.tgxd.gtw.SearchApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class SearchResultsActual {
	
	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}
	
	public static boolean isNotNull_N(JSONObject data,String dataItem ,String [] notNullFields)
	{
		System.out.println("Dara"+data);
		boolean isNull = GeneralUtilities.verifyNotNull(data,dataItem,notNullFields);
		if (isNull == true){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isServiceIDPresentInDetailUrl(JSONObject data,String serviceId)
	{
		JSONArray itemArray = (JSONArray)data.get("Results");		
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		JSONObject obj = (JSONObject) itemArray.get(element);
		String NameIs=(String)obj.get("DetailUrl");
		boolean isServiceIDPresent=NameIs.contains(serviceId);
		return isServiceIDPresent;
	}

}
