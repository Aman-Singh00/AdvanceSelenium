package com.comcast.Contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class CreateContactTest extends BaseClass {

	@Test(groups = "RegressionTest")
	public void contact() throws Throwable {

		/* read testScritp data from Excel file */
		String lastName = excel.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step 2 : navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		// step 3 : click on "create Contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();

		// step 4 : enter all the details & create new Contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);

		// verify Header phone Number info Expected Result

		String actHearder = cp.getHeaderMsg().getText();
		boolean status = actHearder.contains(lastName);
		Assert.assertEquals(status, true);

		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);
		soft.assertAll();

	}

}
