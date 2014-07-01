package com.tgxd.gtw.nameApi;

import org.json.simple.JSONObject;

import com.tgxd.gtw.utilities.GeneralUtilities;

public class NameActual {
		
		public static boolean getImageUrl(JSONObject data)
		{
			System.out.println("Image URL is" +(String)data.get("ImageUrl"));
			return data.get("ImageUrl")!=null;
		}
		
		public static long getStatus(JSONObject status)
		{
				return (Long) status.get("Code");
		}
		
		public static boolean isDataEmpty_N(JSONObject data)
		{
			return data.isEmpty();
		}
		
		public static boolean getLocaleInCreditURL(JSONObject data,String locale)
		{
			String creditURl=(String)data.get("CreditUrl");
			System.out.println("Credit url is: "+creditURl);
			boolean result=creditURl.contains(locale);
			return result;
		}
		
		public static boolean isCreditNamePresent(JSONObject data)
		{
			boolean creditName=(boolean) (data.get("CreditName")!=null);
			System.out.println("Credit Name is :"+(String)data.get("CreditName"));
			return creditName;
		}
		
		public static boolean isDOBPresent(JSONObject data)
		{
			boolean dob=(boolean)(data.get("DateOfBirth")!=null);
			return dob;
		}
		
		public static boolean isIdInURLS(JSONObject data,String urlName,String serviceID)
		{
			String url=(String)data.get(urlName);
			boolean isServiceIdPresent=url.contains(serviceID);
			return isServiceIdPresent;
		}
		
		public static boolean testLocaleInUrls(JSONObject data,String locale,String urlName)
		{
			String url=(String)data.get(urlName);
			boolean isServiceIdPresent=url.contains(locale);
			return isServiceIdPresent;
		}
		
		public static String getCelebrityId(JSONObject data)
		{
			String celebrityId=(String) data.get("Id");
			return celebrityId;
		}
		
		public static boolean isNotNull_N(JSONObject data, String [] notNullFields)
		{
			boolean isNull = GeneralUtilities.verifyNotNull(data,notNullFields);
			if (isNull == true){
				return true;
			}
			else{
				return false;
			}
		}
	}

