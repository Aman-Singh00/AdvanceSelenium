package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewOrganizationPage  extends WebDriverUtility{
	
	
	
	WebDriver driver;
	 public CreatingNewOrganizationPage(WebDriver driver) {           
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 
	 @FindBy(id = "phone")
	 private WebElement phoneNumberEdt;
	 
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDD;
	
	@FindBy(name = "accounttype")
	private WebElement typeDD;

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
		
	}

	public void createOrg(String orgName , String industry, String type) {

		orgNameEdt.sendKeys(orgName);
		select(industryDD,industry);
		select(typeDD,type);
		saveBtn.click();
		
	}
	public void createOrgPhoneNumber(String orgName , String phoneNumber) {

		orgNameEdt.sendKeys(orgName);
		phoneNumberEdt.sendKeys(phoneNumber);	
		saveBtn.click();
		
	}


}



















