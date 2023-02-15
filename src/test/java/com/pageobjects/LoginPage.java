package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver dr;
	
	public LoginPage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	
	
	//@FindBy(name = "uid")
	
	@FindBy(xpath = "//input[@name='uid']")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement userpassword;
	
	
	@FindBy(name="btnLogin")
	WebElement loginbtn;
	
	
	@FindBy(xpath="//a[contains(text(),'Log out')]")
	WebElement logout;
	
	public void enter_username(String uname) {
		username.sendKeys(uname);
	}
	
	public void enter_password(String password) {
		userpassword.sendKeys(password);
	}
	
	public void click_login() {
		loginbtn.click();
	}
	
	public void logout() {
		logout.click();
	}

}
