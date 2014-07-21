package com.tgxd.gtw.ListingsApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.ListingsApi.ListingsGetServerUtcInfoActual;
import com.tgxd.gtw.ListingsApi.ListingsResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ListingsGetServerUtcInfoTest {
	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale) {
		String response=ListingsResourceUtility.getServerUTCInfoResourceResponse(locale);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale)
    {
		getStatusAndData(locale);
		long expected=Constants.passStatusCode;
    	long actual=ListingsGetServerUtcInfoActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale)
    {
    	getStatusAndData(locale);
    	boolean actual=ListingsGetServerUtcInfoActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
  	public void testNotNullFields_N(String locale)
  	{
  		String [] notNullFields = {"CurrentDateTime","MinAppVersionDateTime"};
  		getStatusAndData(locale);
  		boolean actual=ListingsGetServerUtcInfoActual.isNotNull_N(data,notNullFields);
  		Assert.assertTrue(actual);
  	}
    
    //TODO: should we add test for checking the current date
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US"}, 
   		    new Object[] {"es-CO"}
   			
   		};
   	}



}
