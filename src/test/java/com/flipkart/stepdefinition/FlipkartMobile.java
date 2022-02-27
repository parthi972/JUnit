package com.flipkart.stepdefinition;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class FlipkartMobile {
	
	static long startTime;
	static WebDriver driver;
	
	@BeforeClass
	public static void launch() {
		
	
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://www.flipkart.com/");
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@AfterClass
	public static void browserQuit() {
		
		
		driver.quit();

	}
	
	@Before
	public void beforeMethod() {
		
		//System.out.println("before method");
		 startTime = System.currentTimeMillis();

	}
	
	@After
    public void afterMethod() {
		
		//System.out.println("after method");
		long endTime = System.currentTimeMillis();
		
		System.out.println("Time Taken is :"+ (endTime - startTime));

	}
	
	@Test
	public void method1() {
		
		System.out.println("login method - 1");
		try{
			WebElement button = driver.findElement(By.xpath("//button[text()='✕']"));
			Assert.assertTrue(button.isDisplayed());
			button.click();
		}catch (Exception e) {
			System.out.println("login is not applicable");
		}

	}
	
	static String name;
	static String name2;
	
	@Test
	public void method2() {

		System.out.println("mobile search method - 2");
		driver.findElement(By.name("q")).sendKeys("realme",Keys.ENTER);
		WebElement mobileName = driver.findElement(By.xpath("(//div[contains(text(),'realme')])[2]"));
		 name = mobileName.getText();
		System.out.println(name);
		mobileName.click();

	}
	
	@Test
	public void method3() {
		
		System.out.println(" method - 3");
		String parent = driver.getWindowHandle();
		Set<String> child = driver.getWindowHandles();
		for (String x : child) {
			if(!x.equals(parent)){
				driver.switchTo().window(x);
			}
			
		}
		
		WebElement mobileName2 = driver.findElement(By.xpath("(//span[contains(text(),'realme')])[1]"));
		 name2 = mobileName2.getText();
		System.out.println(name2);
		mobileName2.click();


	}
	
	@Ignore
	@Test
	public void method4() {
		
		System.out.println("method - 4");
        
		//excel read & compare both are equals
		Assert.assertEquals(name, name2);
		
		
	}
	
	@Test
	public void method5() throws IOException {
		
		System.out.println("screenshot method - 5");
        
		TakesScreenshot tk = (TakesScreenshot)driver;
		File source = tk.getScreenshotAs(OutputType.FILE);
		File destination = new File(".//target//report.png");
		FileUtils.copyFile(source, destination);
		
	}
	
	}
		
	


