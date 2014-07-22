package com.tgxd.gtw.ListingsApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.ListingsApi.ListingServiceDetailsActual;
import com.tgxd.gtw.ListingsApi.ListingsResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ListingServiceDetailsTest {

	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String serviceID) {
		String response=ListingsResourceUtility.getServiceDetailsResourceResponse(locale, serviceID);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String serviceID)
    {
		getStatusAndData(locale,serviceID);
		long expected=Constants.passStatusCode;
    	long actual=ListingServiceDetailsActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String serviceID)
    {
    	getStatusAndData(locale,serviceID);
    	boolean actual=ListingServiceDetailsActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
  	public void testNotNullFields_N(String locale,String serviceID)
  	{
  		String [] notNullFields = {"ServiceId","Name","TimeZones","Channels","StartDateUtc","StartDateLocal","DurationInDays"};
  		getStatusAndData(locale,serviceID);
  		boolean actual=ListingServiceDetailsActual.isNotNull_N(data,notNullFields);
  		Assert.assertTrue(actual);
  	}
    
    
    @Test(dataProvider ="Locale_ServiceIDs")
    public void testNotNullFieldsforChannel_N(String locale,String serviceID)
    {
    			String [] notNullFields = {"FullName","CallLetters","SourceId","Channel"};
    			String dataItem = "Channels";
    			getStatusAndData(locale,serviceID);
    			boolean actual=ListingServiceDetailsActual.isNotNull_N(data, dataItem, notNullFields);
    			Assert.assertTrue(actual);
    }
    
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","73625"}, 
   		    new Object[] {"es-US","75008"}
   			
   		};
   	}
}
