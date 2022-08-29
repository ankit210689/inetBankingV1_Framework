package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;//local driver object
	
	public LoginPage(WebDriver rdriver)//remote driver object to be called in other classes
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}

	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	
	//@FindBy(xpath="//td/input[@name='password']")
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="//a[contains(text(),'Log out')]")
	@CacheLookup
	WebElement lnkLogout;
	
	
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit()
	{
		btnLogin.click();
	}
	
	public void clickLogout()//Action method for clicking logout
	{
		lnkLogout.click();
	}
	
}
