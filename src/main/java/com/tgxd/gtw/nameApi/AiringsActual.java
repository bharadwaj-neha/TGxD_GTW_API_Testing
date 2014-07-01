package com.tgxd.gtw.nameApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class AiringsActual {

	public static long getStatus(JSONObject status)
	{
		return (Long) status.get("Code");
	}

	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}

	public static String getServiceID(JSONObject data)
	{
		String serviceId="";
		JSONArray itemArray = (JSONArray)data.get("Programs");		
		int element = GeneralUtilities.generateRandomNumber(itemArray.size()); 
		JSONObject obj = (JSONObject) itemArray.get(element);		
		serviceId=(String)obj.get("ServiceId");

		return serviceId;
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
}
