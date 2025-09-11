package com.OrangeHRM;

import java.io.*;
import java.time.Duration;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Assert;

public class LoginTest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("OrangeHRMReport.html");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setReportName("Orange HRM Report");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void setup() {
    	//Below options used to avoid any notification/ pop from browser
    	System.setProperty("webdriver.edge.driver", "D:\\Anjan-SeleniumDemos\\MyMavenProject\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        return getLoginData("D:\\Testing\\Selenium\\LoginDataSet.xlsx", "LoginData");
    }

    @Test(dataProvider = "LoginData")
    public void testLogin(String username, String password) throws IOException, InterruptedException {
        test = extent.createTest("Login Test - " + username);

        OrangeHRMPage orangeHRM = new OrangeHRMPage(driver);
        orangeHRM.login(username, password);
        Thread.sleep(2000);

        captureScreenshot(username); // Screenshot after login
        
        boolean isLoggedIn = driver.findElements(By.xpath("//h6[text()='Dashboard']")).size() > 0;

        try {
            if (username.equals("Admin") && password.equals("admin123")) {
                Assert.assertTrue(isLoggedIn, "Admin login failed!");
                test.pass("Valid credentials - Login successful");
                
                
             // Navigate to User management and clicking on users section
                orangeHRM.goToUsers();
                test.info("Navigated to Admin > User Management > Users");
                Thread.sleep(1000);
                
             // Take screenshot after navigation to Users page
                captureScreenshot(username + "_UsersPage"); 
                test.info("Screenshot captured after navigating to admin/usermanagement/users");
                
             // Logout
                orangeHRM.logout();
                test.info("Logged out successfully");
            } else {
                Assert.assertFalse(isLoggedIn, "Invalid login");
                test.pass("Invalid credentials - Login failed as expected");
                test.info("Screenshot captured after user credentials provided");
            }
        } catch (AssertionError e) {
            test.fail("Test assertion failed: " + e.getMessage());
        }
    }

    public void captureScreenshot(String filename) throws IOException, InterruptedException {
    	Thread.sleep(2000); // To allow page to load completely
        File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(ss, new File("screenshots/" + filename + "_" + System.currentTimeMillis() + ".png"));
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @AfterTest
    public void flushReport() {
        extent.flush();
    }

   // Reading data from Excel
    public static Object[][] getLoginData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rows - 1][cols];
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();
        return data;
    }
}
