package com.tgxd.gtw.programApi;

import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class ProgramDetailsActual {

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
			
		String Id=(String)data.get("Id");
		//System.out.println("Id is:"+Id);	
		return Id;

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
		String NameIs=(String)data.get("DetailUrl");
		boolean isServiceIDPresent=NameIs.contains(serviceId);
		return isServiceIDPresent;
	}

}
