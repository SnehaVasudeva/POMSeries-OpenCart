package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetUp() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
		
	@Test
	public void getHeadersCountList() {
		Assert.assertEquals(accountsPage.getAccountPageHeadersList().size(),4);
	}
	
	@Test
	public void getAccountPageHeadersListTest() {
		List<String> accHeadersList=accountsPage.getAccountPageHeadersList();
		Collections.sort(accHeadersList);
		
		Collections.sort(AppConstants.EXP_HEADERS_LIST);
		Assert.assertEquals(accHeadersList, AppConstants.EXP_HEADERS_LIST);
	}
	
	@Test
	public void logoutLinkTest() {
		boolean logoutLinkTest=accountsPage.isLogoutLinkExist();
		Assert.assertTrue(logoutLinkTest);
	}
	
	@Test
	public void MyAccountLinkTest() {
		boolean myAccountLinkTest=accountsPage.isMyAccountLinkExist();
		Assert.assertTrue(myAccountLinkTest);
	}
	
	@Test
	public void accountTitleTest() {
		String accountpageTitle= accountsPage.getAccountPageTitle();
		
		Assert.assertEquals(accountpageTitle,AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accountUrlTest() {
		String accountpageurl= accountsPage.getAccountPageUrl();
		
		Assert.assertEquals(accountpageurl,AppConstants.ACCOUNT_PAGE_URL_FULL_VALUE);
	}
	
	
}
