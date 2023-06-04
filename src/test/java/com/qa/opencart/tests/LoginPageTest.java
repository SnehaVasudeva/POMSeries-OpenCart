package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utilities.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginUrlTest() {
	String loginactUrl=loginPage.getLoginUrl()	;
		Assert.assertTrue(loginactUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void loginTitleTest() {
	String loginactTitle=loginPage.getLoginTitle()	;
		Assert.assertEquals(loginactTitle,"Account Login");
	}
	
	@Test
	public void loginLogoTest() {
	boolean logo=loginPage.getPageLogo()	;
		Assert.assertTrue(logo);
	}
	
	@Test
	public void newCustomerLabelExistTest() {
		String acttext=loginPage.newCustomerlabelPresent();
		Assert.assertEquals(acttext,"New Customer");
	}
	
	@Test
	public void returningCustomerLabelExistTest() {
		String returningCustActtext=loginPage.returningCustomerlabelPresent();
		Assert.assertEquals(returningCustActtext,"Returning Customer");
	}
	
	@Test
	public void zloginTest() {
	accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	Assert.assertEquals(accountsPage.getAccountPageTitle(),"My Account");
	Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	public void forgotPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.getForgotPasswordLink());
	}
	
	public void logoExistTest() {
		Assert.assertTrue(loginPage.getPageLogo());
	}
	@Test
	public void footerListTest() {
	List<String> actfooterList=loginPage.getFooterList();
	List<String> expFooterList=Arrays.asList("About Us","Delivery Information","Privacy Policy","Terms & Conditions","Contact Us","Returns","Site Map","Brands","Gift Certificates","Affiliate","Specials","My Account","Order History","Wish List","Newsletter");
	Assert.assertEquals(actfooterList,expFooterList);
	}
	
	
	
}
