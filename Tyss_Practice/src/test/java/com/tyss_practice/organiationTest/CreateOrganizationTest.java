package com.tyss_practice.organiationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tyss_practice.genericUtils.PrimaryClass;

public class CreateOrganizationTest extends PrimaryClass{

	@Test
	public void createORgtest() throws Throwable {
		
		/* Fetch test script specific data */
		String orgName = excelLib.getExcelData("org", 1, 2) + "_" + wLib.getRandomNumber();
		String org_Type = excelLib.getExcelData("org", 1, 3);
		String org_industry = excelLib.getExcelData("org", 1, 4);		

		/* step 1 : Navigate to Organization page */
		driver.findElement(By.linkText("Organizations")).click();

		/* step 2 : Navigate to create new Organization */
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		/* step 3 : Create Organization */
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		WebElement swb1 = driver.findElement(By.name("accounttype"));
		wLib.select(swb1, org_Type);

		WebElement swb2 = driver.findElement(By.name("industry"));
		wLib.select(swb2, org_industry);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		/* step 4 : Verify the Organization */
		String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		Assert.assertTrue(actOrgName.contains(orgName));
	}
}
