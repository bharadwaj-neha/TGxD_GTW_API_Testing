package com.tgxd.gtw.aboutApi;

import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

//Test Comment
public class AboutResourceActual {

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
