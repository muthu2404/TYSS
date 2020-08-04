package com.tyss_practice.organiationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tyss_practice.genericUtils.PrimaryClass;

/**
 * 
 * @author Rajasekar
 * 
 *         This class is used for Delete An Organization from Organization page
 *
 */
public class DeleteOrganizationTest extends PrimaryClass {
	/**
	 * This Method is used for Delete An Organization
	 * 
	 * @throws Throwable
	 */
	@Test
	public void deleteOrganization() throws Throwable {

		/* Fetch Test Script specific data */
		String orgName = excelLib.getExcelData("org", 1, 2) + "_" + wLib.getRandomNumber();
		String org_Type = excelLib.getExcelData("org", 1, 3);
		String org_industry = excelLib.getExcelData("org", 1, 4);

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

		/* step 4 : Verify the Creation of Organization */
		String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		Assert.assertTrue(actOrgName.contains(orgName));
		System.out.println("Organization created Sucessfully");

		// Delete the Organization
		/* step 5 : Navigate to the Organization Page */
		driver.findElement(By.linkText("Organizations")).click();

		/* step 6 : Search the Organization */
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		WebElement optionListBox = driver.findElement(By.id("bas_searchfield"));
		wLib.select(optionListBox, excelLib.getExcelData("org", 1, 6));
		driver.findElement(By.name("submit")).click();

		/* step 7 : Select the Organization */
		driver.findElement(By.xpath("//tr[1]//a[text()='" + orgName + "']/..//preceding-sibling::td[2]")).click();

		/* step 8 : Delete the Organization */
		driver.findElement(By.cssSelector("input[value='Delete']")).click();
		wLib.alertOk(driver);
		driver.findElement(By.name("submit")).click();
		/* step 9 : Verify the Deletion of Organization */
		String actDelConformartion = driver.findElement(By.xpath("//span[contains(text(),'No Organization Found')]"))
				.getText();
		String expDelConformation = excelLib.getExcelData("org", 1, 5);
		boolean flag = actDelConformartion.contains(expDelConformation);
		Assert.assertTrue(flag);
	}
}
