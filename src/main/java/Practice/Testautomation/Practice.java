package Practice.Testautomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.StaleElementReferenceException;

public class Practice {
	ChromeDriver driver;

	@Test(priority=1)
	public void NoSuchElementException() {

		driver.findElement(By.id("add_btn")).click();
		try
		{
			WebElement row2=driver.findElement(By.xpath("//*[@id=\"row2\"]/input"));
			Assert.assertTrue(row2.isDisplayed());
		}
		catch(NoSuchElementException | TimeoutException | AssertionError e)
		{
			System.out.println("Defect : The Row2 textfield is not present");
		}
		finally
		{
			driver.navigate().refresh();
		}

	}
	@Test(priority=2)
	public void ElementNotInteractableException() {

		driver.findElement(By.id("add_btn")).click();
		try
		{
			WebElement row2=driver.findElement(By.xpath("//*[@id=\"row2\"]/input"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			row2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"row2\"]/input")));
			row2.sendKeys("Burger");
			driver.findElement(By.name("Save")).click();

			WebElement message=driver.findElement(By.id("confirmation"));
			message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
			Assert.assertTrue(message.isDisplayed());
		}
		catch(ElementNotInteractableException | TimeoutException | AssertionError | NoSuchElementException e)
		{
			System.out.println("Defect : The message showing the 'Row 2 was saved' is not displayed");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@Test(priority=3)
	public void InvalidElementStateException() {
		try
		{
			WebElement row2=driver.findElement(By.xpath("//*[@id=\"row2\"]/input"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			row2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"row2\"]/input")));
			row2.clear();
			String expected="Bowl";
			row2.sendKeys(expected);

			WebElement message=driver.findElement(By.id("confirmation"));
			message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
			String row1text=message.getAttribute("value");
			Assert.assertTrue(!row1text.isEmpty()&row1text.contentEquals(expected));
		}
		catch(InvalidElementStateException | TimeoutException | AssertionError | NoSuchElementException e)
		{
			System.out.println("Defect : The text to the row2 is not changed");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@Test(priority=4)
	public void StaleElementReferenceException() {

		try
		{
			WebElement instruction=driver.findElement(By.id("instructions"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			instruction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("instructions")));
			driver.findElement(By.id("add_btn")).click();
			Assert.assertTrue(instruction.isDisplayed());

		}
		catch(StaleElementReferenceException | TimeoutException | AssertionError | NoSuchElementException e)
		{
			System.out.println("Defect : The instruction text element is not displayed");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@Test(priority=5)
	public void TimeoutException() {

		try
		{
			driver.findElement(By.id("add_btn")).click();
			WebElement row2=driver.findElement(By.xpath("//*[@id=\"row2\"]/input"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			row2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"row2\"]/input")));
			Assert.assertTrue(row2.isDisplayed());

		}
		catch(InvalidElementStateException | TimeoutException | AssertionError | NoSuchElementException e)
		{
			System.out.println("Defect : The Row2 textfield is not present");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}

	@BeforeTest
	public void beforeTest() {
		driver=new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
