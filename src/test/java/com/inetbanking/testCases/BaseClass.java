package com.inetbanking.testCases;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationBuilder;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;

public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig();//Creating an object of ReadConfig.java file

	public String baseURL=readconfig.getApplicationURL();//Methods from ReadConfig.java file called through readconfig object
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger Logger;
	
	@Parameters("browser")//For using the XML file
	@BeforeClass
	public void setup(String br)
	{
		
		//Check youtube video to find the correct log4j.properties file to be used for this purpose.
		//Logger class is not working as of now.
		Logger= LogManager.getLogger("ebanking");//Referred google to find suggestion to replace log4j.Logger.getLogger() to logging.log4j.LogManager.getLogger()
		//PropertyConfigurator.configure("Log4j.properties");
		PropertiesConfigurationBuilder.newConfigurationBuilder().setConfigurationName("Log4j.properties");
	
		if(br.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//Drivers//chromedriver.exe");
			//Copy the chromedriver.exe path from properties and remove the project directory path to append it using getProperty method.
			//Also replace the backward slashes(\\) with forward slashes(//) in the remaining path
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver=new ChromeDriver();	
		}
		else if (br.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver",readconfig.getEdgePath());
			driver=new EdgeDriver();	
		}
		driver.manage().window().maximize();
		driver.get(baseURL);
		
	}
	
	@AfterClass
	public void tearDown()
	{
	driver.quit();
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException //method to take screenshots
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
	}
	
	//Following methods are currently being used in TC_AddCustomerTest_003. But since they are now defined in baseclass we can call them in all test cases that extends the BaseClass
	//To generate random data (email id in this case) we will define a user-defined function
		public String randomestring() //randomestring() method will return a random string. This method should be created in testcase itself if required in one test case only or in baseclass if we want them to be used across all the test cases
		{
			String generatedString=RandomStringUtils.randomAlphabetic(8);//"RandomStringUtils" is a pre-defined class in java. There are 2 methods that can be used as required: 1) "randomAlphabetic" is used to generate random characters. 2) "randomAlphanumeric" will generate random numbers
			//And the user-defined count 8 will generate 8 characters string
			//And we are storing this random string into a variable "generatedString".
			return (generatedString);
		}
		
		public String randomeNum() //randomeNum() method will return a random number
		{
			String generatedString2=RandomStringUtils.randomAlphanumeric(4);
			return (generatedString2);
		}
}
