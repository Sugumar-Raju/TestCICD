package com.FrameLearn.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.FrameWorkLearn.utlity.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener  {
	
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal();
	
	
	public void onTestStart(ITestResult result) {
		
	test=extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);
	
	    
	  }
	public void onTestSuccess(ITestResult result) {
	   
		test.log(Status.PASS, "TestcasePassed");
		extent.flush();
	  }
	
	 public void onTestFailure(ITestResult result) {
		 
		 extentTest.get().fail(result.getThrowable());
		 String filePath=null;
		 
		 try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		 
		 try {
			filePath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		    
		  }
	public void onFinish(ITestContext context) {
		    extent.flush();
		  }

	

}
