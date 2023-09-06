package Practice.ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrganization {

	@Test
	public void organizationContact() throws IOException, InterruptedException {
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
		Sheet sheet = book.getSheet("Contact");
		Row row = sheet.getRow(7);
		String organization = row.getCell(2).toString()+ randomNum;
		String Last_Name = row.getCell(3).toString() + randomNum;
		System.out.println(Last_Name);

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
		driver.findElement(By.name("accountname")).sendKeys(organization);
		driver.findElement(By.className("crmbutton")).click();

		Thread.sleep(2000);
		
		// clicking on contact button
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();

		// clicking on create new button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// enter the data into last name
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(Last_Name);
		
		driver.findElement(By.xpath("(//td[@class='dvtCellInfo']/img[@title='Select'])[1]")).click();
		
		Set<String> all_id = driver.getWindowHandles();
		for(String id : all_id)
		{
			driver.switchTo().window(id);
			String title = driver.getTitle();
			if(title.contains("Accounts&action"))
			{
				break;
			}
		}
		
		driver.findElement(By.id("search_txt")).sendKeys(organization);
		
		driver.findElement(By.xpath("//input[@name='search']")).click();
		
		driver.findElement(By.xpath("//a[text()='"+organization+"']")).click();
		
		
		Set<String> all_id1 = driver.getWindowHandles();
		for(String id1 : all_id1)
		{
			driver.switchTo().window(id1);
			String title = driver.getTitle();
			if(title.contains("Contacts&action"))
			{
				break;
			}
		}
		
		
		driver.findElement(By.xpath("//input[contains(@class,'crmButton')]")).click();
		
		driver.quit();
		
		
		
		
		
	
		
		
	}

}
