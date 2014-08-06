package com.tgxd.gtw.SearchApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class SearchProgramInfoActual {
	
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
		JSONArray itemArray1 = (JSONArray)data.get("ContentInfoLists");
		JSONObject contentInfoLists=(JSONObject) itemArray1.get(0);
		//JSONArray itemArray = (JSONArray)contentInfoLists.get("ProgramInfos");	
		//int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		//JSONObject programInfos = (JSONObject) itemArray.get(element);
		
		System.out.println("Dara"+contentInfoLists);
		boolean isNull = GeneralUtilities.verifyNotNull(contentInfoLists,dataItem,notNullFields);
		System.out.println("Null"+isNull);
		if (isNull == true){
			System.out.println("It is null");
			return true;
		}
		else{
			System.out.println("It is not null");
			return false;
		}
	}
	
	public static boolean isServiceIDPresentInDetailUrl(JSONObject data,String serviceId) {
		boolean isServiceIDPresent;
	
		    JSONArray itemArray1 = (JSONArray)data.get("ContentInfoLists");
			JSONObject contentInfoLists=(JSONObject) itemArray1.get(0);
			JSONArray itemArray = (JSONArray)contentInfoLists.get("ProgramInfos");	
			int element = GeneralUtilities.generateRandomNumber(itemArray.size());
			JSONObject programInfos = (JSONObject) itemArray.get(element);
			String detailUrl=(String)programInfos.get("DetailUrl");
			isServiceIDPresent=detailUrl.contains(serviceId);
		
		return isServiceIDPresent;
	}

}
