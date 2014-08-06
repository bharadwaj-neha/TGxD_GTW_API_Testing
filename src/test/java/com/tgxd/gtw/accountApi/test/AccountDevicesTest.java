package com.tgxd.gtw.accountApi.test;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.accountApi.AccountResourceUtility;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class AccountDevicesTest {
	
	private JSONObject status;
    private JSONObject data;
    
    private void getStatusAndData(String locale) {
		String response=AccountResourceUtility.postAccountDevices(locale);
		System.out.println(response);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	}
       
    @Test(dataProvider="urlData",groups={"Sanity"})
	public void testgetStatus(String locale)
    {
		getStatusAndData(locale);
		//long expected=Constants.passStatusCode;
    	//long actual=AccountResourceActual.getStatus(status);
    	//Assert.assertEquals(actual,expected);
    }
    
    @DataProvider(name = "urlData")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US"}, 
   		    new Object[] {"es-CO"},
   		};
   	}

}
