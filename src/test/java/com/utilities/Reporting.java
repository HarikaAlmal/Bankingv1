package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporting extends TestListenerAdapter {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public void onStart(ITestContext itc) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Test-report" + timestamp + ".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml/");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", "Banking project");
		extent.setSystemInfo("Tester", "Harika");
		extent.setSystemInfo("OS", "Win11");
		extent.setSystemInfo("Browser", "Chrome");

	}

	public void onTestSuccess(ITestResult itr) {

		test = extent.createTest(itr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Passed: " + itr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult itr) {

		test = extent.createTest(itr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Failed: " + itr.getName(), ExtentColor.RED));

		String screenshotpath = System.getProperty("user.dir") + "\\Screenshots\\" + itr.getName() + ".png";
		File f = new File(screenshotpath);

		if (f.exists()) {
			try {
				test.fail("screenshot: " + test.addScreenCaptureFromPath(screenshotpath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void onTestSkipped(ITestResult itr) {

		test = extent.createTest(itr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Passed: " + itr.getName(), ExtentColor.ORANGE));
	}

	
	  public void onFinish(ITestContext itc) 
	  { 
		  extent.flush(); 
	  }
	  
	 

}
