package com.tgxd.gtw.ListingsApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.ListingsApi.ListingFavouriteChannelThumbnailsActual;
import com.tgxd.gtw.ListingsApi.ListingsResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ListingFavouriteChannelThumbnailTest {
	

	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale) {
		String response=ListingsResourceUtility.postGetFavoriteProgramTilesResourceResponse(locale);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale)
    {
		getStatusAndData(locale);
		long expected=Constants.passStatusCode;
    	long actual=ListingFavouriteChannelThumbnailsActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale)
    {
    	getStatusAndData(locale);
    	boolean actual=ListingFavouriteChannelThumbnailsActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
    public void testNotNullFieldsforChannel_N(String locale)
    {
    			String [] notNullFields = {"ServiceId","CallLetters","SourceId","TuneUrl","RecordUrl","Id","Title","DetailUrl"};
    			String dataItem = "ProgramInfos";
    			getStatusAndData(locale);
    			boolean actual=ListingFavouriteChannelThumbnailsActual.isNotNull_N(data, dataItem, notNullFields);
    			Assert.assertTrue(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
    public void testLocalesInUrls(String locale)
    {
    			
    			String[] urlsToTestForLocale={"TuneUrl","RecordUrl","DetailUrl"};
    			String dataItem = "ProgramInfos";
    			getStatusAndData(locale);
    			boolean actual=ListingFavouriteChannelThumbnailsActual.checkLocaleInUrls(data,dataItem,urlsToTestForLocale);
    			Assert.assertTrue(actual);
    	
    }
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US"}, 
   		    new Object[] {"es-CO"}
   		};
   	}

}
