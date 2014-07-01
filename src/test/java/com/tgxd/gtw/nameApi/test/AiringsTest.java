package com.tgxd.gtw.nameApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.nameApi.AiringsActual;
import com.tgxd.gtw.nameApi.NameResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class AiringsTest {

	private JSONObject status;
	private JSONObject data;
	
	
	private void getStatusAndData(String locale, String serviceID,String celebrityID) {
		String response=NameResourceUtility.getNameResourceResponse(locale,serviceID,celebrityID,Constants.nameAirings);
		status=RequestResponseParserUtility.parseResponseStatus(response);
		data=RequestResponseParserUtility.parseResponseData(response);
	}

	//Verify Status code should be 200	
	@Test(dataProvider ="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale, String serviceID, String celebrityID)
	{
		getStatusAndData(locale,serviceID,celebrityID);
		long expected=Constants.passStatusCode;
		long actual=AiringsActual.getStatus(status);
		Assert.assertEquals(actual,expected);
	}

	//Verify 'SericeID' is equal to service ID of request   
	@Test(dataProvider="Locale_ServiceIDs")
	public void testServiceID(String locale,String serviceID, String celebrityID)
	{
		getStatusAndData(locale, serviceID, celebrityID);
		String expected = serviceID;
		String actual = AiringsActual.getServiceID(data);
		Assert.assertEquals(actual,expected);			
	}
	
	@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String serviceID, String celebrityID)
    {
    	getStatusAndData(locale, serviceID,celebrityID);
    	boolean actual=AiringsActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }	

	//Verify Data, Programs, TuneUrl, Id and Title should not be null
	@Test(dataProvider ="Locale_ServiceIDs")
	public void testNotNullFields_N(String locale, String serviceID, String celebrityID)
	{
		String [] notNullFields = {"title","TuneUrl","Id"};
		String dataItem = "Programs";
		getStatusAndData(locale,serviceID,celebrityID);
		boolean actual=AiringsActual.isNotNull_N(data, dataItem, notNullFields);
		Assert.assertTrue(actual);
	}

	@DataProvider(name = "Locale_ServiceIDs")
	public static Object[][] provideData() {

		return new Object[][] { 
				new Object[] {"en-US","75008","100668"}, 
				new Object[] {"es-CO","68337","100668"}
		};
	}
}
