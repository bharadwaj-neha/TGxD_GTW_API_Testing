package com.tgxd.gtw.programApi.test;

import java.util.Arrays;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.programApi.ProgramGetFavouriteProgramTilesActual;
import com.tgxd.gtw.programApi.ProgramResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramGetFavouriteProgramTilesTest {
	
	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String serviceID) {
		String response=ProgramResourceUtility.postGetFavoriteProgramTilesResourceResponse(locale, serviceID);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String serviceID)
    {
		getStatusAndData(locale,serviceID);
		long expected=Constants.passStatusCode;
    	long actual=ProgramGetFavouriteProgramTilesActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String serviceID)
    {
    	getStatusAndData(locale,serviceID);
    	boolean actual=ProgramGetFavouriteProgramTilesActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
   	public void testNotNullFields_N(String locale,String serviceID)
   	{
   		String [] notNullFields = {"Id","Title","ImageUrl","DetailUrl","ProviderId"};
   		String dataItem = "Programs";
   		getStatusAndData(locale, serviceID);
   		boolean actual=ProgramGetFavouriteProgramTilesActual.verifyNotNull(data, dataItem, notNullFields);
   		Assert.assertTrue(actual);
   	}
    
    @Test(dataProvider ="Locale_ServiceIDs")
	public void testID(String locale, String serviceID)
	{
		getStatusAndData(locale,serviceID);
		String[] expected={"6891616","18691595"};
		String actual=ProgramGetFavouriteProgramTilesActual.getID(data);
		Assert.assertTrue(Arrays.asList(expected).contains(actual));
	}
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","75008"}, 
   		    new Object[] {"en-US","73625"}
   		};
   	}

}
