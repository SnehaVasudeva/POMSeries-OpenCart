package com.qa.opencart.utilities;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE_VALUE ="Account Login";
	public static final String ACCOUNT_PAGE_TITLE_VALUE ="My Account11";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE="route=account/login";
	public static final String ACCOUNT_PAGE_URL_FULL_VALUE="https://naveenautomationlabs.com/opencart/index.php?route=account/account";
	public static final int SHORT_DEFAULT_WAIT =5;
	public static final int MEDIUM_DEFAULT_WAIT =10;
	public static final int LONG_DEFAULT_WAIT =20;
	public static final List<String> EXP_HEADERS_LIST= Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final String USER_REG_SUCCESS_MESSAGE="Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME="register";
}
