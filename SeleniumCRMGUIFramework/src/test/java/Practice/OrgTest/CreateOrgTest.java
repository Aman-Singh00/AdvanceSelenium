package Practice.OrgTest;

/**
 * @author AmanSingh
 */

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CreateOrgTest {
	
	@Test
	public void org() throws Throwable{

		// fetching the common data from excel file
		FileInputStream fis = new FileInputStream("E:\\data\\CommanData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String Browser = pobj.getProperty("browser");
		String url = pobj.getProperty("url");
		String username = pobj.getProperty("username");
		String password = pobj.getProperty("password");

		// Random number
		Random rn = new Random();
		int randomNum = rn.nextInt(10000);

		// fetching the organization name from excel file
		FileInputStream stream = new FileInputStream("E:\\data\\Book1.xlsx");
		Workbook book = WorkbookFactory.create(stream);
		Sheet sheet = book.getSheet("Org");
		Row row = sheet.getRow(1);
		String OrgName = row.getCell(2).toString() + randomNum;
		System.out.println(OrgName);

		WebDriver driver = null;

		// Browser is launching
		if (Browser.equals("chrome"))
			driver = new ChromeDriver();
		else if (Browser.equals("firefox"))
			driver = new FirefoxDriver();
		else if (Browser.equals("edge"))
			driver = new EdgeDriver();
		else
			driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		// navigate to login page
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// clicking on organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		driver.findElement(By.className("crmbutton")).click();

		// verifying the header Msg
		String actualMsg = driver.findElement(By.className("dvHeaderText")).getText();

		if (actualMsg.contains(OrgName)) {
			System.out.println(OrgName + " Created Successfully");
		} else {
			System.out.println(OrgName + " Not Created Successfully");
		}

		// verifying the organization name
		String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();

		if (actualOrgName.equals(OrgName)) {
			System.out.println(OrgName + " Verified successfully");
		} else {
			System.out.println(OrgName + " Not Verified successfully");
		}

		driver.quit();
	}

}
