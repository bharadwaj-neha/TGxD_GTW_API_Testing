package com.tgxd.gtw.SearchApi;

import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class SearchResourceUtility {

	public static String getSearchAutoSuggestResourceResponse(String locale,String query)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Search/AutoSuggest/?q="+query;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getSearchResultsResourceResponse(String locale,String serviceID,String query)
	{
		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Search/Results/0/2/"+serviceID+"?q="+query;
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getSearchProgramInfoResourceResponse(String locale,String serviceID,String query)
	{

		String resourceIdentifier="http://qa-g.ceidd.net/Puma/"+locale+"/Search/ProgramInfo/Tv/2/"+serviceID+"?query="+query;
		//System.out.println(resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
}
