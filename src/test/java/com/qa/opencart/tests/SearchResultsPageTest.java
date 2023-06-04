package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opncart.dataproviders.ProductDataProvider;

public class SearchResultsPageTest extends BaseTest {
	
	@BeforeClass
	public void searchSetUp() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
	@Test(dataProvider="ProductSearchKey",dataProviderClass=ProductDataProvider.class)
public void searchProductTitleTest(String searchKey) {
	resultsPage=accountsPage.doSearchProduct(searchKey);
	String actResultsPageTitle=resultsPage.getSearchResultsPageTitle(searchKey);
	System.out.println("Actual Result Page title " +actResultsPageTitle);
	Assert.assertEquals(actResultsPageTitle,"Search - "+searchKey);
	}
	
	@Test(dataProvider="ProductSearchKey",dataProviderClass=ProductDataProvider.class)
	public void displayProductsCountTest(String searchKey) {
		resultsPage=accountsPage.doSearchProduct(searchKey);
	int actProductCount=resultsPage.getSearchResultsProductsCount();
	Assert.assertTrue((actProductCount)>0);
	}
	
	@Test(dataProvider="ProductNameData",dataProviderClass=ProductDataProvider.class)
	public void selectProductTest(String searchKey,String prodName) {
		resultsPage=accountsPage.doSearchProduct(searchKey);
		prodInfoPage=resultsPage.selectProduct(prodName);
		String actProdHeaderName=prodInfoPage.getProductHeaderName();
		Assert.assertEquals(actProdHeaderName, prodName);
	}
	
	@Test(dataProvider="ProductDataProviderWithImage",dataProviderClass=ProductDataProvider.class)
	public void selectProductImagesCountTest(String searchKey,String prodName,int imagesCount) {
		resultsPage=accountsPage.doSearchProduct(searchKey);
		prodInfoPage=resultsPage.selectProduct(prodName);
		int actImagesCount=prodInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
}
