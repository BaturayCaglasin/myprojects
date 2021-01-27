package myproject;

import java.io.File;
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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class ProjectClass {

	private WebDriver driver;
	protected String generateString;
	protected String testURL = "https://twitter.com/?lang=en";
	String driverPathChrome = "src\\main\\resources\\chromedriver.exe";
	String driverPathFirefox = "src\\main\\resources\\geckodriver.exe";
	public ExtentReports report;
	public ExtentTest logger;
	
	
	public static By mailButton = By.name("session[username_or_email]");
	public static By passwordButton = By.name("session[password]");
	public static By loginButton = By.cssSelector(".css-4rbku5:nth-child(2) > .css-901oao > .css-901oao > .css-901oao");
	public static By loginButton2 = By.xpath("//div[@id='react-root']/div/div/div[2]/main/div/div/div[2]/form/div/div[3]/div/div/span/span");
	public static By searchButton = By.cssSelector(".r-30o5oe");
	public static By searchButtonSub = By.cssSelector(".r-9qu9m4:nth-child(1)");
	public static By writeArea = By.cssSelector(".public-DraftStyleDefault-block");
	public static By tweetButton = By.cssSelector(".css-18t94o4:nth-child(4) > .css-901oao > .css-901oao > .css-901oao");
	public static By likeButton = By.cssSelector(".css-1dbjc4n:nth-child(3) > .css-18t94o4 .css-1dbjc4n > .r-4qtqp9");
	public static By menuButton = By.cssSelector("section [class] > div:nth-of-type(1) > div:nth-of-type(1) [class] [class] [class] [class] [class] [class] [class='css-1dbjc4n']:nth-of-type(1) [class='r-4qtqp9 r-yyyyoo r-1xvli5t r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1hdv0qi']");
	public static By mainDeleteButton = By.cssSelector("[class='css-901oao r-daml9f r-1qd0xha r-a023e6 r-16dba41 r-ad9z0x r-bcqeeo r-qvutc0'] [class]");
	public static By confirmDeleteButton = By.cssSelector("div:nth-of-type(2) > .css-901oao.r-16y2uox.r-1777fci.r-18u37iz.r-1awozwy.r-1qd0xha.r-6koalj.r-a023e6.r-ad9z0x.r-b88u0q.r-bcqeeo.r-dnmrzs.r-jwli3a.r-q4m81j.r-qvutc0");
	
	@BeforeSuite

	public void setUpSuite() {
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "./test-output/twittertest.html"));
		report = new ExtentReports();
		report.attachReporter(extent);

	}
	
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
		;
		
		
		
		
	}
	
	@Parameters({ "mail", "password" })
	@Test(priority = 1)

	public void searchTest(@Optional String mail, @Optional String password) throws InterruptedException {
	
	
		logger = report.createTest("searchTest");
		logger.info("This test considers tweet a hashtag, search, like and delete it.");
		logger.pass("Test is successful.");
		
		
		driver.get(testURL);
		System.out.println("Page is opened. The test has been initiated.");
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		//login steps
		
		wait.until(ExpectedConditions.elementToBeClickable((loginButton)));
		driver.findElement(loginButton).click();
		
		wait.until(ExpectedConditions.elementToBeClickable((mailButton)));
		driver.findElement(mailButton).sendKeys(mail);
		
		wait.until(ExpectedConditions.elementToBeClickable((passwordButton)));
		driver.findElement(passwordButton).sendKeys(password);
		
		//for debugging, to see the steps. Can be deleted.
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.elementToBeClickable((loginButton2)));
		driver.findElement(loginButton2).click();
		
		//for debugging, to see the steps. Can be deleted.
		Thread.sleep(2000);
		
		//tweet steps
		
		
		wait.until(ExpectedConditions.elementToBeClickable((writeArea)));
		driver.findElement(writeArea).sendKeys("#vodafonetrtechteam");
		driver.findElement(By.xpath("//html")).click();
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable((tweetButton)));
		driver.findElement(tweetButton).click();
		
		//for debugging, to see the steps. Can be deleted.
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-901oao.r-16dba41.r-1qd0xha.r-1wbh5a2.r-a023e6.r-ad9z0x.r-bcqeeo.r-jwli3a.r-qvutc0.r-utggzx > .css-16my406.css-901oao.r-bcqeeo.r-poiln3.r-qvutc0")));
		Assert.assertEquals("Your Tweet was sent.",
				driver.findElement(By.cssSelector(".css-901oao.r-16dba41.r-1qd0xha.r-1wbh5a2.r-a023e6.r-ad9z0x.r-bcqeeo.r-jwli3a.r-qvutc0.r-utggzx > .css-16my406.css-901oao.r-bcqeeo.r-poiln3.r-qvutc0")).getText());
		
		System.out.println(driver.findElement(By.cssSelector(".css-901oao.r-16dba41.r-1qd0xha.r-1wbh5a2.r-a023e6.r-ad9z0x.r-bcqeeo.r-jwli3a.r-qvutc0.r-utggzx > .css-16my406.css-901oao.r-bcqeeo.r-poiln3.r-qvutc0")).getText());
		
		
		//for debugging, to see the steps. Can be deleted.
		Thread.sleep(1000);
				
		//search steps
		
		wait.until(ExpectedConditions.elementToBeClickable((searchButton)));
		driver.findElement(searchButton).sendKeys("#vodafonetrtechteam");
		driver.findElement(searchButton).sendKeys(Keys.ENTER);
		
		//for debugging, to see the steps. Can be deleted.
		Thread.sleep(1000);
		
		
		//like steps
		
		wait.until(ExpectedConditions.elementToBeClickable((likeButton)));
		driver.findElement(likeButton).click();
	
		//for debugging, to see the steps. Can be deleted.
		Thread.sleep(1000);
	
		
		//delete steps (extra)
		
		
		wait.until(ExpectedConditions.elementToBeClickable((menuButton)));
		driver.findElement(menuButton).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((mainDeleteButton)));
		driver.findElement(mainDeleteButton).click();
		
		wait.until(ExpectedConditions.elementToBeClickable((confirmDeleteButton)));
		driver.findElement(confirmDeleteButton).click();
	
		//for debugging, to see the steps. Can be deleted.
		Thread.sleep(3000);

	}

	@AfterMethod

	public void closeTest() throws InterruptedException {

		System.out.println("Your test has been ended successfully. Your Test is going to close.");
	
		driver.quit();
		report.flush();

	}
	
}
