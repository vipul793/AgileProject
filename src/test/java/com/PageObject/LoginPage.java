package com.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}
	
	@FindBy(xpath = "//a[text()='Agile Project']")
	public WebElement agileproject;
	
	@FindBy(name="uid")
	public WebElement username;
	
	@FindBy(name="password")
	public WebElement password;
	
	@FindBy(name="btnLogin")
	public WebElement loginButton;
}
