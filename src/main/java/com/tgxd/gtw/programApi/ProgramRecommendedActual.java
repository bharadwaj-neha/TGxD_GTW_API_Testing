package com.tgxd.gtw.programApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class ProgramRecommendedActual {
	
	public static long getStatus(JSONObject status)
	{
			return (Long) status.get("Code");
	}
	
	public static boolean isDataEmpty_N(JSONObject data)
	{
		return data.isEmpty();
	}
	
	public static boolean isNotNull_N(JSONObject data,String dataItem ,String [] notNullFields)
	{
		boolean isNull = GeneralUtilities.verifyNotNull(data,dataItem,notNullFields);
		if (isNull == true){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean isServiceIDPresentInDetailUrl(JSONObject data,String serviceId) {
		boolean isServiceIDPresent;
		JSONObject obj;
		JSONArray itemArray;
		boolean isSelectEmpty=true;
		
		do
		{
			itemArray = (JSONArray)data.get("ContentInfoLists");		
			int element = GeneralUtilities.generateRandomNumber(itemArray.size());
			obj = (JSONObject) itemArray.get(element);
			//System.out.println("Element is"+obj);
		
			JSONArray SelectList=(JSONArray)obj.get("SelectList");
			//System.out.println("#####"+SelectList);
			if(SelectList==null)
			{
				//System.out.println("Select List is empty");
				isSelectEmpty=true;
				
			}
			else
			{
				//System.out.println("Select list is not empty");
				isSelectEmpty=false;
			}
			
		}while(isSelectEmpty==true);
		
		
		JSONArray itemArraySelectList=(JSONArray)obj.get("SelectList");
		int selectListElement=GeneralUtilities.generateRandomNumber(itemArraySelectList.size());
		JSONObject selectObject=(JSONObject) itemArraySelectList.get(selectListElement);
		String RequestUrl=(String)selectObject.get("RequestUrl");
		isServiceIDPresent=RequestUrl.contains(serviceId);
		
		return isServiceIDPresent;
	}
	

}
