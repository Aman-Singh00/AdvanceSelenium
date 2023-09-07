package com.comcast.organization;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithPhonenoTest extends BaseClass{

	@Test(groups = {"SmokeTest","RegressionTest"})
	public void orgWithPhone() throws Throwable {
		// read testScritp data from Excel file

		String orgName = excel.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = excel.getDataFromExcel("org", 7, 3);

       //step 2 : navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

       //step 3 : click on "create Organization" Button

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

       //step 4 : enter all the details & create new Organization
		
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgPhoneNumber(orgName, phoneNumber);

        //verify Header phone Number info Expected Result 
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
	    Assert.assertEquals(true, actOrgName.contains(orgName));
		String actPhoneNumber = oip.getPhoneNumInfo().getText();
	    Assert.assertEquals(actPhoneNumber, phoneNumber);
	}

}
