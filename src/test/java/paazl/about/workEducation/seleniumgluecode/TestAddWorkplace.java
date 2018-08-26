package paazl.about.workEducation.seleniumgluecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestAddWorkplace {

	static WebDriver driver;
	public static Properties prop = new Properties();

	@Given("^user logged in with a Facebook account$")
	public void user_logged_in_with_a_facebook_account() throws Throwable {
		File resourcesDirectory = new File("src/test/resources");

		// Load the test parameters
		InputStream input = null;
		input = new FileInputStream(resourcesDirectory.getAbsolutePath() + "/test-parameters.properties");
		prop.load(input);

		// Initialize the web driver and disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver",
				resourcesDirectory.getAbsolutePath() + "/web_drivers/chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		// Sign in Facebook
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("selenium.facebook.url"));
		driver.findElement(By.id("email")).sendKeys(prop.getProperty("selenium.facebook.email"));
		driver.findElement(By.id("pass")).sendKeys(prop.getProperty("selenium.facebook.password"));
		WebElement clickLogin = driver.findElement(By.id("loginbutton"));
		clickLogin.click();
	}

	@Given("^user goes to his profile$")
	public void user_goes_to_his_profile() throws Throwable {
		WebElement clickProfile = driver.findElement(By.xpath("//span[@class='_1qv9']"));
		clickProfile.click();

	}

	@When("^user clicks in the About section$")
	public void user_clicks_in_the_About_section() throws Throwable {
		WebElement clickAbout = driver.findElement(By.cssSelector("a[class='_6-6']"));
		clickAbout.click();

	}

	@When("^user selects work and education$")
	public void user_selects_work_and_education() throws Throwable {
		WebElement clickWorkEducation = driver.findElement(By.cssSelector("li[testid=\"nav_edu_work\"]"));
		clickWorkEducation.click();
	}

	@When("^user clicks on the add icon$")
	public void user_clicks_on_the_add_icon() throws Throwable {
		WebElement clickWork = driver.findElement(By.linkText("Add a workplace"));
		clickWork.click();
	}

	@When("^user adds information for workplace$")
	public void user_adds_information_for_workplace() throws Throwable {
		WebElement company = driver.findElement(By.xpath("//input[@placeholder='Where have you worked?']"));
		company.sendKeys("Paazl");
		// To choose the first value in the combobox
		company.sendKeys(Keys.DOWN, Keys.RETURN);
		WebElement position = driver.findElement(By.xpath("//input[@placeholder='What is your job title?']"));
		position.sendKeys("Software QA tester");
		position.sendKeys(Keys.DOWN, Keys.RETURN);
		// I couldn't use the @id to find the element because it have a new value every
		// time,
		// so I have used Keys.TAB two times
		position.sendKeys(Keys.TAB, Keys.TAB, "Amsterdam, Netherlands", Keys.DOWN, Keys.RETURN);
		driver.findElement(By.name("description")).sendKeys("I am a software QA engineer!");
	}

	@When("^user clicks the save changes button$")
	public void user_clicks_the_save_changes_button() throws Throwable {
		driver.findElement(By.xpath("//button[@class='_42ft _4jy0 _4jy4 _4jy1 selected _51sy']")).click();
		// Wait 5s for saving the new workplace
		Thread.sleep(5000);
	}

	@Then("^new workplace added with success$")
	public void new_workplace_added_with_success() throws Throwable {
		// Check if the workPlace was well added
		WebElement newWorkplace = driver.findElements(By.xpath("//div[contains(@class, '_2lzr _50f5 _50f7')]/a"))
				.get(0);
		String actualText = newWorkplace.getText();
		String expectedText = new String("Paazl");
		Assert.assertEquals(expectedText, actualText);
		driver.close();
	}
}
