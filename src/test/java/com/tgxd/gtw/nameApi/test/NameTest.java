package com.tgxd.gtw.nameApi.test;

import org.json.simple.JSONObject;
import org.testng.annotations.*;
import org.testng.Assert;

import com.tgxd.gtw.nameApi.NameActual;
import com.tgxd.gtw.nameApi.NameResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;


public class NameTest {
	    
		private JSONObject status;
	    private JSONObject data;
	    
		private void getStatusAndData(String locale, String serviceID,String celebrityID) {
			String response=NameResourceUtility.getNameResourceResponse(locale,serviceID,celebrityID,Constants.nameName);
			status=RequestResponseParserUtility.parseResponseStatus(response);
		    data=RequestResponseParserUtility.parseResponseData(response);
		}
	    
	   @Test(dataProvider="Locale_ServiceIDs")
		public void testImageUrl(String locale,String serviceID,String celebrityID)
		{
			getStatusAndData(locale, serviceID, celebrityID);
			boolean actual=NameActual.getImageUrl(data);
		    Assert.assertTrue(actual);
		}

		@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
		public void testgetStatus(String locale,String serviceID,String celebrityID)
	    {
			getStatusAndData(locale, serviceID,celebrityID);
			long expected=Constants.passStatusCode;
	    	long actual=NameActual.getStatus(status);
	    	Assert.assertEquals(actual,expected);
	    }
	
		@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	    public void testisDataEmpty_N(String locale,String serviceID, String celebrityID)
	    {
	    	getStatusAndData(locale, serviceID,celebrityID);
	    	boolean actual=NameActual.isDataEmpty_N(data);
	    	Assert.assertFalse(actual);
	    }
		
		 @Test(dataProvider ="Locale_ServiceIDs")
		 public void testNotNullFields_N(String locale, String serviceID, String celebrityID)
		 {
			 String[] notNullFields={"CreditName","DateOfBirth"};
			 getStatusAndData(locale,serviceID,celebrityID);
			 boolean actual=NameActual.isNotNull_N(data, notNullFields);
			 Assert.assertTrue(actual);			 
		 }
		 
		
		@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
		public void testServiceIDPresentInURL(String locale,String serviceID, String celebrityID)
		{
			getStatusAndData(locale, serviceID, celebrityID);
			String[] expectedURLs={"AiringsUrl","CreditUrl"};
			for(int testLoop=0;testLoop<expectedURLs.length;testLoop++)
			{
			   boolean actual=NameActual.isIdInURLS(data, expectedURLs[testLoop],serviceID);
			   Assert.assertTrue(actual);
			}			
		}
		
		@Test(dataProvider="Locale_ServiceIDs")
		public void testCelebrityIDPresentInURL(String locale,String serviceID, String celebrityID)
		{
			getStatusAndData(locale, serviceID, celebrityID);
			String[] expectedURLs={"AiringsUrl","CreditUrl"};
			for(int testLoop=0;testLoop<expectedURLs.length;testLoop++)
			{
			   boolean actual=NameActual.isIdInURLS(data, expectedURLs[testLoop],celebrityID);
			   Assert.assertTrue(actual);
			}
		}
		
		@Test(dataProvider="Locale_ServiceIDs")
	    public void testLocaleInUrls(String locale,String serviceID,String celebrityID)
	    {
			getStatusAndData(locale, serviceID, celebrityID);
			String[] expectedURLs={"AiringsUrl","CreditUrl"};
			for(int testLoop=0;testLoop<expectedURLs.length;testLoop++)
			{
			   boolean actual=NameActual.isIdInURLS(data, expectedURLs[testLoop],celebrityID);
			   Assert.assertTrue(actual);
			}
	    }
		
		@Test(dataProvider="Locale_ServiceIDs")
		public void testCelebrityId(String locale,String serviceID,String celebrityID)
		{
			getStatusAndData(locale, serviceID, celebrityID);
			String expected=celebrityID;
			String actual=NameActual.getCelebrityId(data);
			Assert.assertEquals(actual, expected);
		}

	    
	    @DataProvider(name = "Locale_ServiceIDs")
		public static Object[][] provideData() {
	 
			return new Object[][] { 
				new Object[] {"en-US","75008","100668"}, 
			    new Object[] {"es-CO","68337","100668"}
			};
		}
	    

}
