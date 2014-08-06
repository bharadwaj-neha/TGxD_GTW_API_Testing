package com.tgxd.gtw.AdminApi;

import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class AdminGetMSOAppConfigActual {
	
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
		JSONObject element=(JSONObject)data.get(dataItem);
		boolean isNull = GeneralUtilities.verifyNotNull(element,notNullFields);
		if (isNull == true){
			return true;
		}
		else{
			return false;
		}
	}

}
