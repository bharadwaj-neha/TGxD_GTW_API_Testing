package com.tgxd.gtw.aboutApi;

import com.tgxd.gtw.utilities.Constants;
import com.tgxd.gtw.utilities.RequestResponseParserUtility;

public class AboutResourceUtility {
	
	public static String getTermsAndConditions(String locale)
	{ 
		String resourceIdentifier = Constants.environment + "v1/terms?locale=" + locale; 
		System.out.println(resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getPrivacyPolicy(String locale)
	{ 
		String resourceIdentifier = Constants.environment + "v1/privacypolicy?locale=" + locale;
		System.out.println(resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
	
	public static String getThirdPartyLicenseTerms(String locale,String deviceType)
	{ 
		String resourceIdentifier = Constants.environment + "v1/3rdpartylicenseterms?locale=" + locale + "&devicetype=" + deviceType;
		System.out.println(resourceIdentifier);
		return RequestResponseParserUtility.response_GET(resourceIdentifier);
	}
}
