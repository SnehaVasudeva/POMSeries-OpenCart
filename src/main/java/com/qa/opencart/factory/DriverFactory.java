package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.frameworkexception.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	OptionsManager optionsManager;
	public static String highlightElement;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		//String browserName = System.getProperty("browser");
		System.out.println("Browser name is " + browserName);
		highlightElement = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase()) {
		case "chrome": {
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		}
		case "firefox": {
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		}

		case "edge": {
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		}

		default: {
			System.out.println("Please pass the right browser" + browserName);
			throw new FrameworkException("BROWSERNOTFOUNDEXCEPTION");

		}
		}
//		driver.get(prop.getProperty("url"));
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		return driver;

		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		Properties prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Test cases are running in the environment " + envName);
		try {
			if (envName == null) {
				ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
			} else
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/main/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/main/resources/config/config.properties");
					break;
				default:
					System.out.println("Please pass the right browser");
					throw new FrameworkException("NOTAVALIDENVIRONMENT");

				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;

	}
	

	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	

}
