package com.tgxd.gtw.nameApi.test;

import java.util.Arrays;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.nameApi.GetFavouritesActual;
import com.tgxd.gtw.nameApi.NameResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.ExcelUtil;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class GetFavouritesTest {

	private JSONObject status;
	private JSONObject data;
	
	private void getStatusAndData(String locale, String serviceID) {
		String response=NameResourceUtility.postNameResourceResponse(locale,serviceID,Constants.nameGetFavorites);
		status=RequestResponseParserUtility.parseResponseStatus(response);
		data=RequestResponseParserUtility.parseResponseData(response);
	}

	//Verify Status code should be 200
	@Test(dataProvider ="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale, String serviceID)
	{
		getStatusAndData(locale,serviceID);
		long expected=Constants.passStatusCode;
		long actual=GetFavouritesActual.getStatus(status);
		Assert.assertEquals(actual,expected);
	}
	
	//TODO: Add test to find if the data is not null
	@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String serviceID)
    {
    	getStatusAndData(locale, serviceID);
    	boolean actual=GetFavouritesActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
	
	//Verify Response Id should be equal to id passed in the request
	@Test(dataProvider ="Locale_ServiceIDs")
	public void testID(String locale, String serviceID)
	{
		getStatusAndData(locale,serviceID);
		String[] expected=Constants.nameGetFavoritesCelebrityIds;
		String actual=GetFavouritesActual.getID(data);
		Assert.assertTrue(Arrays.asList(expected).contains(actual));
	}

	//Verify Data, Names and DetailUrl should not be null
	@Test(dataProvider ="Locale_ServiceIDs")
	public void testNotNullFields_N(String locale, String serviceID)
	{
		String [] notNullFields = {"Name","DetailUrl"};
		String dataItem = "Names";
		getStatusAndData(locale,serviceID);	
		boolean actual=GetFavouritesActual.verifyNotNull(data, dataItem, notNullFields);
		Assert.assertTrue(actual);
	}
	
	//Verify 'SericeID' of DetailUrl is equal to service ID of request   
	@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testServiceID(String locale,String serviceID)
	{
		getStatusAndData(locale, serviceID);
		boolean actual = GetFavouritesActual.verifyDetailUrl(data,serviceID);
		Assert.assertTrue(actual);			
	}

	/*@DataProvider(name = "Locale_ServiceIDs")
	public static Object[][] provideData() {

		return new Object[][] { 
				new Object[] {"en-US","75008"}, 
				new Object[] {"es-CO","68337"}
		};
	}*/
	
	
	@DataProvider(name = "Locale_ServiceIDs")
	public static Object[][] provideData() {
		String locale = "null";
		String serviceID = "null";
		Object[] dataObj = null;
		Object[] dataObj1 = null;
		Object[] dataObj2 = null;		
		try {
			ExcelUtil.setExcelFile(Constants.xlsPath, Constants.nameApiSheet);
			int totalRows = ExcelUtil.ExcelWSheet.getLastRowNum();
			
			for(int i =1; i<=totalRows;i++){
				locale = ExcelUtil.getCellData(i, 0);
				serviceID = ExcelUtil.getCellData(i, 1).replaceAll("\\.0*$", "");
				dataObj = new Object []{locale,serviceID};
				
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
