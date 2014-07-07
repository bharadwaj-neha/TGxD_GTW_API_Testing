package com.tgxd.gtw.nameApi.test;

import org.json.simple.JSONObject;
import org.testng.annotations.*;
import org.testng.Assert;

import com.tgxd.gtw.nameApi.CreditsActual;
import com.tgxd.gtw.nameApi.NameResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.ExcelUtil;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class CreditsTest {
	private JSONObject status;
	private JSONObject data;

	private void getStatusAndData(String locale, String serviceID,String celebrityID) {
		String response=NameResourceUtility.getNameResourceResponse(locale,serviceID,celebrityID,Constants.nameCredits);
		status=RequestResponseParserUtility.parseResponseStatus(response);
		data=RequestResponseParserUtility.parseResponseData(response);
	}

	@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String serviceID,String celebrityID)
	{
		getStatusAndData(locale, serviceID, celebrityID);
		long expected=Constants.passStatusCode;
		long actual=CreditsActual.getStatus(status);
		Assert.assertEquals(actual,expected);
	}

	@Test(dataProvider="Locale_ServiceIDs")
	public void testNameID(String locale,String serviceID,String celebrityID)
	{
		getStatusAndData(locale, serviceID, celebrityID);
		String expected=celebrityID;
		String actual=CreditsActual.getNameID(data);
		Assert.assertEquals(actual, expected);
	}

	@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testisDataEmpty_N(String locale,String serviceID,String celebrityID)
	{
		getStatusAndData(locale, serviceID, celebrityID);
		boolean actual=CreditsActual.isDataEmpty_N(data);
		Assert.assertFalse(actual);		 
	}

	@Test(dataProvider="Locale_ServiceIDs")
	public void testLocalesInURLs(String locale,String serviceID,String celebrityID)
	{
		getStatusAndData(locale, serviceID, celebrityID);
		String[] expectedURLs={"DetailUrl"};
		for(int testLoop=0;testLoop<expectedURLs.length;testLoop++)
		{
			//System.out.println("Locale in Test is :"+locale);
			boolean actual=CreditsActual.testLocaleInUrls(data, expectedURLs[testLoop],locale);
			Assert.assertTrue(actual);
		}
	}

	@Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testServiceIDInURLs(String locale,String serviceID,String celebrityID)
	{
		getStatusAndData(locale, serviceID, celebrityID);
		String[] expectedURLs={"DetailUrl"};
		for(int testLoop=0;testLoop<expectedURLs.length;testLoop++)
		{
			//System.out.println("Locale in Test is :"+locale);
			boolean actual=CreditsActual.testIDInUrls(data, expectedURLs[testLoop],serviceID);
			Assert.assertTrue(actual);
		}
	}

	@Test(dataProvider="Locale_ServiceIDs")
	public void testProgramIDInURLs(String locale,String serviceID,String celebrityID)
	{
		getStatusAndData(locale, serviceID, celebrityID);
		String programID=CreditsActual.getProgramID(data);
		String detailUrl=CreditsActual.getDetailUrl(data);		
		boolean actual=detailUrl.contains(programID);
		Assert.assertFalse(actual);
	}

	@Test(dataProvider ="Locale_ServiceIDs")
	public void testNotNullFields_N(String locale, String serviceID, String celebrityID)
	{
		String[] notNullFields={"ProgramId","Names"};
		String dataItem="Names";
		getStatusAndData(locale,serviceID,celebrityID);
		boolean actual=CreditsActual.isNotNull_N(data, dataItem, notNullFields);
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
