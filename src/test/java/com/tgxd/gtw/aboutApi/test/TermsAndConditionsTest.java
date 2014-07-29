package com.tgxd.gtw.aboutApi.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tgxd.gtw.aboutApi.AboutResourceUtility;

public class TermsAndConditionsTest {
	
	private String response;
    
    private void getData(String locale) {
		response=AboutResourceUtility.getTermsAndConditions(locale);
	} 
    
    @Test(dataProvider="urlData",groups={"Sanity"})
    public void testData(String locale)
    {
    	getData(locale);
		System.out.println(response);
		response.contains("Terms of Use");
		response.contains("ACCEPTANCE OF TERMS OF USE FOR THIS APPLICATION");
		response.contains("YOUR OBLIGATIONS AND CONDUCT");
		response.contains("DISCLAIMER");
		response.contains("CONTENT SUBMITTED TO ROVI");
		response.contains("INDEMNITY");
		response.contains("NOTICES; MODIFICATION AND TERMINATION OF SERVICES; AMENDMENT OF TERMS");
		response.contains("INTELLECTUAL PROPERTY RIGHTS");
		response.contains("DISCLAIMER OF WARRANTIES");
		response.contains("LIMITATION OF LIABILITY");
		response.contains("PRIVACY POLICY");
		response.contains("AVAILABILITY OF SERVICES");
		response.contains("THIRD PARTY LOGOS");
		response.contains("GENERAL TERMS");
    }
    
    @DataProvider(name = "urlData")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US"}, 
   		    new Object[] {"es-CO"},
   		};
   	}
}
