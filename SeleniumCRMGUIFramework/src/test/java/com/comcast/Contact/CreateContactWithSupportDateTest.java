package com.comcast.Contact;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class CreateContactWithSupportDateTest  extends BaseClass{
	
	@Test(groups = "RegressionTest")
	public void contactWithSupportDate() throws Throwable
	{
		// read testScritp data from Excel file
				String lastName = excel.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

				// step 2 : navigate to Contact module
				HomePage hp = new HomePage(driver);
				hp.getContactlnk().click();

				// step 3 : click on "create Contact" Button
				ContactPage cp = new ContactPage(driver);
				cp.getCreateNewOrgBtn().click();

				// step 4 : enter all the details & create new Contact
				String endDate = jLib.getSystemDateYYYYDDMM();
				String startDate = jLib.getRequriedDateYYYYDDMM(30);
				CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
				ccp.createContactWithSupportDate(lastName, startDate, endDate);

				// verify Header phone Number info Expected Result
				ContactInfoPage cip = new ContactInfoPage(driver);
				String actStartDate = cip.getSuppoortStartDateInfo().getText();
				Assert.assertEquals(actStartDate.trim(), startDate);
				
				String actendDate = cip.getSupportEndDateInfo().getText();
				Assert.assertEquals(actendDate.trim(), endDate);
	}

}
