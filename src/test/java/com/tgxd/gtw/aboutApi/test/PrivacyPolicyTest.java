/*
package com.tgxd.gtw.aboutApi.test;




import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tgxd.gtw.aboutApi.AboutResourceUtility;

public class PrivacyPolicyTest {
	
	private String response;
    
    private void getData(String locale) {
		response=AboutResourceUtility.getPrivacyPolicy(locale);
	} 
    
    @Test(dataProvider="urlData",groups={"Sanity"})
    public void testData(String locale)
    {
    	getData(locale);
		System.out.println(response);
		response.contains("Privacy Policy");
		response.contains("Information Collection");
		response.contains("Notice");
		response.contains("Cookies and Tracking Technology");
		response.contains("Advertising");
		response.contains("How We Use Information Collected");
		response.contains("Information Sharing and Disclosure");
		response.contains("Data Security");
		response.contains("Communications Preferences");
		response.contains("Third Party Websites, Products and Services");
		response.contains("Commitment To Children's Privacy");
		response.contains("Public Areas");
		response.contains("Anonymous Device Information");
		response.contains("How long we keep Personal Information");
		response.contains("Our do not contact policy");
		response.contains("Your access to information and rights under the Communications Act");
		response.contains("Changes to this Privacy Policy");
		response.contains("Translations");
		response.contains("Inquiries or Suggestions");
    }
    
    @DataProvider(name = "urlData")
   	public static Object[][] provideData() {
    
   		return new Object[][] { 
   			new Object[] {"en-US"}, 
   		    new Object[] {"es-CO"},
   		};
   	}
}
*/