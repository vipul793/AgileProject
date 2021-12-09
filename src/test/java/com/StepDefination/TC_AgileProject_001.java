package com.StepDefination;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObject.LoginPage;
import com.Utilities.DriverUtils;
import com.Utilities.PropertyUtil;
import com.Utilities.WebIteractableUtils;

public class TC_AgileProject_001 extends WebIteractableUtils {
	LoginPage lp;

	@Test()
	public void LoginTest() {

		lp = new LoginPage(DriverUtils.getDriver());
		if (lp.agileproject.isDisplayed()) {
			click(lp.agileproject);
			Assert.assertTrue(true);

			log.info("Agile project is enabled and displayed");
		} else {
			Assert.assertFalse(false);

			log.info("Agile project is not enabled nor displayed");
		}

		sendKeys(lp.username, PropertyUtil.getProperty("username"));

		log.info("User entered username");
		sendKeys(lp.password, PropertyUtil.getProperty("password"));
		log.info("User entered password");

		click(lp.loginButton);
		log.info("User logged successfully");
		if (DriverUtils.getDriver().getTitle().equals("Guru99 Bank Customer HomePage")) {
			Assert.assertTrue(true);
			log.info("Login Test Passed");

		} else {
			Assert.assertFalse(false);
			log.info("Login Test failed");
		}

	}
	@Test
	public void m1() {
		System.out.println("method m1");
	}

}
