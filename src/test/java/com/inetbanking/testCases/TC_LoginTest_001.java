package com.inetbanking.testCases;

import java.io.IOException;

//import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void loginTest() throws InterruptedException, IOException
	{
		
		Logger.info("URL is opened");
		
		LoginPage lp=new LoginPage(driver);
		
		
		lp.setUserName(username);
		Logger.info("Entered username");//Using Logger method from BaseClass created the test-output folder that will contains logs for all tests
		
		lp.setPassword(password);
		Logger.info("Entered password");
		
		//Work here to fix the handling of login screen google analytics pop-up		
		
		//driver.findElement(By.name("robots")).click();
		//driver.findElement(By.linkText("close")).click();
		
		//I will try javascript click for this purpose now
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("document.getElementsByClassName('mat-button-wrapper').item(0).click()");
		
		
		
		Thread.sleep(3000);//This line can be modified by adding wait.until condition for clickSubmit button elementToBeClickable
		
		lp.clickSubmit();
		
		//System.out.println(driver.getTitle());
		
		if (driver.getTitle().equals("GTPL Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			Logger.info("Login test passed");

		}
		else
		{
			captureScreen(driver,"logintest");//calling captureScreen method from BaseClass when the test case fails
			Assert.assertTrue(false);
			Logger.info("Login test passed");
		}
		
	}

}
