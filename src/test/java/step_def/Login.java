package step_def;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	public ChromeDriver driver;

	@Given("open the chrome browser and maximize the window")
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
}

	@Given("load the service now application")
	public void loadAppUrl() {
		driver.get("https://dev68594.service-now.com/");

	}

	@Given("enter username as {string}")
	public void enter_username_as_admin(String username) {
		driver.switchTo().frame("gsft_main");
		driver.findElementById("user_name").sendKeys(username);
	}

	@Given("enter password as {string}")
	public void enter_password_as_india(String password) {
		driver.findElementById("user_password").sendKeys(password);
	}

	@When("click login button")
	public void click_login_button() {
		driver.findElementByName("not_important").click();
	}

	/*@Then("homepage should be displayed")
	public void homepage_should_be_displayed() {
		System.out.println("Home page should be displayed");
	}*/
	
	@But("error should be displayed")
	public void verifyError() {
		System.out.println("error is displayed");

	}
	@Then ("Enter {string} in the filter navigator")
	public void filter_navigation(String incident) {
		driver.findElementById("filter").sendKeys(incident);
	}
	@Then ("Click All under Incident")
	public void all_incidents() throws InterruptedException {
			Thread.sleep(5000);
			driver.findElementByXPath("(//div[text()='All'])[2]").click();
	}
	
	@When("Click New button")
	public void click_new_button() {
		driver.switchTo().frame("gsft_main");

		driver.findElementByXPath("//button[text()='New']").click();
	}
	
	@Then("Read the incident number from Number and store it in variable for verification")
	public String store_incident_number() {
			return (driver.findElementById("incident.number").getAttribute("value"));
	}
	
	@When("Click on look up icon for Caller and Search {string} in the lookup window")
	public void click_on_caller(String abel) {
		driver.findElementByXPath("//span[text()='Caller']/following::span[@class='icon icon-search'][1]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(windowHandles);
		
		String secondWin = listHandles.get(1);
		String firstWin = listHandles.get(0);
		driver.switchTo().window(secondWin);
		driver.findElementByXPath("//input[@placeholder='Search']").sendKeys(abel, Keys.ENTER);
		driver.findElementByLinkText("Abel Tuter").click();
		driver.switchTo().window(firstWin);
	}
	
	@Then("Enter the Short_description in previous window")
	public void short_description() {
		driver.switchTo().frame("gsft_main");
		driver.findElementById("incident.short_description").sendKeys("Satyam Testing Automation 2");
	}
	
	@When("Click on Submit")
	public void click_submit() {
		driver.findElementById("sysverb_insert_bottom").click();
	}
	
	@Then("use that saved incident number in search box in ALL incident and search")
	public void search_our_incident(){
		String incidentNumber = store_incident_number();
		WebElement searchBy = driver.findElementByXPath("//select[contains(@id,'select')]");

		Select dd = new Select(searchBy);

		dd.selectByVisibleText("Number");

		driver.findElementByXPath("//input[@placeholder='Search']").sendKeys(incidentNumber, Keys.ENTER);
	}
	
	
}
