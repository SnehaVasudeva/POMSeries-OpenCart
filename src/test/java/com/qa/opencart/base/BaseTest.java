package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchResultsPage resultsPage;
	protected ProductInfoPage prodInfoPage;
	protected DriverFactory df;
	protected Properties prop;
	protected RegistrationPage registerPage;

	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String browserName) {

		df = new DriverFactory();
				prop = df.initProp();
				if(prop!=null) {
					prop.setProperty("browser", browserName);
				}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
