package Practice.ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDate {

	@Test
	public void contactSupportDate() throws IOException, InterruptedException {
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
		Row row = sheet.getRow(4);
		String Last_Name = row.getCell(2).toString() + randomNum;
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

		// clicking on contact button
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();

		// clicking on create new button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// enter the data into last name
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(Last_Name);
		
		//program for system date
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		System.out.println(date);
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String required_Date = sdf.format(cal.getTime());
		System.out.println(required_Date);
		
		Thread.sleep(5000);
		//enter the date into start date
		driver.findElement(By.xpath("//input[@name='support_start_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_start_date']")).sendKeys(date);
		
		
		Thread.sleep(5000);
		//enter the end date into end date 
		driver.findElement(By.xpath("//input[@name='support_end_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_end_date']")).sendKeys(required_Date);

		// click on save button
		driver.findElement(By.xpath("//input[contains(@class,'crmButton')]")).click();

		// verify the last name

		String ActualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		System.out.println(ActualLastName);

		if (ActualLastName.equals(Last_Name)) {
			System.out.println(Last_Name + " verified successfully");
		} else {
			System.out.println(Last_Name + " not Verified successfully");
		}
		
		//verify the start date
		String Actual_start_date= driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		if(Actual_start_date.equals(date))
		{
			System.out.println(date+" verified successfully");
		}
		else
		{
			System.out.println(date+ " not verified successfully");
		}
		
		//verify the end date 
		String Actual_end_date = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		if(Actual_end_date.equals(required_Date))
		{
			System.out.println(required_Date+" verified successfully");
		}
		else
		{
			System.out.println(required_Date + " not verified successfully");
		}
		

		driver.quit();
	}

}
