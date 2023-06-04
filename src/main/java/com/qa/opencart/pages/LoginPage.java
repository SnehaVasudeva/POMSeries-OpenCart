package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
		
	}
	
	private By emailId= By.id("input-email");
	private By pwd= By.id("input-password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By footerLinks= By.xpath("//footer//li/a");
	private By forgotPwdLink=By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By logoDisplay= By.xpath("//div[@id='logo']");
	private By returningCustomerLabel= By.xpath("//div[@class='well']//h2[text()='Returning Customer']");
	private By newCustomerLabel= By.xpath("//h2[text()='New Customer']");
	private By loginErrorMessage=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	private By register= By.linkText("Register");
	public String getLoginTitle() {
		return eleUtil.waitForTitleIsAndCapture("Account Login", AppConstants.SHORT_DEFAULT_WAIT);
		
	}
	
	public String getLoginUrl() {
		return eleUtil.waitForURLContainsAndCapture("route=account/login", AppConstants.SHORT_DEFAULT_WAIT);
		
	}
	
	public boolean getForgotPasswordLink() {
		return eleUtil.checkElementIsDisplayed(forgotPwdLink);
	}
	
	public boolean getPageLogo() {
		boolean logo=eleUtil.IsElementDisplayed(logoDisplay);
		return logo;
	}
	
	public String returningCustomerlabelPresent() {
		String labelText=eleUtil.doGetElementText(returningCustomerLabel);
		return labelText;
	}
	
	public String newCustomerlabelPresent() {
		String newCustomerlabelText=eleUtil.doGetElementText(newCustomerLabel);
		return newCustomerlabelText;
	}
	
	public AccountsPage doLogin(String email, String password) {
		System.out.println("Entering email and password " +email +password);
	eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(email);
	eleUtil.doSendKeys(pwd, password);
	eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public boolean doIncorrectLoginCredentials(String email, String password) {
		System.out.println("Entering incorrect email and password " +email +password);
	eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_DEFAULT_WAIT);
	eleUtil.doSendKeys(emailId, email);
	eleUtil.doSendKeys(pwd, password);
	eleUtil.doClick(loginBtn);
	if(eleUtil.doGetElementText(loginErrorMessage).contains("No match for E-Mail Address and/or Password."))
		{
			return true;
		}
	
	return false;
	}
	
	
	public List<String> getFooterList() {
		List<WebElement> footerList=eleUtil.waitForElementsVisible(footerLinks, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> footerText= new ArrayList<String>();
		for(WebElement e:footerList) {
			String text=e.getText();
			footerText.add(text);
		}
		return footerText;
	}
	
	
	
	public RegistrationPage navigateToRegistrationPage() {
		eleUtil.doClick(register);
		return new RegistrationPage(driver);
	}
}
