package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void prodInfoSetUp() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoTest() {
		resultsPage=accountsPage.doSearchProduct("Macbook");
		prodInfoPage=resultsPage.selectProduct("MacBook Pro");
		Map<String,String> actProdInfoMap=prodInfoPage.getProductInfo();
		System.out.println("Product Info details: " +actProdInfoMap);
		Assert.assertEquals(actProdInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(actProdInfoMap.get("Availability"), "In Stock");
		Assert.assertEquals(actProdInfoMap.get("productname"), "MacBook Pro");
		Assert.assertEquals(actProdInfoMap.get("productprice"), "$2,000.00");
		
		
	}
	
	@Test
	public void addToCartProductTest() {
		resultsPage=accountsPage.doSearchProduct("Macbook");
		prodInfoPage=resultsPage.selectProduct("MacBook Pro");
		prodInfoPage.enterProductQuantity();
		String alertMessage=prodInfoPage.selectAddToCartProduct();
		String prodName=prodInfoPage.getProductHeaderName();
		Assert.assertEquals("Success: You have added "+prodName +" to your shopping cart!\n×",alertMessage);
	}
	

}
