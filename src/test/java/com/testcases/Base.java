package com.testcases;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.utilities.Readconfig;
import com.utilities.Xlutils;

public class Base {
	
	/*
	 * maintain all variables in config.properties file
	 * 
	 * String url = "https://demo.guru99.com/v4/"; String username = "mngr26593";
	 * String password = "Atharv@12";
	 */
	
	WebDriver dr;
	Logger logger;
	Readconfig rc = new Readconfig();
	
	
	
	@Parameters("browser")
	@BeforeClass
	public void set_browser(String browser) throws InterruptedException {
		
		 logger =Logger.getLogger("Bankingv1");
		DOMConfigurator.configure("log4j.xml");
		
		
		if(browser.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", rc.getchromepath());
			dr = new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", rc.getfirefoxpath());
			dr = new FirefoxDriver();
		}
		Thread.sleep(2000);
		dr.get(rc.geturl());
		
		
	}
	
	@AfterClass
	public void teardown() {
		dr.quit();
	}
	
	
	public void take_Screenshot(WebDriver dr, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)dr;
		System.out.println("inside screenshot of invalid credentials");
		File src = ts.getScreenshotAs(OutputType.FILE);
		System.out.println("middle screenshot of invalid credentials");
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(src,target);
		System.out.println("ending screenshot of invalid credentials");
		
	}

}
