package com.tgxd.gtw.nameApi.test;

import org.json.simple.JSONObject;
import org.testng.annotations.*;
import org.testng.Assert;

import com.tgxd.gtw.nameApi.NameActual;
import com.tgxd.gtw.nameApi.NameResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.ExcelUtil;
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
			String locale = "null";
			String serviceID = "null";
			String celebrityID = "null";
			Object[] dataObj = null;
			Object[] dataObj1 = null;
			Object[] dataObj2 = null;		
			try {
				ExcelUtil.setExcelFile(Constants.xlsPath, Constants.nameApiSheet);
				int totalRows = ExcelUtil.ExcelWSheet.getLastRowNum();
				
				for(int i =1; i<=totalRows;i++){
					locale = ExcelUtil.getCellData(i, 0);
					serviceID = ExcelUtil.getCellData(i, 1).replaceAll("\\.0*$", "");
					celebrityID = ExcelUtil.getCellData(i, 2).replaceAll("\\.0*$", "");	
					dataObj = new Object []{locale,serviceID,celebrityID};
					
					switch(i){
					case 1:
						dataObj1 = dataObj;
						break;
					case 2:
						dataObj2 = dataObj;
						break;
					}
				}

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return new Object[][] { 
					dataObj1, dataObj2
			};
		}

}
