package com.weborder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order extends CustomCredentials {
	

	public static void main(String[] args) {
		Random randomNumber = new Random();
		CustomCredentials s1 = new CustomCredentials();
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yaroslav Kryvda\\Documents\\selenium dependencies\\drivers\\chromedriver.exe");
		
		//1.Open browser
		WebDriver driver = new ChromeDriver();
		
		//2.Go to url http://secure.smartbearsoftware.com/samples/TestCompl...
		driver.get( "http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		String actual = driver.getTitle();
		
		if(actual.contains("Web Orders Login")) {
			System.out.println("Succefully landed on the main Page: "+ actual);
		}else {
			System.out.println("Fail landing on the main Page: "+ actual);
		}
		
		//3. Login using username Tester and password test.
		
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys(s1.userName);
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys(s1.password);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		
		if(actual.contains("Web Orders Login")) {
			System.out.println("Succefully landed on the main Page no2: "+ actual);
		}else {
			System.out.println("Fail landing on the main Page no2: "+ actual);
		}
		
		//4.Click on Order link:
		driver.findElement(By.linkText("Order")).click();
		
		//Step 5 Input random number to  Quantity.
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(randomNumber.nextInt(100)+1+"");
		
		//6 Enter Customer Name: John <middle_name> Smith
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(s1.randomStringFullName());
		
		//Step 7 Enter street: 123 Any st.
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys(s1.streetName);
		//Step 8 Enter City: Anytown
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(s1.cityName);
		//Step 9 Enter State: Virginia
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(s1.stateName);
		//Step 10 Enter a random 5 digit number to the zip code field
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(generateNumber(5));
		//Step 11-12 - Select any card type./ If you selected Visa....
		int CardNumber = randomNumber.nextInt(3);
		
		switch(CardNumber) {
			case 0:
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
				driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("4"+generateNumber(15));
				break;
			case 1:
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
				driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("5"+generateNumber(15));
				break;
			case 2:
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click();
				driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("3"+generateNumber(14));
				break;
			default:
				System.out.println("Can't select Card type");
		}
		
		// Step 13 Enter any valid expiration date . BY using LocalDateTime
		LocalDateTime ldt = LocalDateTime.now();
		ldt=ldt.plusYears(5);
		String validDate=DateTimeFormatter.ofPattern("MM/yy", Locale.ENGLISH).format(ldt)+"";
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(validDate);
		
		// Step 14 Click on Process 
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		//Step 15 String expectedLabel = "New order has been successfully added.";
		
		String actualLabel = driver.findElement(By.xpath("//*[@id='ctl00_MainContent_fmwOrder']/tbody/tr/td/div/strong")).getText();
		if(actualLabel.contains("New order has been successfully added.")) {
			System.out.println("New order has been successfully added");
		}else {
			System.out.println("New order has NOT been successfully added");
		}
		
		
		
		
		
		driver.close();
		
		//Just added
	}



}
