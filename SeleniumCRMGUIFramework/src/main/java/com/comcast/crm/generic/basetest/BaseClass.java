package com.comcast.crm.generic.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutlity.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {


	/* Create Object */
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility excel = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public  WebDriver driver;
	public  static WebDriver sdriver;



	@BeforeSuite(alwaysRun = true)
	public void configBS() throws SQLException {
		System.out.println("===Connect to DB , Report Config===");
		dbLib.getDbconnection();
	}


	//@Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	public void configBC() throws Throwable {
		System.out.println("==Launch the BROWSER==");
		String BROWSER	= fLib.getDataFromPropertiesFile("browser");
		//String BROWSER = System.getProperty("browser" , fLib.getDataFromPropertiesFile("browser"));
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
		//driver.manage().window().maximize();
	}

	@BeforeMethod(alwaysRun = true)
	public void configBM() throws Throwable {
		System.out.println("=login=");
		//String URL	= fLib.getDataFromPropertiesFile("url");
		//String USERNAME	= fLib.getDataFromPropertiesFile("username");
		//String PASSWORD	= fLib.getDataFromPropertiesFile("password");
		String URL = System.getProperty("url" ,fLib.getDataFromPropertiesFile("url") );
		String USERNAME = System.getProperty("username" , fLib.getDataFromPropertiesFile("username"));
		String PASSWORD = System.getProperty("password" , fLib.getDataFromPropertiesFile("password"));
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}


	@AfterMethod(alwaysRun = true)
	public void configAM() {
		System.out.println("=logout=");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}


	@AfterClass(alwaysRun = true)
	public void configAC() {
		System.out.println("==Close the BROWSER==");
		driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void configAS() throws SQLException {
		System.out.println("===close Db , Report backUP====");
		dbLib.closeDbconnection();

	}






}
