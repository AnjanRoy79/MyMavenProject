package com.BrowserStack.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/BstackReport.html");
            spark.config().setReportName("BStackDemo Automation Report");
            spark.config().setDocumentTitle("Automation Test Results");
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            // Set system info
            extent.setSystemInfo("Project", "BStackDemo Automation");
            extent.setSystemInfo("Tester", "Anjan Roy");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}
