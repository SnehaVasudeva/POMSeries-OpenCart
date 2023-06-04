package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	private By productText = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCart= By.id("button-cart");
	private By addToCartSuccessMesg= By.cssSelector("div.alert");
	

	public String getProductHeaderName() {
		return eleUtil.doGetElementText(productText);
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, AppConstants.SHORT_DEFAULT_WAIT).size();
	}
	
	public Map<String,String> getProductInfo() {
		//productInfoMap=new HashMap<String,String>();
		//productInfoMap=new LinkedHashMap<String,String>();
		productInfoMap=new TreeMap<String,String>();
		getProductMetaData();
		getProductPricingdata();
		productInfoMap.put("productname", getProductHeaderName());
		return productInfoMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		
		for (WebElement e : metaDataList) {
			String metaText = e.getText();
			String metaInfo[] = metaText.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
productInfoMap.put(key,value);
		}
	}
	
	private void getProductPricingdata() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String priceValue=priceList.get(0).getText();
		String exTaxPrice=priceList.get(1).getText();
		String exTaxPriceValue=exTaxPrice.split(":")[1].trim();
		productInfoMap.put("productprice", priceValue);
		productInfoMap.put("extaxprice", exTaxPriceValue);
	}
	
	public void enterProductQuantity() {
		eleUtil.doSendKeys(quantity, "2");
	
	
	}
	
public String selectAddToCartProduct() {
		eleUtil.doClick(addToCart);
		 return eleUtil.doGetElementText(addToCartSuccessMesg);
	}

}
