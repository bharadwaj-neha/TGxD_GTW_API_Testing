package com.tgxd.gtw.programApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.programApi.ProgramAiringsActual;
import com.tgxd.gtw.programApi.ProgramResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramAiringsTest {
	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String programID,String provider,String serviceID) {
		String response=ProgramResourceUtility.getProgramAiringsResourceResponse(locale,programID,serviceID);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String programID,String provider,String serviceID)
    {
		getStatusAndData(locale, programID,provider,serviceID);
		long expected=Constants.passStatusCode;
    	long actual=ProgramAiringsActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramAiringsActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testServiceId(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	String actual=ProgramAiringsActual.getServiceId(data);
    	String expected=serviceID;
    	Assert.assertEquals(actual, expected);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
	public void testNotNullFields_N(String locale,String programID,String provider,String serviceID)
	{
		String [] notNullFields = {"ServiceId","Channel","SourceId","TuneUrl","RecordUrl","Synopsis","DetailUrl"};
		String dataItem = "Programs";
		getStatusAndData(locale, programID,provider,serviceID);
		boolean actual=ProgramAiringsActual.isNotNull_N(data, dataItem, notNullFields);
		Assert.assertTrue(actual);
	}
	
    @DataProvider(name = "Locale_ServiceIDs")
	public static Object[][] provideData() {
 
		return new Object[][] { 
			new Object[] {"en-US","19667621","2","75008"}, 
		    new Object[] {"es-CO","19667621","2","75008"}
			
		};
	}


}
