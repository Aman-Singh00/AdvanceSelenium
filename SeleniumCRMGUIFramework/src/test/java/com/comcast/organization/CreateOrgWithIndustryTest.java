package com.comcast.organization;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWithIndustryTest extends BaseClass {

	@Test(groups = "SmokeTest")
	public void orgWithIndustry() throws Throwable {

		// read testScritp data from Excel file

		String orgName = excel.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		System.out.println(orgName);
		String industry = excel.getDataFromExcel("org", 4, 3);
		System.out.println(industry);
		String type = excel.getDataFromExcel("org", 4, 4);
		System.out.println(type);

		// step 2 : navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3 : click on "create Organization" Button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step 4 : enter all the details & create new Organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);

		// verify the industries and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(orgName));
		String actIndutries = oip.getIndustryInfo().getText();
		Assert.assertEquals(actIndutries.trim(), industry);

		String actType = oip.getTypeInfo().getText();
		Assert.assertEquals(actType.trim(), type);

	}

}
