package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	
	private By logoutLink= By.linkText("Logout");
	private By MyAccountLink= By.xpath("//div[@class='list-group' ]//a[text()='My Account']");
	private By headers= By.xpath("//div[@id='content']//h2");
	private By searchProduct= By.name("search");
	private By searchButton= By.xpath("//div[@id='search']//button");
	
	
	public boolean isLogoutLinkExist() {
		boolean logout=eleUtil.checkElementIsDisplayed(logoutLink);
		return logout;
	}
	
	public boolean isMyAccountLinkExist() {
		boolean MyAccount=eleUtil.checkElementIsDisplayed(MyAccountLink);
		return MyAccount;	
	}
	
	public String getAccountPageTitle() {
		return eleUtil.waitForTitleIsAndCapture("My Account", AppConstants.SHORT_DEFAULT_WAIT);
		
	}
	
	public String getAccountPageUrl() {
		return eleUtil.waitForURLContainsAndCapture("route=account/account", AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public List<String> getAccountPageHeadersList() {
		List<WebElement> headersList= eleUtil.waitForElementsVisible(headers, AppConstants.SHORT_DEFAULT_WAIT);
		List<String> accountHeadersList= new ArrayList<String>();
		for(WebElement e:headersList) {
			String text=e.getText();
			accountHeadersList.add(text);
		}
		return accountHeadersList;
	}
	
	public SearchResultsPage doSearchProduct(String prodName) {
		eleUtil.waitForElementVisible(searchProduct, AppConstants.SHORT_DEFAULT_WAIT);
		eleUtil.doSendKeys(searchProduct, prodName);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}

}
