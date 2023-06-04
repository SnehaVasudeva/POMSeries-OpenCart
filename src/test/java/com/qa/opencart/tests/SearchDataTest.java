package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pojo.Product;
import com.qa.opncart.dataproviders.ProductDataProvider;

public class SearchDataTest extends BaseTest {
	@BeforeClass
	public void searchSetUp() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	

	
	@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
public void searchProductTitleTest(Product product) {
	resultsPage=accountsPage.doSearchProduct(product.getSearchKey());
	String actResultsPageTitle=resultsPage.getSearchResultsPageTitle(product.getSearchKey());
	System.out.println("Actual Result Page title " +actResultsPageTitle);
	Assert.assertEquals(actResultsPageTitle,"Search - "+product.getSearchKey());
	}
	
	@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void displayProductsCountTest(Product product) {
		resultsPage=accountsPage.doSearchProduct(product.getSearchKey());
	int actProductCount=resultsPage.getSearchResultsProductsCount();
	Assert.assertTrue((actProductCount)>0);
	}
	
	@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void selectProductTest(Product product) {
		resultsPage=accountsPage.doSearchProduct(product.getSearchKey());
		prodInfoPage=resultsPage.selectProduct(product.getProductName());
		String actProdHeaderName=prodInfoPage.getProductHeaderName();
		Assert.assertEquals(actProdHeaderName, product.getProductName());
	}
	
	@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void selectProductImagesCountTest(Product product) {
		resultsPage=accountsPage.doSearchProduct(product.getSearchKey());
		prodInfoPage=resultsPage.selectProduct(product.getProductName());
		int actImagesCount=prodInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, product.getProductImages());
	}
}
