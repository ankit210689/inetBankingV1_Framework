//Data driven test case

package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	@Test(dataProvider="LoginData")//It refers the dataProvider method LoginData
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		Logger.info("user name provided");
		lp.setPassword(pwd);
		Logger.info("password provided");
		
		Thread.sleep(3000);
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		if (isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();//return control on main login window
			Assert.assertTrue(false);
			Logger.warn("Login failed");
		}
		
		else
		{
			Assert.assertTrue(true);
			Logger.warn("Login passed");
			lp.clickLogout();
			
			Thread.sleep(3000);
			
			driver.switchTo().alert().accept();//close the logout alert
			driver.switchTo().defaultContent();
		}
		
		
	}
	
	//User-define Method to verify invalid user alert present or not
	public boolean isAlertPresent()
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	
	@DataProvider(name="LoginData")//DataProvider method begins
	String [][] getData() throws IOException
	{
		//String path="C:\\Users\\hp\\eclipse-workspace\\inetBankingV1_Framework\\src\\test\\java\\com\\inetbanking\\testData\\LoginData.xlsx";
		
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		//Creating a 2-D array to store data from excel sheet
		String logindata[][]=new String[rownum][colcount];
		
		for (int i=1;i<=rownum;i++)
		{
			for (int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);//i=1,j=0
			}
		}
		return logindata;
	}//DataProvider method ends	
	
}
