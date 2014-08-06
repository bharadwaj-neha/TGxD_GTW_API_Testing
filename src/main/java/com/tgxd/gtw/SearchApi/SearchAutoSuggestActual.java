package com.tgxd.gtw.SearchApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class SearchAutoSuggestActual {

	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}

	public static boolean isQueryValueNotPresntInResults_N(JSONObject data,String query) {
	
		
		JSONArray itemArray = (JSONArray)data.get("Results");		
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		String results= (String) itemArray.get(element);
		boolean isQueryAbsent=!results.contains(query);
		return isQueryAbsent;
	}
	
}
