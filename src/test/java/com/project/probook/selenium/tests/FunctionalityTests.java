package com.project.probook.selenium.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.probook.selenium.constants.Constants;
import com.project.probook.selenium.pages.AddBookmarkPage;
import com.project.probook.selenium.pages.AddTypePage;
import com.project.probook.selenium.pages.ViewBookmarkPage;
import com.project.probook.selenium.pages.ViewTypePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FunctionalityTests {

	private WebDriver driver;
	
	private AddTypePage addTypePage;
	private ViewTypePage viewTypePage;
	
	private AddBookmarkPage addBookmarkPage;
	private ViewBookmarkPage viewBookmarkPage;

	private String typeName = "Test";
	private String editedTypeName = "Hello";
	
	private String bookmarkName = "Test";
	private String bookmarkDescription = "Test123";
	private String bookmarkUrl = "http://www.test.com";

	private String editedBookmarkName = "BAE";
	private String editedBookmarkDescription = "BAE123";
	private String editedBookmarkUrl = "http://www.BAE.com";
	
	private String destination;
	
	@LocalServerPort
	private int port;
	
	
	@Value("${server.servlet.context-path}")
	private String context;

	@Before
	public void setup() {
		System.setProperty(Constants.PROPERTY, Constants.PATH);
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().setSize(new Dimension(1600, 700));

		this.addTypePage = PageFactory.initElements(this.driver, AddTypePage.class);
		this.viewTypePage = PageFactory.initElements(this.driver, ViewTypePage.class);
		this.addBookmarkPage = PageFactory.initElements(this.driver, AddBookmarkPage.class);
		this.viewBookmarkPage = PageFactory.initElements(this.driver, ViewBookmarkPage.class);

		this.destination = Constants.HOST + port + context;
	}
	

	@Test
	public void allFunctionalityTest() throws InterruptedException {
		this.driver.get(this.destination + Constants.ADD_TYPE);
		this.addTypePage.submitType(this.typeName);

		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.alertIsPresent());
		String alert = this.driver.switchTo().alert().getText();
		assertEquals("Type Created", alert);
		this.driver.switchTo().alert().accept();

		
		this.driver.get(this.destination + Constants.ADD_BOOKMARK);
		this.addBookmarkPage.submitBookmark(bookmarkName, bookmarkDescription, bookmarkUrl);
		wait.until(ExpectedConditions.alertIsPresent());
		String alert2 = this.driver.switchTo().alert().getText();
		assertEquals("Bookmark Created", alert2);
		this.driver.switchTo().alert().accept();

		this.driver.get(this.destination + Constants.VIEW_BOOKMARK);
		this.viewBookmarkPage.viewBookmark();
		Thread.sleep(2000);

		this.viewBookmarkPage.editBookmark();
//		Thread.sleep(2000);

		this.viewBookmarkPage.saveEditedBookmark(editedBookmarkName, editedBookmarkDescription, editedBookmarkUrl);
		wait.until(ExpectedConditions.alertIsPresent());
		String alert3 = this.driver.switchTo().alert().getText();
		assertEquals("Bookmark Updated", alert3);
		this.driver.switchTo().alert().accept();

		this.viewBookmarkPage.deleteBookmark();
		wait.until(ExpectedConditions.alertIsPresent());
		String alert4 = this.driver.switchTo().alert().getText();
		assertEquals("Bookmark Deleted", alert4);
		this.driver.switchTo().alert().accept();
//		Thread.sleep(2000);

		this.driver.get(this.destination + Constants.VIEW_TYPE);
//		Thread.sleep(2000);
		this.viewTypePage.editType();
//		Thread.sleep(2000);
		
		this.viewTypePage.saveEditedType(this.editedTypeName);
		wait.until(ExpectedConditions.alertIsPresent());

		String alert5 = this.driver.switchTo().alert().getText();
		assertEquals("Type Updated", alert5);
		this.driver.switchTo().alert().accept();


		this.viewTypePage.deleteType();
		wait.until(ExpectedConditions.alertIsPresent());
		String alert6 = this.driver.switchTo().alert().getText();
		assertEquals("Type Deleted", alert6);
		this.driver.switchTo().alert().accept();

	}

}
