package com.tgxd.gtw.ListingsApi;

import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class ListingsGetServerUtcInfoActual {
	
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
	
}
