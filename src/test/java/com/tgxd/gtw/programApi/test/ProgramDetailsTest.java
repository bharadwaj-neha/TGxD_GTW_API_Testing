package com.tgxd.gtw.programApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tgxd.gtw.programApi.ProgramDetailsActual;
import com.tgxd.gtw.programApi.ProgramResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramDetailsTest {

	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String programID,String provider,String serviceID) {
		String response=ProgramResourceUtility.getProgramDetailsResourceResponse(locale,programID,serviceID);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String programID,String provider,String serviceID)
    {
		getStatusAndData(locale, programID,provider,serviceID);
		long expected=Constants.passStatusCode;
    	long actual=ProgramDetailsActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramDetailsActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testProgramId(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	String actual=ProgramDetailsActual.getProgramId(data);
    	System.out.println("Actual data is:"+actual);
    	String expected=programID;
    	System.out.println("Expected data is:"+expected);
    	Assert.assertEquals(actual, expected);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
  	public void testNotNullFields_N(String locale,String programID,String provider,String serviceID)
  	{
  		String [] notNullFields = {"ServiceId","EpisodeTitle","ProgramType","Synopsis","Id",};
  		getStatusAndData(locale, programID,provider,serviceID);
  		boolean actual=ProgramDetailsActual.isNotNull_N(data,notNullFields);
  		Assert.assertTrue(actual);
  	}
    
    @Test(dataProvider="Locale_ServiceIDs")
    public void testServiceIdPresentInDetailUrl(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramDetailsActual.isServiceIDPresentInDetailUrl(data, serviceID);
    	boolean expected=true;
    	Assert.assertEquals(actual, expected);
    }
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","21392913","2","73625"}, 
   		    new Object[] {"en-US","4672","2","896839"},
   			new Object[] {"en-US","19667621","2","75008"}
   		};
   	}

	
	
}
