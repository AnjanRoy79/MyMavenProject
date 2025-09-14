package com.BrowserStack.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.BrowserStack.test.BaseTest;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Get ExtentTest instance for current thread
    public static ExtentTest getTestLogger() {
        return test.get();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        extentTest.log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = getTestLogger();
        extentTest.log(Status.PASS, "Test Passed");

        WebDriver driver = BaseTest.driver;
        if (driver != null) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver,
                    result.getMethod().getMethodName() + "_success");
            try {
                extentTest.addScreenCaptureFromPath(screenshotPath, "Success Screenshot");
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = getTestLogger();
        extentTest.log(Status.FAIL, result.getThrowable());

        WebDriver driver = BaseTest.driver;
        if (driver != null) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver,
                    result.getMethod().getMethodName() + "_failure");
            try {
                extentTest.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = getTestLogger();
        extentTest.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }


}
