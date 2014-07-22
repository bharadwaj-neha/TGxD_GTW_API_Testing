package com.tgxd.gtw.ListingsApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.ListingsApi.ListingGetChannelLineUpsActual;
import com.tgxd.gtw.ListingsApi.ListingsResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ListingsGetChannelLineUpsTest {
	
	private JSONObject status;
    private JSONObject data;

  
    
    private void getStatusAndData(String locale,String msoID,String postalCode,String countryCode) {
		String response=ListingsResourceUtility.getChannelLineUpsResourceResponse(locale, msoID, postalCode, countryCode);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String msoID,String postalCode,String countryCode)
    {
		getStatusAndData(locale, msoID, postalCode, countryCode);
		long expected=Constants.passStatusCode;
    	long actual=ListingGetChannelLineUpsActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String msoID,String postalCode,String countryCode)
    {
    	getStatusAndData(locale, msoID, postalCode, countryCode);
    	boolean actual=ListingGetChannelLineUpsActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
    public void testNotNullFieldsforChannel_N(String locale,String msoID,String postalCode,String countryCode)
    {
    			String [] notNullFields = {"ServiceId","ServiceName","MsoId","MsoName","TimeZones","PostalCode"};
    			String dataItem = "Services";
    			getStatusAndData(locale, msoID, postalCode, countryCode);
    			boolean actual=ListingGetChannelLineUpsActual.isNotNull_N(data, dataItem, notNullFields);
    			Assert.assertTrue(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
    public void testPostalCode(String locale,String msoID,String postalCode,String countryCode)
    {
    		  getStatusAndData(locale, msoID, postalCode, countryCode);
    	      String expected=postalCode;
    	      String actual=ListingGetChannelLineUpsActual.getPostalCode(data);
    	      Assert.assertEquals(actual, expected);
    }
    
    //http://qa-g.ceidd.net/Puma/en-US/Listing/GetChannelLineUps?msoId=25&postalCode=91504&countryCode=US
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","25","91504","US"}
   		};
   	}

}
