package com.tgxd.gtw.aboutApi.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.aboutApi.AboutResourceUtility;

public class ThirdPartyLicenseTermsTest {

	private String response;
    
    private void getData(String locale, String deviceType) {
		response=AboutResourceUtility.getThirdPartyLicenseTerms(locale,deviceType);
	} 
    
    @Test(dataProvider="urlData",groups={"Sanity"})
    public void testData(String locale, String deviceType)
    {
    	getData(locale, deviceType);
		System.out.println(response);
		response.contains("http://www.rovicorp.com/pl/license.txt");
		response.contains("The following license applies to Bitstream Vera and Toga");
		response.contains("The following license applies to LodePNG");
		response.contains("The following license applies to Freetype");
		response.contains("The following license applies to LDNS");
    }
    
    @DataProvider(name = "urlData")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US", ""}, 
   		    new Object[] {"es-CO", "ipad"},
   		    new Object[] {"en-US", "iphone"},
   		    new Object[] {"es-CO", "android"},
   		};
   	}	
	
}
