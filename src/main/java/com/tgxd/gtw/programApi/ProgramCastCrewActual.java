package com.tgxd.gtw.programApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class ProgramCastCrewActual {
	
	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}
	
	public static String getProgramId(JSONObject data)
	{
		String programId="";
		JSONArray itemArray = (JSONArray)data.get("Names");		
		//System.out.println("Item Array Size:"+itemArray.size());
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		//System.out.println("Random number generated is:"+element);
		JSONObject obj = (JSONObject) itemArray.get(element);		
		String NameIs=(String)obj.get("Name");
		//System.out.println("Name is:"+NameIs);
		programId=(String)obj.get("ProgramId");		

		return programId;

	}
	
	public static boolean isNotNull_N(JSONObject data,String dataItem ,String [] notNullFields)
	{
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
		JSONArray itemArray = (JSONArray)data.get("Names");		
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		JSONObject obj = (JSONObject) itemArray.get(element);		
		String NameIs=(String)obj.get("DetailUrl");
		boolean isServiceIDPresent=NameIs.contains(serviceId);
		return isServiceIDPresent;
	}

}
