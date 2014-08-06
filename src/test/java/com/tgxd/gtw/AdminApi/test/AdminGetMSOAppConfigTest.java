package com.tgxd.gtw.AdminApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.AdminApi.AdminGetMSOAppConfigActual;
import com.tgxd.gtw.AdminApi.AdminResourceUtility;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class AdminGetMSOAppConfigTest {
	private JSONObject status;
    private JSONObject data;

    
    private void getStatusAndData(String locale,String msoProfileId,String deviceType) {
		String response=AdminResourceUtility.getMSOAppConfigRequestResponse(locale, msoProfileId,deviceType);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
    
    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
	public void testgetStatus(String locale,String msoProfileId,String deviceType)
    {
		getStatusAndData(locale,msoProfileId,deviceType);
		long expected=Constants.passStatusCode;
    	long actual=AdminGetMSOAppConfigActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="Locale_ServiceIDs",groups={"Sanity"})
    public void testisDataEmpty_N(String locale,String msoProfileId,String deviceType)
    {
    	getStatusAndData(locale,msoProfileId,deviceType);
    	boolean actual=AdminGetMSOAppConfigActual.isDataEmpty_N(data);
    	Assert.assertFalse(actual);
    }
    
    @Test(dataProvider ="Locale_ServiceIDs")
  	public void testNotNullFields_N(String locale,String msoProfileId,String deviceType)
  	{
  		String [] notNullFields = {"ClientAppInstanceId","CrashEmailRecipientEmailAddress","NameForServer","GetChannelLineUp","GetChannelLineUp","MSOTerms","MSOPrivacyPolicy"};
  		String dataItem="DetailsInfo";
  		getStatusAndData(locale, msoProfileId,deviceType);
  		boolean actual=AdminGetMSOAppConfigActual.isNotNull_N(data,dataItem,notNullFields);
  		Assert.assertTrue(actual);
  	}
    
    
    @DataProvider(name = "Locale_ServiceIDs")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US","369","iphone"}, 
   		    new Object[] {"en-US","346","ipad"},
   			new Object[] {"es-CO","346","ipad"}
   		};
   	}

}
