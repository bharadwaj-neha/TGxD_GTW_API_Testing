package com.tgxd.gtw.serviceApi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.serviceApi.ScheduleResourceActual;
import com.tgxd.gtw.serviceApi.ScheduleResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.GeneralUtilities;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class GetSourceScheduleTest {
	private JSONObject status;
    private JSONObject data;
    
    private void getStatusAndData(String locale, String serviceID,String startTime, String durationInMin) {
		String response=ScheduleResourceUtility.getScheduleResourceResponse(locale, serviceID, startTime, durationInMin);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
        
    @Test(dataProvider="urlData",groups={"Sanity"})
	public void testgetStatus(String locale, String serviceID,String startTime, String durationInMin)
    {
		getStatusAndData(locale, serviceID, startTime, durationInMin);
		long expected=Constants.passStatusCode;
    	long actual=ScheduleResourceActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }
    
    @Test(dataProvider="urlData",groups={"Sanity"})
    public void testisDataEmpty_N(String locale, String serviceID,String startTime, String durationInMin)
    {
    	getStatusAndData(locale, serviceID, startTime, durationInMin);
    	boolean actual=ScheduleResourceActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="urlData",groups={"Sanity"})
    public void testNotNullFieldsforChannel_N(String locale, String serviceID,String startTime, String durationInMin)
    {
    	String [] notNullFields = {"ServiceId","StartDate","Duration","DataExpireDate","Locale","GridChannels"};
    	getStatusAndData(locale, serviceID, startTime, durationInMin);
    	boolean actual=ScheduleResourceActual.isNotNull_N(data, notNullFields);
    	Assert.assertTrue(actual);
    }
    
    @DataProvider(name = "urlData")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US", Constants.claroServiceId, GeneralUtilities.getServerDateAndTime(), "240"}, 
   		    new Object[] {"es-CO", Constants.burbankServiceId, GeneralUtilities.getServerDateAndTime(), "240"},
   		};
   	}
}
