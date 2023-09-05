package Practice.OrgTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreateOrgWithIndustryTest {

	@Test
	public void industry() throws IOException, InterruptedException{
		


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
		int randomNum = rn.nextInt(1000);

		// fetching the organization name from excel file
		FileInputStream stream = new FileInputStream("E:\\data\\Book1.xlsx");
		Workbook book = WorkbookFactory.create(stream);
		Sheet sheet = book.getSheet("Org");
		Row row = sheet.getRow(4);
		String OrgName = row.getCell(2).toString() + randomNum;
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();

		System.out.println(OrgName);
		System.out.println(industry);
		System.out.println(type);

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

		WebElement industry_droddown = driver.findElement(By.xpath("//select[@name='industry']"));

		Select s = new Select(industry_droddown);
		s.selectByVisibleText(industry);
		
		WebElement type_dropdown = driver.findElement(By.xpath("//select[@name='accounttype']"));
		
		Select s1 = new Select(type_dropdown);
		s1.selectByVisibleText(type);

		Thread.sleep(5000);

		driver.findElement(By.className("crmbutton")).click();

		// verifying the industry name
		String actualIndustry= driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();


		if (actualIndustry.equals(industry)) {
			System.out.println(industry + " Verified successfully");
		} else {
			System.out.println(industry + " Not Verified successfully");
		}
		
		//verifying the type name
		
		String actualType= driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		
		if (actualType.equals(type)) {
			System.out.println(type + " Verified successfully");
		} else {
			System.out.println(type + " Not Verified successfully");
		}

		driver.quit();
	}

}
