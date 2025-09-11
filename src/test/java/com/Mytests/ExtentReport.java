package com.Mytests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static void main(String[] args) {
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("MySampleReport.html");
		//File that holds the report ( MySampleReport.html is the name provided )
		ExtentReports report = new ExtentReports();
		//Hold the test report and creates the actual report.
		report.attachReporter(htmlReport);
		//This will store the report in html file with method attachReporter
		ExtentTest test;
		//Represent the test case
		
		//Add environment details, anything can be set.
		report.setSystemInfo("Processor", "i5");
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("User Name", "Anjan");
		report.setSystemInfo("Bandwidth", "5G");
		
		//Set the UI of report
		htmlReport.config().setDocumentTitle("My First Report");
		htmlReport.config().setReportName("Google Test");
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setTimeStampFormat("dd-MMMM-yyy");
		
		//First test
		test = report.createTest("Google Title Test");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.google.co.in");
		System.out.println("Title: " + driver.getTitle());
		driver.close();
		
		test.log(Status.PASS, MarkupHelper.createLabel("Title Test: Pass", ExtentColor.GREEN));
		
		//Second test
		test = report.createTest("Facebook Test");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com");
		System.out.println("Title: " + driver.getTitle());
		driver.close();
		
		test.log(Status.FAIL, MarkupHelper.createLabel("Facebook test: Fail", ExtentColor.RED));
		
		report.flush();
		//Your report will get generated only after calling this method
	}

}