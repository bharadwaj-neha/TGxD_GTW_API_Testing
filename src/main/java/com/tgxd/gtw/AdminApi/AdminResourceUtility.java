package com.tgxd.gtw.AdminApi;

import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class AdminResourceUtility {
	
	
	public static String getAllMSOProficeRequestResponse(String locale,String hd)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/puma/v1/msos?hd="+hd+"&locale="+locale;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getMSOAppConfigRequestResponse(String locale,String msoProfileId,String deviceType)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/puma/v1/msos/"+msoProfileId+"?locale="+locale+"&devicetype="+deviceType;
		//System.out.println("Resource Identifier:"+resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
}
