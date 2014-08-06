package com.tgxd.gtw.utilities;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/*
 * Fetches:
 * - Set top box DeviceID (getDeviceId())
 * 
 */
public class TestDataProviders {
	
	public static String getSecurityToken()
	{
		String securityToken=null;
		String resourceIdentifier = "http://qa-g.ceidd.net/puma/v2.2/account/authenticate";
		String postData = "{\"UserName\": \"tgxd_test\",\"Password\": \"tgxd_test\",\"ClientUID\": \"0\"}";
		
		String response=RequestResponseParserUtility.response_POST(resourceIdentifier, postData);
		JSONObject responseContent=getResponse(response);
		JSONObject content=(JSONObject) responseContent.get("Authentication");
	    securityToken=(String) content.get("SecurityToken");

	    return securityToken;
	}
	
	
	public static String getDeviceId()
	{
		WebDriver driver=new HtmlUnitDriver();
		driver.get("http://tul1cstapp04/Lomb/Terminals");
		//Setting environment
		String environmentName="QA";
		String manufacturerName="Cisco";
		String clientVersion="6.0";
		
		selectSubElement(driver, "Environment", environmentName);
		
		//Setting manufacturer details
		selectSubElement(driver, "Manufacturer", manufacturerName);
		
		//Setting client version for box
		selectSubElement(driver, "ClientVersion", clientVersion);
		
	
		WebElement createVirtualSTB=driver.findElement(By.id("AddSTB"));
		createVirtualSTB.click();
		
		delayUsingSleep(9000);
		
		
		WebElement deviceProfileId=driver.findElement(By.xpath(".//*[@id='main']/div/dl[1]/dd[3]"));
		String deviceId=deviceProfileId.getText();
		return deviceId;
	}

	private static void delayUsingSleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static JSONObject getResponse(String response) {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)jsonParser.parse(response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	
	private static void selectSubElement(WebDriver driver,String titleName,String optionToBeSelected)
	{
		WebElement environment=driver.findElement(By.id(titleName));
		List<WebElement> env_options=environment.findElements(By.tagName("option"));
		for(WebElement w:env_options)
		{
			if(w.getText()==optionToBeSelected)
			{
				w.click();
			}
		}
		
	}

}
