package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utilities.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By resultsProduct = By.cssSelector("div.product-layout.product-grid");

	public String getSearchResultsPageTitle(String searckKey) {
		return eleUtil.waitForTitleIsAndCapture(searckKey, AppConstants.SHORT_DEFAULT_WAIT);
	}

	public int getSearchResultsProductsCount() {
		int resultsCount = eleUtil.waitForElementsVisible(resultsProduct, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		return resultsCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By prodName= By.linkText(productName);
		eleUtil.doClick(prodName);
		return new ProductInfoPage(driver);
	}
}
