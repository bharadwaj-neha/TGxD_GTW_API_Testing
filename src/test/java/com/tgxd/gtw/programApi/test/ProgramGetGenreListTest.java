package com.tgxd.gtw.programApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.programApi.ProgramGetGenreListActual;
import com.tgxd.gtw.programApi.ProgramResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class ProgramGetGenreListTest {

	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String type) {
		String response=ProgramResourceUtility.getGenreListResourceResponse(locale, type);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String type)
    {
		getStatusAndData(locale, type);
		long expected=Constants.passStatusCode;
    	long actual=ProgramGetGenreListActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String type)
    {
    	getStatusAndData(locale,type);
    	boolean actual=ProgramGetGenreListActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
   	public void testNotNullFields_N(String locale,String type)
   	{
   		String [] notNullFields = {"GenreName","GenreName"};
   		String dataItem = "Genres";
   		getStatusAndData(locale, type);
   		boolean actual=ProgramGetGenreListActual.isNotNull_N(data, dataItem, notNullFields);
   		Assert.assertTrue(actual);
   	}
    
    @DataProvider(name = "Locale_ServiceIDs")
  	public static Object[][] provideData() {
   
  		return new Object[][] { 
  			new Object[] {"en-US","Movie"}, 
  		    new Object[] {"es-CO","TV"}
  			
  		};
  	}
}
