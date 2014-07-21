package com.tgxd.gtw.programApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class ProgramAiringsActual {
	
	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}
	
	public static String getServiceId(JSONObject data)
	{
		String serviceId="";
		JSONArray itemArray = (JSONArray)data.get("Programs");		
		System.out.println("Item Array Size:"+itemArray.size());
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		System.out.println("Random number generated is:"+element);
		JSONObject obj = (JSONObject) itemArray.get(element);		
		serviceId=(String)obj.get("ServiceId");
		System.out.println("Name is:"+serviceId);

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
