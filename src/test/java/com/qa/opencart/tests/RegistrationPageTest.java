package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.AppConstants;
import com.qa.opencart.utilities.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registerSetUp() {
		registerPage=loginPage.navigateToRegistrationPage();
	}

	public String getRandomEmail() {
		return "opencart" + System.currentTimeMillis() + "@gmail.com";
		//return "opencart" + UUID.randomUUID()+ "@gmail.com";
	}
	
//	@DataProvider(name="UserRegisterData")
//	public Object[][] userRegisterdata() {
//		return new Object[][] {
//			{"OpenCart", "Automation1","98474784884","Test123#","yes"},
//			{"OpenCart", "Automation2","98474745555","Test111#","no"},
//			{"OpenCart", "Automation3","9847471111","Test222#","yes"}
//		};
//	}
	
	@DataProvider(name="regExcelData")
	public Object[][] getRegisterExcelTestData() {
		Object regData[][]=ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider="regExcelData")
	public void userRegisterTest(String firtName, String lastName,String phone,String password,String subscribe) {
//		String actRegisterSuccessMesg = registerPage.registerPageData("Test", "Automation", getRandomEmail(),
//				"6779876789", "Test123#", "Yes");
		String actRegisterSuccessMesg = registerPage.registerPageData(firtName,lastName,getRandomEmail(),phone,password,subscribe);
		Assert.assertEquals(actRegisterSuccessMesg, AppConstants.USER_REG_SUCCESS_MESSAGE);
		
	}

}
