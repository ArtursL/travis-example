package gradle.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.After;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class BasicStepdefs {
    private final WebDriver driver = new FirefoxDriver();

    @Given("^I am on the Google search page$")
    public void I_visit_google() {
        driver.get("https://www.google.com");
    }


    @When("^I search for \"(.*)\"$")
    public void search_for(String query) {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(query);
        element.submit();
    }

    @Then("^the page title should start with \"(.*)\"$")
    public void checkTitle(String title) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(title);
            }
        });
        assertThat(driver.getTitle(), startsWith(title));
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
