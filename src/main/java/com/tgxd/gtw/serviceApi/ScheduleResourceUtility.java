package com.tgxd.gtw.serviceApi;

import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ScheduleResourceUtility {

	public static String getScheduleResourceResponse(String locale, String serviceID,String startTime, String durationInMin)
	{ 
		String resourceIdentifier = Constants.environment + "v1/service/" + serviceID + "/schedule?locale=" + locale + "&startTime=" + startTime + "&durationInMinutes=" + durationInMin;
		//System.out.println(resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getSourceScheduleResourceResponse(String locale,String serviceID, String sourceId, String startTime, String durationInMin)
	{ 
		String resourceIdentifier = Constants.environment + "v1/service/" + serviceID + "/schedule/source/" + sourceId + "?locale=" + locale + "&startTime=" + startTime + "&durationInMinutes=" + durationInMin;
		//System.out.println(resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
}
