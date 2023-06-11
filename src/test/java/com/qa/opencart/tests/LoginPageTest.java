package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Login Page Design")
@Story("US 101: design login page for open cart app with title, url, forgot pwd links, user is able to login")

public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.NORMAL)
	@Description("checking login page url test...")
	@Feature("url test")


	@Test
	public void loginUrlTest() {
		String loginactUrl = loginPage.getLoginUrl();
		Assert.assertTrue(loginactUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Severity(SeverityLevel.MINOR)
	@Description("checking login page title test...")
	@Feature("title test")

	@Test
	public void loginTitleTest() {
		String loginactTitle = loginPage.getLoginTitle();
		Assert.assertEquals(loginactTitle, "Account Login");
	}
	@Severity(SeverityLevel.NORMAL)
	@Description("checking login page logo test...")
	@Feature("title test")
	@Test
	public void loginLogoTest() {
		boolean logo = loginPage.getPageLogo();
		Assert.assertTrue(logo);
	}

	@Test
	public void newCustomerLabelExistTest() {
		String acttext = loginPage.newCustomerlabelPresent();
		Assert.assertEquals(acttext, "New Customer");
	}

	@Test
	public void returningCustomerLabelExistTest() {
		String returningCustActtext = loginPage.returningCustomerlabelPresent();
		Assert.assertEquals(returningCustActtext, "Returning Customer");
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("checking user is able to login with correct username/password test...")
	@Feature("login test")

	@Test
	public void zloginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountPageTitle(), "My Account");
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("checking forgot pwd link test...")
	@Feature("forgot pwd test")
	@Test
	public void forgotPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.getForgotPasswordLink());
	}

	public void logoExistTest() {
		Assert.assertTrue(loginPage.getPageLogo());
	}

	@Test
	public void footerListTest() {
		List<String> actfooterList = loginPage.getFooterList();
		List<String> expFooterList = Arrays.asList("About Us", "Delivery Information", "Privacy Policy",
				"Terms & Conditions", "Contact Us", "Returns", "Site Map", "Brands", "Gift Certificates", "Affiliate",
				"Specials", "My Account", "Order History", "Wish List", "Newsletter");
		Assert.assertEquals(actfooterList, expFooterList);
	}

}
