package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Readconfig {
	
	Properties pro;
	
	public Readconfig()  {
		File f = new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(f);
			pro = new Properties();
			pro.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String geturl() {
		return pro.getProperty("url"); 
	}
	
	public String getusername() {
		return pro.getProperty("username"); 
	}
	
	public String getpassword() {
		return pro.getProperty("password"); 
	}
	
	public String getchromepath() {
		return pro.getProperty("chrome_driver");
	} 
	
	public String getfirefoxpath() {
		return pro.getProperty("firefox_driver");
	} 
}
