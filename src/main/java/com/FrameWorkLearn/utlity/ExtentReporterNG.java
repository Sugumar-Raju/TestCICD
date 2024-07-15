package com.FrameWorkLearn.utlity;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
	String reportPath= System.getProperty("user.dir")+"//resources//index.html";
	ExtentSparkReporter reporter= new ExtentSparkReporter(reportPath);
	reporter.config().setDocumentTitle("automationTEsting");
	ExtentReports extent= new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Sugumar");
	return extent;
	}
	
	
	
	
	

}
