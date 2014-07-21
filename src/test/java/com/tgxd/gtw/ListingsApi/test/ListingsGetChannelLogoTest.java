package com.tgxd.gtw.ListingsApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.ListingsApi.ListingGetChannelLogoActual;
import com.tgxd.gtw.ListingsApi.ListingsResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ListingsGetChannelLogoTest {
	
	private JSONObject status;
    private String data;

    
    private void getStatusAndData(String locale,String source,String provider,String serviceID,String size) {
		String response=ListingsResourceUtility.getChannelLogoResourceResponse(locale, source, provider, serviceID, size);
		System.out.println("Response is:"+response);
		status=RequestResponseParserUtility.parseResponseStatus(response);
		System.out.println("Status is"+status);
	    data=ListingGetChannelLogoActual.getData(response);
	    System.out.println("Data"+data);
	    
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String source,String provider,String serviceID,String size)
    {
		getStatusAndData(locale, source, provider, serviceID, size);
		long expected=Constants.passStatusCode;
    	long actual=ListingGetChannelLogoActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

   @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String source,String provider,String serviceID,String size)
    {
    	getStatusAndData(locale, source, provider, serviceID, size);
    	Assert.assertNotNull(data);
    }
    
    @DataProvider(name = "Locale_ServiceIDs")
	public static Object[][] provideData() {
 
		return new Object[][] { 
			new Object[] {"en-US","1280","25","75008","2"}, 
		    new Object[] {"es-US","1280","25","73625","2"}
			
		};
	}

}
