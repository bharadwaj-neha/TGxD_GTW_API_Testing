package com.tgxd.gtw.nameApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class CreditsActual {	

	public static boolean getImageUrl(JSONObject data)
	{
		return data.get("ImageUrl")!=null;
	}
	
	public static long getStatus(JSONObject status)
	{
			return (Long)status.get("Code");
	}
	
	public static String getNameID(JSONObject data)
	{
		String ID="null";
		JSONArray itemArray = (JSONArray)data.get("Names");
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		JSONObject obj = (JSONObject) itemArray.get(element);		
		ID=(String)obj.get("Id");

		return ID;
	}
	
	public static String getProgramID(JSONObject data)
	{
		String ID="null";
		System.out.println("Data is: "+data);
		JSONArray itemArray = (JSONArray)data.get("Names");
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		JSONObject obj = (JSONObject) itemArray.get(element);		
		ID=(String)obj.get("ProgramId");

		return ID;
	}
	
	public static String getDetailUrl(JSONObject data)
	{
		String ID="null";
		JSONArray itemArray = (JSONArray)data.get("Names");
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		JSONObject obj = (JSONObject) itemArray.get(element);		
		ID=(String)obj.get("DetailUrl");

		return ID;
	}
	
	public static boolean testLocaleInUrls(JSONObject data,String urlName,String locale)
	{
		String urlElement = getUrl(data,urlName);
		boolean isLocalePresent=urlElement.contains(locale);
		return isLocalePresent;
	}


	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}

	public static boolean testIDInUrls(JSONObject data, String urlName,String serviceID) {
		String urlElement = getUrl(data,urlName);
		boolean isIdPresent=urlElement.contains(serviceID);
		return isIdPresent;
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
	
	
	private static String getUrl(JSONObject data,String urlName) {
		JSONArray itemArray = (JSONArray)data.get("Names");
		int element = GeneralUtilities.generateRandomNumber(itemArray.size());
		JSONObject obj = (JSONObject) itemArray.get(element);
		String urlElement=(String) obj.get(urlName);
		return urlElement;
	}
	
}
