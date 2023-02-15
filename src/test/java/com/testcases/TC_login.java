package com.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobjects.LoginPage;

public class TC_login extends Base{
	
	LoginPage lp;
	
	@Test
	public void tc_login() throws InterruptedException, IOException { 
		
		
		logger.info("url opened");
		dr.manage().window().maximize();
		Thread.sleep(2000);
		
		lp = new LoginPage(dr);
		
		lp.enter_username(rc.getusername());
		logger.info("username entered");
		lp.enter_password(rc.getpassword());
		logger.info("password entered");
		lp.click_login();
		logger.info("clicked on login");
		String actualTitle = dr.getTitle();
		String expectedTitle = "Guru99 Bank Manager HomePage";
		if(actualTitle.equalsIgnoreCase(expectedTitle)) {
			Assert.assertTrue(true);
		}
		else {
			take_Screenshot(dr,"tc_login");
			Assert.assertTrue(false);
		}
		Assert.assertEquals(actualTitle, expectedTitle);
	}

}
