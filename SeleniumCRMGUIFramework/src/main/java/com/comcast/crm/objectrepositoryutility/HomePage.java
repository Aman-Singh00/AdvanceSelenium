package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	 public HomePage(WebDriver driver) {           
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(linkText = "Products")
		private WebElement productLink;
	
	public WebDriver getDriver() {
		return driver;
	}


	public WebElement getProductLink() {
		return productLink;
	}


	public WebElement getAdminImg() {
		return adminImg;
	}


	public WebElement getSingNOutLnk() {
		return singNOutLnk;
	}

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlnk;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignlnk;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement singNOutLnk;
	

	public WebElement getOrgLink() {
		return orgLink;
	}


	public WebElement getContactlnk() {
		return contactlnk;
	}



	public WebElement getCampaignlnk() {
		return campaignlnk;
	}



	public WebElement getMoreLink() {
		return moreLink;
	}



	public void navigateToCampaginPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaignlnk.click();
	}
	
	
	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();
		singNOutLnk.click();
	}
	
	
	

}
