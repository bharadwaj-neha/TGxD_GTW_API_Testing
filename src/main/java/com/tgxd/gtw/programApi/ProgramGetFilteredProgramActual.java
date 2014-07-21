package com.tgxd.gtw.programApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class ProgramGetFilteredProgramActual {
	
	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}
	
	public static boolean isNotNull_N(JSONObject data, String [] notNullFields)
	{
		boolean isNull = GeneralUtilities.verifyNotNull(data,notNullFields);
		if (isNull == true){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isServiceIDPresentInDetailUrl(JSONObject data,String serviceId)
	{
		JSONArray itemArray = (JSONArray)data.get("ContentInfoLists");
		JSONObject obj = (JSONObject) itemArray.get(0);
		JSONArray itemArray2 = (JSONArray)obj.get("ProgramTiles");	
		int element = GeneralUtilities.generateRandomNumber(itemArray2.size());
		JSONObject targetObj = (JSONObject) itemArray2.get(element);
		String detailURL=(String)targetObj.get("DetailUrl");
		boolean isServiceIdPresent=detailURL.contains(serviceId);
	
		return isServiceIdPresent;
	}
	

}
