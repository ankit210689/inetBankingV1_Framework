package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	
	WebDriver ldriver;//Initialize an object of WebDriver interface
	
	public AddCustomerPage(WebDriver rdriver) {//Create a constructor
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	//Using how keyword in @FindBy approach to identify web-elements.
	//This is just for variation as in different organizations either of the 2 ways can be used in page objects of frameworks
	@FindBy (how=How.LINK_TEXT, using="New Customer")
	@CacheLookup
	WebElement lnkAddNewCustomer;
	
	@FindBy(how=How.NAME, using="name")
	@CacheLookup
	WebElement txtCustomerName;
	
	@FindBy (how=How.NAME, using="rad1")
	@CacheLookup
	WebElement rdGender;
	
	@FindBy (how=How.NAME,using="dob")
	@CacheLookup
	WebElement txtdob;
	
	@FindBy (how=How.NAME,using="addr")
	@CacheLookup
	WebElement txtaddress;
	
	@FindBy (how=How.NAME,using="city")
	@CacheLookup
	WebElement txtcity;
	
	@FindBy (how=How.NAME,using="state")
	@CacheLookup
	WebElement txtstate;
	
	@FindBy (how=How.NAME,using="pinno")
	@CacheLookup
	WebElement txtpinno;
	
	@FindBy (how=How.NAME,using="telephoneno")
	@CacheLookup
	WebElement txttelephoneno;
	
	@FindBy (how=How.NAME,using="emailid")
	@CacheLookup
	WebElement txtemailid;
	
	@FindBy (how=How.NAME,using="sub")
	@CacheLookup
	WebElement btnSubmit;
	
	@FindBy (how=How.NAME,using="res")
	@CacheLookup
	WebElement btnReset;
	
	
	//Next we will write action methods for every web-element identified above
	
	public void clickAddNewCustomer()
	{
		lnkAddNewCustomer.click();
	}
	
	public void custName(String cname)
	{
		txtCustomerName.sendKeys(cname);
	}
	
	public void custGender(String cgender)
	{
		rdGender.click();
	}
	
	public void custdob (String mm, String dd, String yy)
	{
		txtdob.sendKeys(mm);
		txtdob.sendKeys(dd);
		txtdob.sendKeys(yy);
	}
	
	public void custaddress(String caddress)
	{
		txtaddress.sendKeys(caddress);
	}
	
	public void custcity(String ccity)
	{
		txtcity.sendKeys(ccity);
	}
	
	public void custstate(String cstate)
	{
		txtstate.sendKeys(cstate);
	}
	
	public void custpinno(String cpinno)//public void custpinno(int cpinno)-If we are taking pin-no as integer value
	{
		txtpinno.sendKeys(String.valueOf(cpinno));
		//String.valuof is required when we are taking cpinno as int. 
		//However, in this case we are passing cpinno as string so it is not required.
		//Although not needed we are still keeping it for future reference.
	}
	
	public void custtelephoneno(String ctelephoneno)
	{
		txttelephoneno.sendKeys(ctelephoneno);
	}
	
	public void custemailid(String cemailid)
	{
		txtemailid.sendKeys(cemailid);
	}
	
	public void custsubmit()
	{
		btnSubmit.click();
	}
	
	public void custreset()
	{
		btnReset.click();
	}

}
//This is the complete page object class for Add New Customer page