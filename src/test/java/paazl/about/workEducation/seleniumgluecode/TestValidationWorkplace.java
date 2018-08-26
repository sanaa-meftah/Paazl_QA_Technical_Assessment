package paazl.about.workEducation.seleniumgluecode;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;

public class TestValidationWorkplace {

	@Then("^an error massage displayed$")
	public void an_error_massage_displayed() throws Throwable {
		// Validation of the mandatory field (Employer)
		WebElement newWorkplace = TestAddWorkplace.driver.findElement(By.xpath("//div[contains(@class, '_4-i0')]"));
		String actualText = newWorkplace.getText();
		String expectedText = new String("Invalid Employer");
		Assert.assertEquals(expectedText, actualText);
		TestAddWorkplace.driver.close();
	}
}
