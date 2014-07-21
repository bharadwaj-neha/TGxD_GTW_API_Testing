package com.tgxd.gtw.programApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.programApi.ProgramRecommendedActual;
import com.tgxd.gtw.programApi.ProgramResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramRecommendTest {

	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String serviceID) {
		String response=ProgramResourceUtility.getProgramRecommendedResourceResponse(locale,serviceID);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String serviceID)
    {
		getStatusAndData(locale,serviceID);
		long expected=Constants.passStatusCode;
    	long actual=ProgramRecommendedActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String serviceID)
    {
    	getStatusAndData(locale,serviceID);
    	boolean actual=ProgramRecommendedActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
   	public void testNotNullFields_N(String locale,String serviceID)
   	{
   		String [] notNullFields = {"ListTitle","ListType"};
   		String dataItem = "ContentInfoLists";
   		getStatusAndData(locale,serviceID);
   		boolean actual=ProgramRecommendedActual.isNotNull_N(data, dataItem, notNullFields);
   		Assert.assertTrue(actual);
   	}
       
       @Test(dataProvider="Locale_ServiceIDs")
       public void testServiceIdPresentInRequestUrl(String locale,String serviceID)
       {
       	getStatusAndData(locale,serviceID);
       	boolean actual=ProgramRecommendedActual.isServiceIDPresentInDetailUrl(data, serviceID);
       	boolean expected=true;
       	Assert.assertEquals(actual, expected);
       }
   	
       @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","73625"}, 
   		    new Object[] {"es-US","75008"}
   			
   		};
   	}
   

}
