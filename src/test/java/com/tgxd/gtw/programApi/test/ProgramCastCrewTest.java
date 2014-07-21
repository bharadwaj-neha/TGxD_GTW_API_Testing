package com.tgxd.gtw.programApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tgxd.gtw.programApi.ProgramCastCrewActual;
import com.tgxd.gtw.programApi.ProgramResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramCastCrewTest {
	
	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String programID,String provider,String serviceID) {
		String response=ProgramResourceUtility.getProgramCastCrewResourceResponse(locale,programID,serviceID);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String programID,String provider,String serviceID)
    {
		getStatusAndData(locale, programID,provider,serviceID);
		long expected=Constants.passStatusCode;
    	long actual=ProgramCastCrewActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramCastCrewActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testProgramId(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	String actual=ProgramCastCrewActual.getProgramId(data);
    	String expected=programID;
    	Assert.assertEquals(actual, expected);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
  	public void testNotNullFields_N(String locale,String programID,String provider,String serviceID)
  	{
  		String [] notNullFields = {"Id","Name","ProviderId","DetailUrl"};
  		String dataItem = "Names";
  		getStatusAndData(locale, programID,provider,serviceID);
  		boolean actual=ProgramCastCrewActual.isNotNull_N(data, dataItem, notNullFields);
  		Assert.assertTrue(actual);
  	}
    
    @Test(dataProvider="Locale_ServiceIDs")
    public void testServiceIdPresentInDetailUrl(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramCastCrewActual.isServiceIDPresentInDetailUrl(data, serviceID);
    	boolean expected=true;
    	Assert.assertEquals(actual, expected);
    }
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","4672","2","896839"}, 
   		    new Object[] {"en-US","19667621","2","896839"}
   			
   		};
   	}

}
