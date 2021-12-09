package com.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class WebIteractableUtils{
	public static Logger log;

	@Parameters("browser")
	@BeforeClass
	public static void setUp(String br) {
		log = Logger.getLogger("TesNg");
		PropertyConfigurator.configure("D:\\AgileProject\\Config\\Log4J.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", PropertyUtil.getProperty("chromepath"));
			DriverUtils.setDriver(new ChromeDriver());
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", PropertyUtil.getProperty("firefoxpath"));
			DriverUtils.setDriver(new FirefoxDriver());
		} else if (br.equals("edge")) {
			System.setProperty("webdriver.edge.driver", PropertyUtil.getProperty("edgepath"));
			DriverUtils.setDriver(new EdgeDriver());
		}

		DriverUtils.getDriver().manage().window().maximize();
	//	DriverUtils.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		DriverUtils.getDriver().get(PropertyUtil.getProperty("baseUrl"));
		log.info("Url launch successfully");

	}

	@AfterClass
	public static void tearDown() {
		DriverUtils.getDriver().close();

	}

	public static String getTitle() {

		return DriverUtils.getDriver().getTitle();
	}

	public static void clear(WebElement element) {

		element.clear();

	}

	public static String getCurrentUrl() {

		return DriverUtils.getDriver().getCurrentUrl();

	}

	public static String getPgeResourse() {
		return DriverUtils.getDriver().getPageSource();
	}

	public static String getWindowHandle() {
		String window = DriverUtils.getDriver().getWindowHandle();
		return window;
	}

	public static Set<String> WindowHandles() {

		return DriverUtils.getDriver().getWindowHandles();
	}

	/*
	 * public static void WindowHandles(WebElement element) { element.click();
	 * 
	 * Set<String> windowhandles = DriverUtils.getDriver().getWindowHandles();
	 * 
	 * Iterator<String> iterator = windowhandles.iterator(); String parentwindow =
	 * iterator.next();
	 * 
	 * String childwindow = iterator.next();
	 * 
	 * DriverUtils.getDriver().switchTo().window(childwindow);
	 * 
	 * }
	 */

	public static void navigateBack() {
		DriverUtils.getDriver().navigate().back();
	}

	public static void refresh() {
		DriverUtils.getDriver().navigate().refresh();
	}

	public static void navigateForward() {
		DriverUtils.getDriver().navigate().forward();

	}

	public static void sendKeys(WebElement element, String value) {
		element.sendKeys(value);

	}

	public static String getAttribute(WebElement element, String attributeName) {
		String value = element.getAttribute(attributeName);

		return value;

	}

	public static void click(WebElement element) {
		element.click();
	}

	public static void getCssValue(WebElement element, String attributeName) {
		element.getCssValue(attributeName);
	}

	public static String getText(WebElement element) {
		return element.getText();
	}

	public static boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public static boolean isEnabled(WebElement element) {

		return element.isEnabled();

	}

	public static boolean isSelected(WebElement element) {
		return element.isSelected();

	}

	public static void SelectClass(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);

	}

	public static void ActionMoveToElement(WebElement element) {
		Actions action = new Actions(DriverUtils.getDriver());
		action.moveToElement(element).perform();

	}

	public static void DragandDrop(WebElement sourceElement, WebElement targetElement) {
		Actions action = new Actions(DriverUtils.getDriver());
		action.dragAndDrop(sourceElement, targetElement).perform();
	}

	public static void Allcheckboxes(WebElement... element) {
		for (WebElement checkboxes : element) {
			checkboxes.click();
		}
	}

	public static void checkboxes(String attributeName, String value, WebElement... element) {
		for (WebElement checkbox : element) {
			if (checkbox.getAttribute(attributeName).equalsIgnoreCase(value))
				checkbox.click();
		}
	}

	public static void AlertAccept() {
		DriverUtils.getDriver().switchTo().alert().accept();
	}

	public static void AlertDismiss() {
		DriverUtils.getDriver().switchTo().alert().dismiss();
	}

	public static void AlertSendKeys(String value) {
		Alert alert = DriverUtils.getDriver().switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
		
	}

	public static void AutoItFileUpload(WebElement element) {
		element.click();
		String filepath = "";
		try {
			Runtime.getRuntime().exec(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String takeScrenshot(WebDriver driver, String TName) {
		String timeStamp = new SimpleDateFormat("yyyy-MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot screen = (TakesScreenshot)driver;
		File file = screen.getScreenshotAs(OutputType.FILE);
		String path = "D:\\AgileProject\\ScreenShots\\Screenshot_" + TName + timeStamp + ".png";
		try {
			FileUtils.copyFile(file, new File(path));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;
	}

}
