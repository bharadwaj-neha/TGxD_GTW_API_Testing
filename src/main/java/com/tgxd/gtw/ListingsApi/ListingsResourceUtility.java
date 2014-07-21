package com.tgxd.gtw.ListingsApi;

import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ListingsResourceUtility {

	public static String getChannelLineUpsResourceResponse(String locale,int msoID,String postalCode,String countryCode)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Listing/GetChannelLineUps?msoId="+msoID+"&postalCode="+postalCode+"&countryCode="+countryCode;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getChannelLogoResourceResponse(String locale,String source,String provider,String serviceID,String size)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Listing/GetChannelLogo/"+source+"/"+provider+"/"+serviceID+"/"+size;
		System.out.println("Resource Identifier:"+resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getServerUTCInfoResourceResponse(String locale)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Listing/GetServerUtcInfo";
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getServiceDetailsResourceResponse(String locale,String serviceID)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Listing/ServiceDetails/"+serviceID;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String postGetFavoriteProgramTilesResourceResponse(String locale)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Listing/FavoriteChannelThumbnails";
		String postData ="{\"ServiceId\":\"75008\",\"SourceIds\":[\"840|15|75008\",\"23192|443|75008\"],\"DurationInMinutes\":125,\"PageNumber\":0,\"StartTime\":\"PRIMETIME\"}";
		return RequestResponseParserUtility.response_POST(resourceIdentifier, postData);
	}
}
