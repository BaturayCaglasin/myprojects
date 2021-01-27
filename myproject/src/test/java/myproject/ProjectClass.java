package myproject;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProjectClass {

	private WebDriver driver;
	protected String generateString;
	protected String testURL = "https://twitter.com/?lang=en";
	String driverPathChrome = "src\\main\\resources\\chromedriver.exe";
	String driverPathFirefox = "src\\main\\resources\\geckodriver.exe";
	
	public static By mailButton = By.name("session[username_or_email]");
	public static By passwordButton = By.name("session[password]");
	public static By loginButton = By.cssSelector(".css-4rbku5:nth-child(2) > .css-901oao > .css-901oao > .css-901oao");
	public static By loginButton2 = By.xpath("//div[@id='react-root']/div/div/div[2]/main/div/div/div[2]/form/div/div[3]/div/div/span/span");
	public static By searchButton = By.cssSelector(".r-30o5oe");
	public static By searchButtonSub = By.cssSelector(".r-9qu9m4:nth-child(1)");
	public static By writeArea = By.cssSelector(".public-DraftStyleDefault-block");
	public static By tweetButton = By.cssSelector(".css-18t94o4:nth-child(4) > .css-901oao > .css-901oao > .css-901oao");
	public static By likeButton = By.cssSelector(".css-1dbjc4n:nth-child(3) > .css-18t94o4 .css-1dbjc4n > .r-4qtqp9");
	
	
	
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome") String browser) throws InterruptedException {

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverPathChrome);
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", driverPathFirefox);
			driver = new FirefoxDriver();
			break;

		default:
			System.setProperty("webdriver.chrome.driver", driverPathChrome);
			driver = new ChromeDriver();
			break;
		}

	
		driver.manage().window().maximize();
	}
	
	@Parameters({ "mail", "password" })
	@Test(priority = 1)

	public void searchTest(@Optional String mail, @Optional String password) throws InterruptedException {
	
		driver.get(testURL);
		System.out.println("Page is opened.");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		wait.until(ExpectedConditions.elementToBeClickable((loginButton)));
		driver.findElement(loginButton).click();
		
		wait.until(ExpectedConditions.elementToBeClickable((mailButton)));
		driver.findElement(mailButton).sendKeys(mail);
		
		wait.until(ExpectedConditions.elementToBeClickable((passwordButton)));
		driver.findElement(passwordButton).sendKeys(password);
		
		//for debugging:
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.elementToBeClickable((loginButton2)));
		driver.findElement(loginButton2).click();
		
		//for debugging:
		Thread.sleep(2000);
		
		
		wait.until(ExpectedConditions.elementToBeClickable((writeArea)));
		driver.findElement(writeArea).sendKeys("#vodafonetrtechteam");
		driver.findElement(By.xpath("//html")).click();
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable((tweetButton)));
		driver.findElement(tweetButton).click();
	
		
		
		//for debugging:
		Thread.sleep(2000);
		
		
		wait.until(ExpectedConditions.elementToBeClickable((searchButton)));
		driver.findElement(searchButton).sendKeys("#vodafonetrtechteam");
		driver.findElement(searchButton).sendKeys(Keys.ENTER);
		
		//for debugging:
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.elementToBeClickable((likeButton)));
		driver.findElement(likeButton).click();
	
		//for debugging:
		Thread.sleep(1000);
		

	}

	@AfterMethod

	public void closeTest() throws InterruptedException {

		System.out.println("Your Test Has Been Ended Successfully.Your Test is going to Close");
		driver.quit();

	}
	
}
