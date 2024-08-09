package Practice.Testautomation;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.BeforeTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


public class Loginpage {
	ChromeDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;
	@BeforeClass
    public static void setUp() {
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Selenium\\eclipse_workspace\\Testautomation\\target\\HtmlReport\\Loginpage.html");
        extent.attachReporter(htmlReporter);
    }
	@Test(priority=1)
	public void verifyurl() {
		test = extent.createTest("Verifying The URL");
		try
		{
			String expected = "https://practicetestautomation.com/practice-test-lo";
			String actual=driver.getCurrentUrl();
			Assert.assertEquals(actual, expected);
			System.out.println("URL accessed successfully");
			test.pass("URL accessed successfully and the test is passed");
		}
		catch(AssertionError e)
		{
			System.out.println("DEFECT : Not correct URL "+e.getLocalizedMessage());
			test.fail("Not correct URL and the test is failed");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@Test(priority=2)
	public void validlogin()
	{
		test = extent.createTest("Verifying The login using valid credentials");
		driver.findElement(By.id("username")).sendKeys("student");
		driver.findElement(By.id("password")).sendKeys("Password123");
		driver.findElement(By.id("submit")).click();
		try
		{
			String expected="Logged In Successfully | Practice Test Automation";
			String actual=driver.getTitle();
			Assert.assertEquals(actual, expected);
			System.out.println("The user is able to login with valid username and password");
			test.pass("The user is able to login with valid username and password and the test is passed");
		}
		catch(AssertionError e)
		{
			System.out.println("DEFECT : The user is not able to login using valid username and password "+e.getLocalizedMessage() );
			test.fail("The user is not able to login using valid username and password and the test is failed");
		}
		finally
		{
			driver.findElement(By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div/div/div/a")).click();
		}
	}
	@Test(priority=3)
	public void invalidusername()
	{
		test = extent.createTest("Verifying The login using invalid username");
		driver.findElement(By.id("username")).sendKeys("student123");
		driver.findElement(By.id("password")).sendKeys("Password123");
		driver.findElement(By.id("submit")).click();
		try
		{
			String expected="Test Login | Practice Test Automation";
			String actual=driver.getTitle();
			Assert.assertEquals(actual, expected);
			System.out.println("The user is not able to login with invalid username");
			test.pass("The user is not able to login with invalid username and the test is passed");
			try
			{
				WebElement error=driver.findElement(By.id("error"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				error=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
				Assert.assertTrue(error.isDisplayed());
				System.out.println("The error message is displayed for the invalid username");
				test.pass("The error message is displayed for the invalid username and the test is passed");
				try
				{
					String expectederror="Your username is invalid!";
					String actualerror=error.getText();
					Assert.assertEquals(actualerror, expectederror);
					System.out.println("The correct error message is displayed for the invalid username");
					test.pass("The correct error message is displayed for the invalid username and the test is passed");
				}
				catch(AssertionError e)
				{
					System.out.println("DEFECT : Wrong error message for invalid username "+e.getLocalizedMessage());
					test.fail("Wrong error message for invalid username and the test is failed");
				}
			}
			catch(NoSuchElementException | TimeoutException f)
			{
				System.out.println("DEFECT : No error message displayed for invalid username "+f.getLocalizedMessage());
				test.fail("No error message displayed for invalid username and the test is failed");
			}
		}
		catch(AssertionError g)
		{
			System.out.println("DEFECT : The user is able to login using invalid username "+g.getLocalizedMessage() );
			test.fail("The user is able to login using invalid username and the test is failed");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@Test(priority=4)
	public void invalidpassword()
	{
		test = extent.createTest("Verifying The login using invalid password");
		driver.findElement(By.id("username")).sendKeys("student");
		driver.findElement(By.id("password")).sendKeys("Password");
		driver.findElement(By.id("submit")).click();
		try
		{
			String expected="Test Login | Practice Test Automation";
			String actual=driver.getTitle();
			Assert.assertEquals(actual, expected);
			System.out.println("The user is not able to login with invalid password");
			test.pass("The user is not able to login with invalid password and the test is passed");
			try
			{
				WebElement error=driver.findElement(By.id("error"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				error=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
				Assert.assertTrue(error.isDisplayed());
				System.out.println("The error message is displayed for the invalid password");
				test.pass("The error message is displayed for the invalid password and the test is passed");
				try
				{
					String expectederror="Your password is invalid!";
					String actualerror=error.getText();
					Assert.assertEquals(actualerror, expectederror);
					System.out.println("The correct error message is displayed for the invalid password");
					test.pass("The correct error message is displayed for the invalid password and the test is passed");
				}
				catch(AssertionError e)
				{
					System.out.println("DEFECT : Wrong error message for invalid password "+e.getLocalizedMessage());
					test.fail("Wrong error message for invalid password and the test is failed");
				}
			}
			catch(NoSuchElementException | TimeoutException f)
			{
				System.out.println("DEFECT : No error message displayed for invalid password "+f.getLocalizedMessage());
				test.fail("No error message displayed for invalid password and the test is failed");
			}
		}
		catch(AssertionError g)
		{
			System.out.println("DEFECT : The user is able to login using inavlid password "+g.getLocalizedMessage() );
			test.fail("The user is able to login using inavlid password and the test is failed");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@Test(priority=5)
	public void invalidusernameandpassword()
	{
		test = extent.createTest("Verifying The login using invalid username and password");
		driver.findElement(By.id("username")).sendKeys("student123");
		driver.findElement(By.id("password")).sendKeys("Password");
		driver.findElement(By.id("submit")).click();
		try
		{
			String expected="Test Login | Practice Test Automation";
			String actual=driver.getTitle();
			Assert.assertEquals(actual, expected);
			System.out.println("The user is not able to login with invalid username and password");
			test.pass("The user is not able to login with invalid username and password and the test is passed");
			try
			{
				WebElement error=driver.findElement(By.id("error"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				error=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
				Assert.assertTrue(error.isDisplayed());
				System.out.println("The error message is displayed for the invalid username and password");
				test.pass("The error message is displayed for the invalid username and password and the test is passed");
				try
				{
					String expectederror="Your username and password are invalid!";
					String actualerror=error.getText();
					Assert.assertEquals(actualerror, expectederror);
					System.out.println("The correct error message is displayed for the invalid username and password");
					test.pass("The correct error message is displayed for the invalid username and password and the test is passed");
				}
				catch(AssertionError e)
				{
					System.out.println("DEFECT : Wrong error message for invalid username and password "+e.getLocalizedMessage());
					test.fail("Wrong error message for invalid username and password and the test is failed");
					
				}
			}
			catch(NoSuchElementException | TimeoutException f)
			{
				System.out.println("DEFECT : No error message displayed for invalid username and password "+f.getLocalizedMessage());
				test.fail("No error message displayed for invalid username and password and the test is failed");
			}
		}
		catch(AssertionError g)
		{
			System.out.println("DEFECT : The user is able to login using inavlid username and password "+g.getLocalizedMessage() );
			test.fail("The user is able to login using inavlid username and password and the test is failed");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@BeforeTest
	public void beforeTest() {
		driver=new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() {
		driver.close();

	}
	@AfterClass
    public static void tearDown() {
        extent.flush();
    }

}
