//TODO: FOR YOU API
package com.tgxd.gtw.programApi;

import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramResourceUtility {
	
	public static String getProgramAiringsResourceResponse(String locale,String programID,String serviceID)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Program/Airings/"+programID+"/2/"+serviceID;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getProgramCastCrewResourceResponse(String locale,String programID,String serviceID)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Program/CastCrew/"+programID+"/2/"+serviceID;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getProgramDetailsResourceResponse(String locale,String programID,String serviceID)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Program/Details/"+programID+"/2/"+serviceID;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String postGetFavoriteProgramTilesResourceResponse(String locale,String serviceID)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Program/GetFavoriteProgramsTiles/"+serviceID+"/2";
		String postData ="{\"ProgramIdentifierList\":[\"18691595\",\"6891616\"]}";
		return RequestResponseParserUtility.response_POST(resourceIdentifier, postData);
	}
	
	public static String getFilteredProgramsResourceResponse(String locale,String type,String serviceID)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Program/GetFilteredPrograms/"+type+"/2/"+serviceID;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getGenreListResourceResponse(String locale, String type)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/program/GetGenreList/?listType="+type+"";
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getProgramRecommendedResourceResponse(String locale,String serviceID)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Program/Recommended/ForYou/2/"+serviceID;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	
	}
	
	public static String getProgramSimilarResourceResponse(String locale,String programID,String serviceID)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Program/Similar/"+programID+"/2/"+serviceID;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
}
