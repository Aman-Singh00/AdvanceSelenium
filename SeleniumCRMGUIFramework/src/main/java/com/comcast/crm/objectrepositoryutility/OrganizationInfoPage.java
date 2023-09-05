package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;
	 public OrganizationInfoPage(WebDriver driver) {           
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	
		@FindBy(id = "mouseArea_Organization Name")
		private WebElement orgNameInfo;
		
		@FindBy(id = "dtlview_Phone")
		private WebElement phoneNumInfo;
		
		@FindBy(id = "dtlview_Industry")
		private WebElement industryInfo;
		
		@FindBy(id = "dtlview_Type")
		private WebElement typeInfo;
		
	 
	 
	public WebElement getIndustryInfo() {
			return industryInfo;
		}
		public void setOrgNameInfo(WebElement orgNameInfo) {
			this.orgNameInfo = orgNameInfo;
		}
	public WebElement getOrgNameInfo() {
			return orgNameInfo;
		}
		public WebElement getPhoneNumInfo() {
			return phoneNumInfo;
		}
		public WebElement getTypeInfo() {
			return typeInfo;
		}

	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	

	
	

}
