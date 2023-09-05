package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage extends WebDriverUtility {

	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "support_start_date")
	private WebElement startDate;

	@FindBy(name = "support_end_date")
	private WebElement endDate;

	@FindBy(name = "lastname")
	private WebElement lastNameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgLookUp;

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createContact(String conatctName) {
		lastNameEdt.sendKeys(conatctName);
		saveBtn.click();

	}

	public void createContactWithSupportDate(String conatctName, String startData, String enddate) {
		lastNameEdt.sendKeys(conatctName);
		startDate.clear();
		startDate.sendKeys(startData);
		endDate.clear();
		endDate.sendKeys(enddate);
		saveBtn.click();

	}

	public void createContactwithOrg(String conatctName, String orgName) throws InterruptedException {
		lastNameEdt.sendKeys(conatctName);
		orgLookUp.click();
		// switch To child Window
		switchToTabOnURL(driver, "module=Accounts");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getSerchEdt().sendKeys(orgName);
		op.getSearchBtn().click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		// switch to parent Window
		switchToTabOnURL(driver, "module=Contacts");
		saveBtn.click();

	}

}
