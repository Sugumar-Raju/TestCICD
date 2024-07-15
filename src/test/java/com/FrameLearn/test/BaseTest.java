package com.FrameLearn.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.*;

import com.FrameLearn.pages.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage land;
	public WebDriver initialiseDriver() throws IOException
	{
		
		Properties prop =new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\config\\config.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initialiseDriver();
		land=new LandingPage(driver);
		land.gotTo();
		return land;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		
		driver.close();
		
	}
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException
	{
		//Json content to string
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//string to hashmap
		ObjectMapper mapper=new ObjectMapper();
		 List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		 return data;
		
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath=System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
		FileUtils.copyFile(src, new File(screenshotPath));
		
		return screenshotPath ;
		
		
	}
	
}
