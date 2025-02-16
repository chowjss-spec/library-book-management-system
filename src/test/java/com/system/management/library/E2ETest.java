package com.system.management.library;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class E2ETest {
	WebDriver driver;
	  @Test
	  public void getTitle() {
		  String title = driver.getTitle();
		  Assert.assertEquals(title,"Library Book Management System");
	  }
	  @BeforeTest
	  public void beforeTest() {
	  }

	  @AfterTest
	  public void afterTest() {
	  }

	  @BeforeSuite
	  public void beforeSuite() {
		  ChromeOptions options = new ChromeOptions();
	      options.addArguments("--headless");
	      options.addArguments("--disable-gpu");
	      options.addArguments("--no-sandbox");
	      options.addArguments("--disable-dev-shm-usage");
		  this.driver = new ChromeDriver(options);
		  driver.get("http://localhost:8080/LibraryBookManagementSystem/Home");
	  }

	  @AfterSuite
	  public void afterSuite() {
		  driver.quit();
	  }

}
