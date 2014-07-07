package com.tgxd.gtw.nameApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.nameApi.AiringsActual;
import com.tgxd.gtw.nameApi.NameResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.ExcelUtil;
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
		String locale = "null";
		String serviceID = "null";
		String celebrityID = "null";
		Object[] dataObj = null;
		Object[] dataObj1 = null;
		Object[] dataObj2 = null;		
		try {
			ExcelUtil.setExcelFile(Constants.xlsPath, Constants.nameApiSheet);
			int totalRows = ExcelUtil.ExcelWSheet.getLastRowNum();
			
			for(int i =1; i<=totalRows;i++){
				locale = ExcelUtil.getCellData(i, 0);
				serviceID = ExcelUtil.getCellData(i, 1).replaceAll("\\.0*$", "");
				celebrityID = ExcelUtil.getCellData(i, 2).replaceAll("\\.0*$", "");	
				dataObj = new Object []{locale,serviceID,celebrityID};
				
				switch(i){
				case 1:
					dataObj1 = dataObj;
					break;
				case 2:
					dataObj2 = dataObj;
					break;
				}
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return new Object[][] { 
				dataObj1, dataObj2
		};
	}
}
