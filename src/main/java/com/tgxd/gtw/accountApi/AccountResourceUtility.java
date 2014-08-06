package com.tgxd.gtw.accountApi;

import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class AccountResourceUtility {

	public static String getNameResourceResponse(String locale,String serviceID,String celebrityID, String nameResource)
	{ 
		String resourceIdentifier = Constants.environment + locale + nameResource + celebrityID + Constants.provider + serviceID;
		//System.out.println(resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String postAccountCreate(String locale)
	{
		long systemTime = System.currentTimeMillis();
		String time = String.valueOf(systemTime);
		//System.out.println(time);
		//String resourceIdentifier = Constants.environment + locale + Constants.nameGetFavorites + serviceID + Constants.provider;
		//String postData = "{\""+ Constants.celebrityIdentifierList +"\":[\"" + Constants.nameGetFavoritesCelebrityIds[0] + "\",\"" + Constants.nameGetFavoritesCelebrityIds[1] + "\"]}";
		String resourceIdentifier = "http://qa-g.ceidd.net/Puma/en-US/Account/CreateAccount";

		String postData ="{\"DisplayName\":\"" + time +
				"\"," +
				"\"Email\":\"" + time +
				"@gmail.com\"," +
				"\"UserName\":\"" + time +
				"\"," +
				"\"Password\":\"" + time +
				"\"}";
		//System.out.println(resourceIdentifier + "Parameters:" +postData);
		return RequestResponseParserUtility.response_POST(resourceIdentifier, postData); 
	}
	
	public static String postAccountAuthenticate(String locale)
	{
		//String resourceIdentifier = Constants.environment + locale + Constants.nameGetFavorites + serviceID + Constants.provider;
		//String postData = "{\""+ Constants.celebrityIdentifierList +"\":[\"" + Constants.nameGetFavoritesCelebrityIds[0] + "\",\"" + Constants.nameGetFavoritesCelebrityIds[1] + "\"]}";
		String resourceIdentifier = "http://qa-g.ceidd.net/puma/v2.2/account/authenticate?expand=service";
		String postData ="{\"UserName\":\"tgxd_test\"," +
				"\"Password\":\"tgxd_test\"," +
				"\"ClientUID\":\"0\"}";
		//System.out.println(resourceIdentifier + "Parameters:" +postData);
		return RequestResponseParserUtility.response_POST(resourceIdentifier, postData); 
	}
	
	public static String postAccountDevices(String locale)
	{
		//String resourceIdentifier = Constants.environment + locale + Constants.nameGetFavorites + serviceID + Constants.provider;
		//String postData = "{\""+ Constants.celebrityIdentifierList +"\":[\"" + Constants.nameGetFavoritesCelebrityIds[0] + "\",\"" + Constants.nameGetFavoritesCelebrityIds[1] + "\"]}";
		String resourceIdentifier = "http://qa-g.ceidd.net/puma/v2.2/account/devices";
		String postData ="{\"UserName\":\"tgxd_test\"," +
				"\"Password\":\"tgxd_test\"," +
				"\"ClientUID\":\"0\"}";
		//System.out.println(resourceIdentifier + "Parameters:" +postData);
		return RequestResponseParserUtility.response_POST(resourceIdentifier, postData); 
	}
}
