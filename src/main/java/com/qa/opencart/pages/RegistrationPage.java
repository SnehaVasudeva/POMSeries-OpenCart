package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utilities.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By firstName = By.id("input-firstname");

	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input");
	private By agreeCheckbox = By.name("agree");
	private By continueBtn = By.xpath("//input[@value='Continue']");
	private By successMesg = By.cssSelector("div#content h1");
	private By logOut= By.linkText("Logout");
	private By register= By.linkText("Register");
	public String registerPageData(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(confirmPwd, password);
		doSubscribe(subscribe);
		eleUtil.doClick(agreeCheckbox);
		eleUtil.doClick(continueBtn);
		String regSuccessMessg= eleUtil.waitForElementVisible(successMesg, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		System.out.println(regSuccessMessg);
		eleUtil.doClick(logOut);
		eleUtil.doClick(register);
		return regSuccessMessg;
	}

	private void doSubscribe(String subscribe) {
		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else
			eleUtil.doClick(subscribeNo);

	}
	


}
