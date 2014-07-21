package com.tgxd.gtw.programApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.programApi.ProgramResourceUtility;
import com.tgxd.gtw.programApi.ProgramSimilarActual;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramSimilarTest {

	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String programID,String provider,String serviceID) {
		String response=ProgramResourceUtility.getProgramSimilarResourceResponse(locale,programID,serviceID);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String programID,String provider,String serviceID)
    {
		getStatusAndData(locale, programID,provider,serviceID);
		long expected=Constants.passStatusCode;
    	long actual=ProgramSimilarActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramSimilarActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testServiceId(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	String actual=ProgramSimilarActual.getServiceId(data);
    	String expected=serviceID;
    	Assert.assertEquals(actual, expected);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
	public void testNotNullFields_N(String locale,String programID,String provider,String serviceID)
	{
		String [] notNullFields = {"ServiceId","Id","Title","DetailUrl"};
		String dataItem = "Programs";
		getStatusAndData(locale, programID,provider,serviceID);
		boolean actual=ProgramSimilarActual.isNotNull_N(data, dataItem, notNullFields);
		Assert.assertTrue(actual);
	}
    
    @Test(dataProvider="Locale_ServiceIDs")
    public void testServiceIdPresentInDetailUrl(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramSimilarActual.isServiceIDPresentInDetailUrl(data, serviceID);
    	boolean expected=true;
    	Assert.assertEquals(actual, expected);
    }
	
    @DataProvider(name = "Locale_ServiceIDs")
	public static Object[][] provideData() {
 
		return new Object[][] { 
			new Object[] {"en-US","4477399","2","73625"}, 
		    new Object[] {"es-US","19667621","2","75008"}
			
		};
	}
}
