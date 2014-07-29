package com.tgxd.gtw.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tgxd.gtw.ListingsApi.ListingsResourceUtility;

public class GeneralUtilities {

	public static int generateRandomNumber(int itemArraySize)
	{
		int randomNumber=(int)(Math.random()*itemArraySize);
		return randomNumber;
	}

	public static boolean verifyNotNull(JSONObject data, String dataItem, String [] notNullFields){

		int flag = 0;
		if(data != null){

			JSONArray itemArray = (JSONArray)data.get(dataItem);
			if(itemArray != null){							
				int element =generateRandomNumber(itemArray.size()); 
				JSONObject obj = (JSONObject) itemArray.get(element);
				for(int i=0; i< notNullFields.length; i++){				
					if((String)obj.get(obj.get(notNullFields[i])) != ""){
						flag ++;
					}
					else{
						break;
					}

				}		
			}
		}
		if(flag == notNullFields.length){
			return true;
		}
		else{
			return false;	
		}		

	}

	public static boolean verifyNotNull(JSONObject data, String [] notNullFields){

		int flag = 0;
		if(data != null){


			for(int i=0; i< notNullFields.length; i++){				
				if((String)data.get(data.get(notNullFields[i])) != ""){
					flag ++;
				}
				else{
					break;
				}


			}
		}
		if(flag == notNullFields.length){
			return true;
		}
		else{
			return false;	
		}		

	}

	
	public static boolean verifyLocalesInUrl(JSONObject data, String dataItem, String [] urlList){

		int flag = 0;
		if(data != null){

			JSONArray itemArray = (JSONArray)data.get(dataItem);
			if(itemArray != null){							
				int element =generateRandomNumber(itemArray.size()); 
				JSONObject obj = (JSONObject) itemArray.get(element);
				for(int i=0; i< urlList.length; i++){				
					if((String)obj.get(obj.get(urlList[i])) != ""){
						flag ++;
					}
					else{
						break;
					}

				}		
			}
		}
		if(flag == urlList.length){
			return true;
		}
		else{
			return false;	
		}		

	}

	
	public static String getEnvironment() {

		Properties prop = new Properties();
		InputStream input = null;
		String env = "";

		try {

			input = new FileInputStream("config.properties");
			prop.load(input);
			env = prop.getProperty("environment");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally{
			if(input!=null){
				try {
					input.close();
					return env;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return env;
	}
	
	public static String getServerDateAndTime(){
		String response=ListingsResourceUtility.getServerUTCInfoResourceResponse(Constants.defaultLocale);
		JSONObject data=RequestResponseParserUtility.parseResponseData(response);
		String currentDateTime=(String)data.get("CurrentDateTime");
		return currentDateTime.replaceAll(" ", "%20");
	}

}
