package com.tgxd.gtw.nameApi.test;

import org.json.simple.JSONObject;
import org.testng.annotations.*;
import org.testng.Assert;

import com.tgxd.gtw.nameApi.CreditsActual;
import com.tgxd.gtw.nameApi.NameResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class CreditsTest {
	private JSONObject status;
    private JSONObject data;
    
	 private void getStatusAndData(String locale, String serviceID,String celebrityID) {
		 String response=NameResourceUtility.getNameResourceResponse(locale,serviceID,celebrityID,Constants.nameCredits);
			status=RequestResponseParserUtility.parseResponseStatus(response);
		    data=RequestResponseParserUtility.parseResponseData(response);
		}
    
	 @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	    public void testgetStatus(String locale,String serviceID,String celebrityID)
	    {
		 getStatusAndData(locale, serviceID, celebrityID);
	    	long expected=Constants.passStatusCode;
	    	long actual=CreditsActual.getStatus(status);
	    	Assert.assertEquals(actual,expected);
	    }
	    
	 @Test(dataProvider="Locale_ServiceIDs")
	 public void testNameID(String locale,String serviceID,String celebrityID)
	 {
		 getStatusAndData(locale, serviceID, celebrityID);
		 String expected=celebrityID;
		 String actual=CreditsActual.getNameID(data);
		 Assert.assertEquals(actual, expected);
	 }
	 
	 @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	 public void testisDataEmpty_N(String locale,String serviceID,String celebrityID)
	 {
		 getStatusAndData(locale, serviceID, celebrityID);
	     boolean actual=CreditsActual.isDataEmpty_N(data);
	     Assert.assertFalse(actual);		 
	 }
	 
	 @Test(dataProvider="Locale_ServiceIDs")
	 public void testLocalesInURLs(String locale,String serviceID,String celebrityID)
	 {
		 getStatusAndData(locale, serviceID, celebrityID);
			String[] expectedURLs={"DetailUrl"};
			for(int testLoop=0;testLoop<expectedURLs.length;testLoop++)
			{
				System.out.println("Locale in Test is :"+locale);
			   boolean actual=CreditsActual.testLocaleInUrls(data, expectedURLs[testLoop],locale);
			   Assert.assertTrue(actual);
			}
	 }
	 
	 @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	 public void testServiceIDInURLs(String locale,String serviceID,String celebrityID)
	 {
		 getStatusAndData(locale, serviceID, celebrityID);
			String[] expectedURLs={"DetailUrl"};
			for(int testLoop=0;testLoop<expectedURLs.length;testLoop++)
			{
				System.out.println("Locale in Test is :"+locale);
			   boolean actual=CreditsActual.testIDInUrls(data, expectedURLs[testLoop],serviceID);
			   Assert.assertTrue(actual);
			}
	 }
	 
	 @Test(dataProvider="Locale_ServiceIDs")
	 public void testProgramIDInURLs(String locale,String serviceID,String celebrityID)
	 {
		 getStatusAndData(locale, serviceID, celebrityID);
		 String programID=CreditsActual.getProgramID(data);
		 String detailUrl=CreditsActual.getDetailUrl(data);		
		 boolean actual=detailUrl.contains(programID);
		 Assert.assertFalse(actual);
	 }
	 
	 @Test(dataProvider ="Locale_ServiceIDs")
	 public void testNotNullFields_N(String locale, String serviceID, String celebrityID)
	 {
		 String[] notNullFields={"ProgramId","Names"};
		 String dataItem="Names";
		 getStatusAndData(locale,serviceID,celebrityID);
		 boolean actual=CreditsActual.isNotNull_N(data, dataItem, notNullFields);
		 Assert.assertTrue(actual);
		 
	 }
	 
	 @DataProvider(name = "Locale_ServiceIDs")
		public static Object[][] provideData() {
	 
			return new Object[][] { 
				new Object[] {"en-US","75008","100668"}, 
			    new Object[] {"es-CO","68337","100668"}
			};
		}

}
