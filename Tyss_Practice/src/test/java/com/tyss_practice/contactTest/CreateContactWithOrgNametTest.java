package com.tyss_practice.contactTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tyss_practice.genericUtils.PrimaryClass;
import com.tyss_practice.objectRepositoryLib.ContactInformationPage;
import com.tyss_practice.objectRepositoryLib.ContactPage;
import com.tyss_practice.objectRepositoryLib.CreateNewContactPage;
import com.tyss_practice.objectRepositoryLib.CreateNewOrganizationPage;
import com.tyss_practice.objectRepositoryLib.HomePage;
import com.tyss_practice.objectRepositoryLib.OrganizationInformationPage;
import com.tyss_practice.objectRepositoryLib.OrganizationLookupPage;
import com.tyss_practice.objectRepositoryLib.OrganizationPage;
/**
 * 
 * @author Rajasekar
 * 
 * This class is used to verify Creation and deletion of contact with Organization
 *
 */
public class CreateContactWithOrgNametTest extends PrimaryClass {
	/**
	 * 	This class is used to verify Creation and deletion of contact with Organization
	 * @throws Throwable
	 */
	@Test
	public void createContactWithOrgtest() throws Throwable {

		HomePage hpObj = new HomePage(driver);
		OrganizationPage orgObj = new OrganizationPage(driver);
		CreateNewOrganizationPage cnopObj = new CreateNewOrganizationPage(driver);
		OrganizationInformationPage oipObj = new OrganizationInformationPage(driver);
		OrganizationLookupPage olpObj = new OrganizationLookupPage(driver);
		ContactPage cpObj = new ContactPage(driver);
		CreateNewContactPage cncpObj = new CreateNewContactPage(driver);
		ContactInformationPage cipObj = new ContactInformationPage(driver);
		
		/* Fetch test script specific data */
		String orgName = excelLib.getExcelData("contact", 1, 2) + "_" + wLib.getRandomNumber();
		String org_Type = excelLib.getExcelData("contact", 1, 3);
		String org_industry = excelLib.getExcelData("contact", 1, 4);
		String contactName = excelLib.getExcelData("contact", 1, 5);

		/* step 1 : Navigate to Organization page */
		hpObj.getOrganizationLink().click();;

		/* step 2 : Navigate to create new Organization */
		orgObj.navigateToCreateNewOrgPage();

		/* step 3 : Create Organization */
		cnopObj.getOrgNameTxtBox().sendKeys(orgName);

		WebElement swb1 = cnopObj.getTypeListBox();
		wLib.select(swb1, org_Type);

		WebElement swb2 = cnopObj.getIndustryListBox();
		wLib.select(swb2, org_industry);

		cnopObj.getSaveButton().click();

		/* step 4 : Verify the Organization */
		String actOrgName = oipObj.getOrgInfo().getText();

		Assert.assertTrue(actOrgName.contains(orgName));

		/* step 5 : Navigate to Contact page */
		hpObj.getContactsLink().click();

		/* step 6 : Navigate to create new Contact page */
		cpObj.getCreateContact().click();

		/* step 7 : Create new Contact */
		cncpObj.getLastNameTxtBox().sendKeys(contactName);
		cncpObj.getOrgNameLookup().click();

		// Goto new tab
		wLib.switchToNewTab(driver, "specific_contact_account_address");

		olpObj.getSearchTxtBox().sendKeys(orgName);
		olpObj.getSearchBtn().click();
		olpObj.getSelectOrg().click();

		// come back to parent Window
		wLib.switchToNewTab(driver, "Administrator - Contacts");

		cncpObj.getSaveButton().click();

		/* step 8: Verify the Contact Creation */
		String actconatct = cipObj.getContactInfo().getText();
		Assert.assertTrue(actconatct.contains(contactName));
	}
}
