package com.tgxd.gtw.programApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.programApi.ProgramGetFilteredProgramActual;
import com.tgxd.gtw.programApi.ProgramResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramGetFilteredProgramTest {
	
	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String type,String provider,String serviceID) {
		String response=ProgramResourceUtility.getFilteredProgramsResourceResponse(locale,type,serviceID);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String programID,String provider,String serviceID)
    {
		getStatusAndData(locale, programID,provider,serviceID);
		long expected=Constants.passStatusCode;
    	long actual=ProgramGetFilteredProgramActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramGetFilteredProgramActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
  	public void testNotNullFields_N(String locale,String programID,String provider,String serviceID)
  	{
  		String [] notNullFields = {"ServiceId","EpisodeTitle","ProgramType","Synopsis","Id",};
  		getStatusAndData(locale, programID,provider,serviceID);
  		boolean actual=ProgramGetFilteredProgramActual.isNotNull_N(data,notNullFields);
  		Assert.assertTrue(actual);
  	}
    
    
    
    @Test(dataProvider="Locale_ServiceIDs")
    public void testServiceIdPresentInDetailUrl(String locale,String programID,String provider,String serviceID)
    {
    	getStatusAndData(locale, programID,provider,serviceID);
    	boolean actual=ProgramGetFilteredProgramActual.isServiceIDPresentInDetailUrl(data, serviceID);
    	boolean expected=true;
    	Assert.assertEquals(actual, expected);
    }
    
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","Movie","2","75008"}, 
   		    new Object[] {"en-US","Tv","2","75008"},
   			new Object[] {"en-US","TvSeries","2","75008"}
   		};
   	}


}
