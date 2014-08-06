package com.tgxd.gtw.SearchApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.SearchApi.SearchProgramInfoActual;
import com.tgxd.gtw.SearchApi.SearchResourceUtility;
import com.tgxd.gtw.SearchApi.SearchResultsActual;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class SearchProgramInfoTest {

	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String serviceID,String query) {
		String response=SearchResourceUtility.getSearchProgramInfoResourceResponse(locale, serviceID, query);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String serviceID,String query)
    {
		getStatusAndData(locale, serviceID, query);
		long expected=Constants.passStatusCode;
    	long actual=SearchProgramInfoActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String serviceID,String query)
    {
    	getStatusAndData(locale, serviceID, query);
    	boolean actual=SearchProgramInfoActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
  //  @Test(dataProvider ="Locale_ServiceIDs")
   	public void testNotNullFields_N(String locale,String serviceID,String query)
   	{
   		String [] notNullFields = {"Id","Title","DetailUrl","Channel","SourceId","HD","ServiceId"};
   		String dataItem = "ProgramInfos";
   		getStatusAndData(locale, serviceID, query);
   		boolean actual=SearchResultsActual.isNotNull_N(data, dataItem, notNullFields);
   		Assert.assertTrue(actual);
   	}
    
    @Test(dataProvider="Locale_ServiceIDs")
    public void testServiceIdPresentInDetailUrl(String locale,String serviceID,String query)
    {
    	getStatusAndData(locale, serviceID, query);
    	boolean actual=SearchProgramInfoActual.isServiceIDPresentInDetailUrl(data, serviceID);
    	boolean expected=true;
    	Assert.assertEquals(actual, expected);
    }
    
   
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","75008","friends"}, 
   		    new Object[] {"en-US","75008","Lisa"}
   			
   		};
   	}

}
