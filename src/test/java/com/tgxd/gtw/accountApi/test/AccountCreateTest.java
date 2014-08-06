package com.tgxd.gtw.accountApi.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.accountApi.AccountResourceActual;
import com.tgxd.gtw.accountApi.AccountResourceUtility;
import com.tgxd.gtw.serviceApi.ScheduleResourceActual;
import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.GeneralUtilities;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class AccountCreateTest {
	
	private JSONObject status;
    private JSONObject data;
    
    private void getStatusAndData(String locale) {
		String response=AccountResourceUtility.postAccountCreate(locale);
		status=RequestResponseParserUtility.parseResponseStatus(response);
	    data=RequestResponseParserUtility.parseResponseData(response);
	    //System.out.println(response);
	}
       
    //@Test(dataProvider="urlData",groups={"Sanity"})
	public void testgetStatus(String locale)
    {
		getStatusAndData(locale);
		long expected=Constants.passStatusCode;
    	long actual=AccountResourceActual.getStatus(status);
    	Assert.assertEquals(actual,expected);
    }
    
    @Test(dataProvider ="urlData",groups={"Sanity"})
    public void testNotNullFieldsforChannel_N(String locale)
    {
    	String [] notNullFields = {"SecurityToken","UserId","AccountInfo"};
    	getStatusAndData(locale);
    	boolean actual=AccountResourceActual.isNotNull_N(data, notNullFields);
    	Assert.assertTrue(actual);
    }

    
    @DataProvider(name = "urlData")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US"}, 
   		};
   	}

}
