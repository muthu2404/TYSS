package com.tyss_practice.contactTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tyss_practice.genericUtils.PrimaryClass;

public class CreateContactWithOrgNametTest extends PrimaryClass {

	@Test
	public void createContactWithOrgtest() throws Throwable {

		/* Fetch test script specific data */
		String orgName = excelLib.getExcelData("contact", 1, 2) + "_" + wLib.getRandomNumber();
		String org_Type = excelLib.getExcelData("contact", 1, 3);
		String org_industry = excelLib.getExcelData("contact", 1, 4);
		String contactName = excelLib.getExcelData("contact", 1, 5);

		/* step 1 : Navigate to Organization page */
		driver.findElement(By.linkText("Organizations")).click();

		/* step 2 : Navigate to create new Organization page */
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		/* step 3 : Create Organization */
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		WebElement swb1 = driver.findElement(By.name("accounttype"));
		wLib.select(swb1, org_Type);

		WebElement swb2 = driver.findElement(By.name("industry"));
		wLib.select(swb2, org_industry);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		/* step 4 : Verify the creation of Organization */
		String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		Assert.assertTrue(actOrgName.contains(orgName));

		/* step 5 : Navigate to Contact page */
		driver.findElement(By.linkText("Contacts")).click();

		/* step 6 : Navigate to create new Contact page */
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		/* step 7 : Create new Contact */
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// Goto new tab
		wLib.switchToNewTab(driver, "specific_contact_account_address");

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(orgName)).click();

		// come back to parent Window
		wLib.switchToNewTab(driver, "Administrator - Contacts");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		/* step 8: Verify the Organization */
		String actconatct = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(actconatct.contains(contactName));
	}
}
