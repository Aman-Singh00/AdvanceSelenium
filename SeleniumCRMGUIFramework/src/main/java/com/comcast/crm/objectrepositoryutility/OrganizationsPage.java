package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;
	 public OrganizationsPage(WebDriver driver) {           
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	
	 @FindBy(name="search_text")
	 private WebElement serchEdt;
	 
	 @FindBy(name="search_field")
	 private WebElement searchDD;
	 
	 @FindBy(name="search")
	 private WebElement searchBtn;
	 
	 @FindBy(name = "submit")
	 private WebElement submitBtn;
	 
	 
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getSerchEdt() {
		return serchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createNewOrgBtn;

	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	
	

}
