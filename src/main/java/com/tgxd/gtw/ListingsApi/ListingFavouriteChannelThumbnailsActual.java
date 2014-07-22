package com.tgxd.gtw.ListingsApi;

import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class ListingFavouriteChannelThumbnailsActual {

	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}
	public static boolean isNotNull_N(JSONObject data, String dataItem, String [] notNullFields)
	{
		boolean isNotNull = GeneralUtilities.verifyNotNull(data,dataItem,notNullFields);
		if (isNotNull == true){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean checkLocaleInUrls(JSONObject data, String dataItem,
			String[] urlsToTestForLocale) {
		
		boolean isPresent = GeneralUtilities.verifyLocalesInUrl(data, dataItem, urlsToTestForLocale);
		if (isPresent == true){
			return true;
		}
		else{
			return false;
		}
	}

	
}
