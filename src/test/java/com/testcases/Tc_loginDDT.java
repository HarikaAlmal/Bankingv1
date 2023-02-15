package com.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.LoginPage;
import com.utilities.Xlutils;

public class Tc_loginDDT extends Base{
	
	/* DataDriven Testcase using excel 
	 * file data through Xlutils utility class
	 */
	
	LoginPage lp;
	
	@Test(dataProvider="logindetails")
	public void tc_login(String username,String password) throws InterruptedException, IOException { 
		
		Thread.sleep(2000);
		logger.info("url opened");
		dr.manage().window().maximize();
		Thread.sleep(2000);
		
		lp = new LoginPage(dr);
		
		lp.enter_username(username);
		logger.info("username entered");
		lp.enter_password(password);
		logger.info("password entered");
		lp.click_login();
		logger.info("clicked on login");
		if(isalertpresent()==true) {
			Thread.sleep(2000);
			System.out.println("before screenshot of invalid credentials");
			take_Screenshot(dr,"loginfailddt");
			System.out.println("after screenshot of invalid credentials");
			Thread.sleep(2000);
			dr.switchTo().alert().accept();
			dr.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else {
			take_Screenshot(dr,"loginfailddt");
			Assert.assertTrue(true);
			lp.logout();
			dr.switchTo().alert().accept();//closes logout alert
			dr.switchTo().defaultContent();
		}
		/*
		 * String actualTitle = dr.getTitle(); String expectedTitle =
		 * "Guru99 Bank Manager HomePage";
		 * if(actualTitle.equalsIgnoreCase(expectedTitle)) { Assert.assertTrue(true); }
		 * else { take_Screenshot(dr,"tc_login"); Assert.assertTrue(false); }
		 * Assert.assertEquals(actualTitle, expectedTitle);
		 */
	}
	
	
	@DataProvider(name="logindetails")
	public String[][] getData() throws IOException {
		String path = System.getProperty("user.dir")+"/src/test/java/com/testdata/testdata.xlsx";
		int rowcount = Xlutils.getRowCount(path, "Sheet1");
		int cellcount = Xlutils.getCellCount(path, "Sheet1", 1);
		String[][] logindata = new String[rowcount][cellcount];
		
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<cellcount;j++) {
				logindata[i-1][j] = Xlutils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	}
	
	
	public boolean isalertpresent() {
		try {
			dr.switchTo().alert();//wrong crdentials alert
			return true;
		}
		catch(Exception e) {
			return false; 
		}
		
		
	}
	

}












