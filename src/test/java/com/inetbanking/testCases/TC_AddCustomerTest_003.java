package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass //We have to extend the baseclass in every test case to use the setup of baseclass in test-cases

{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		//LoginPage objects will be called for login
		
		LoginPage lp=new LoginPage(driver);
		
		Thread.sleep(3000);
		
		//Next 2 lines are to close google analytics consent form
		driver.switchTo().frame("ccpa-consent-notice");
		driver.findElement(By.xpath("//button[@id='close']")).click();
		
		
		lp.setUserName(username);
		Logger.info("User name is provided");
		lp.setPassword(password);
		Logger.info("Password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		//Create objects of AddCustomerPage to create a new customer
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		//Call all the methods of AddCustomerPage one by one
		
		addcust.clickAddNewCustomer();
		Thread.sleep(3000);
		
		Logger.info("Providing customer details......");
		
		addcust.custName("Ankit");
		addcust.custGender("male");
		addcust.custdob("01", "01", "1990");
		Thread.sleep(3000);
		addcust.custaddress("USA");
		addcust.custcity("San Diego");
		addcust.custstate("CA");
		addcust.custpinno("11111");
		addcust.custtelephoneno("1234567890");
		
		//For email there is a restriction that one customer can have only one email-id.
		//So if we hard code the email-id here the first instance will pass, but after that it will fail as email is not unique anymore.
		//Hence, we have to generate a random email id every time we run this test case
				
		String email= randomestring()+"gmail.com";
		addcust.custemailid(email);//This is how we can pass dynamic values to static methods
		
		addcust.custsubmit();
		//Thread.sleep(3000);
		
		Logger.info("Validation started.....");
		
		//boolean res=driver.getPageSource().contains("Connection failed: Access denied for user 'root'@'localhost' (using password: NO)");
		boolean res=driver.switchTo().alert().getText().contains("Please fill all fields");
		
		
		if (res==true)
		{
			Assert.assertTrue(true);
			Logger.info("Test case passed....");
			
		}
		else
		{
			captureScreen(driver,"addnewCustomer");
			Assert.assertTrue(false);
			Logger.info("Test case failed....");
		}
	}
	
	//Moving both these methods to base class
	//To generate random data (email id in this case) we will define a user-defined function
	//public String randomestring() //randomestring() method will return a random string. This method should be created in testcase itself if required in one test case only or in baseclass if we want them to be used across all the test cases
	//{
		//String generatedString=RandomStringUtils.randomAlphabetic(8);//"RandomStringUtils" is a pre-defined class in java. There are 2 methods that can be used as required: 1) "randomAlphabetic" is used to generate random characters. 2) "randomAlphanumeric" will generate random numbers
		//And the user-defined count 8 will generate 8 characters string
		//And we are storing this random string into a variable "generatedString".
		//return (generatedString);
	//}
	
	//public String randomeNum() //randomeNum() method will return a random number
	//{
		//String generatedString2=RandomStringUtils.randomAlphanumeric(4);
		//return (generatedString2);
	//}
}
