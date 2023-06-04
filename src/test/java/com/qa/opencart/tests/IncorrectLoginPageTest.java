package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class IncorrectLoginPageTest extends BaseTest {

	@DataProvider
	public Object[][] getIncorrectLoginData() {
		return new Object[][] {
			{"abc@gmail.com","Test123#"},
			{"opencartTest1@gmail.com","Test"},
			{"abc","12345"}};
		}
	
@Test(dataProvider="getIncorrectLoginData")
public void getIncorrectLoginCredentialsTest(String email,String password) {
	Assert.assertTrue(loginPage.doIncorrectLoginCredentials(email, password));
}

}