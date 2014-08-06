package com.tgxd.gtw.SearchApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.SearchApi.SearchAutoSuggestActual;
import com.tgxd.gtw.SearchApi.SearchResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class SearchAutoSuggestTest {

	
	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String query) {
		String response=SearchResourceUtility.getSearchAutoSuggestResourceResponse(locale, query);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String query)
    {
		getStatusAndData(locale, query);
		long expected=Constants.passStatusCode;
    	long actual=SearchAutoSuggestActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String query)
    {
    	getStatusAndData(locale, query);
    	boolean actual=SearchAutoSuggestActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testQueryValueNotPresentInResults_N(String locale,String query)
    {
    	getStatusAndData(locale, query);
    	boolean actual=SearchAutoSuggestActual.isQueryValueNotPresntInResults_N(data,query);
    	Assert.assertFalse(actual);
    	
    }
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","Tom"}, 
   		    new Object[] {"es-CO","Lisa"}
   			
   		};
   	}


}
