package com.tgxd.gtw.nameApi;

import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class NameResourceUtility {
	
	public static String getNameResourceResponse(String locale,String serviceID,String celebrityID, String nameResource)
	{ 
		String resourceIdentifier = Constants.environment + locale + nameResource + celebrityID + Constants.provider + serviceID;
		System.out.println(resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String postNameResourceResponse(String locale, String serviceID, String nameResource)
	{
		String resourceIdentifier = Constants.environment + locale + Constants.nameGetFavorites + serviceID + Constants.provider;
		String postData = "{\""+ Constants.celebrityIdentifierList +"\":[\"" + Constants.nameGetFavoritesCelebrityIds[0] + "\",\"" + Constants.nameGetFavoritesCelebrityIds[1] + "\"]}";
		//System.out.println(resourceIdentifier + "Parameters:" +postData);
		return RequestResponseParserUtility.response_POST(resourceIdentifier, postData); 
	}
}
