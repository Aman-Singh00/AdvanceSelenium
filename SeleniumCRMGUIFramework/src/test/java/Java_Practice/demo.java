package Java_Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class demo {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://shoppersstack.com/user-signin");
		driver.manage().window().maximize();
	
		
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("abcd");
		
		//driver.findElement(By.xpath("//span[text()='Login']")).click();

		// System.out.println(email.getAttribute("title"));
		driver.quit();

	}

}
