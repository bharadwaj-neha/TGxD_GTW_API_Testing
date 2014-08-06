package com.tgxd.gtw.AdminApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.AdminApi.AdminGetAllMSOProfileActual;
import com.tgxd.gtw.AdminApi.AdminResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class AdminGetAllMSOProfileTest {
	
	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String hd) {
		String response=AdminResourceUtility.getAllMSOProficeRequestResponse(locale, hd);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	    //System.out.println("Data:"+data);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String hd)
    {
		getStatusAndData(locale,hd);
		long expected=Constants.passStatusCode;
    	long actual=AdminGetAllMSOProfileActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String hd)
    {
    	getStatusAndData(locale,hd);
    	boolean actual=AdminGetAllMSOProfileActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
  	public void testNotNullFields_N(String locale,String hd)
  	{
  		String [] notNullFields = {"ProfileId","Id","Name","ResBaseUrl","IsMultiTenant"};
  		String dataItem="MsoLists";
  		getStatusAndData(locale, hd);
  		boolean actual=AdminGetAllMSOProfileActual.isNotNull_N(data,dataItem,notNullFields);
  		Assert.assertTrue(actual);
  	}
    
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","true"}, 
   		    new Object[] {"en-US","false"},
   			new Object[] {"es-CO","true"},
   			new Object[] {"es-CO","false"}
   		};
   	}

}
