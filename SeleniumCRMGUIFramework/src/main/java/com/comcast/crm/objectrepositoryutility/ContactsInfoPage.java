package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
	WebDriver driver;
	 public ContactsInfoPage(WebDriver driver) {           
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	
	
	 
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement suppoortStartDateInfo;
	
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement supportEndDateInfo;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgNameInfo;
	
	
	
	public WebElement getSuppoortStartDateInfo() {
		return suppoortStartDateInfo;
	}



	public WebElement getSupportEndDateInfo() {
		return supportEndDateInfo;
	}



	public WebElement getOrgNameInfo() {
		return orgNameInfo;
	}



	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	

	
	

}
