//package com.project.probook.selenium.tests;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.PageFactory;
//
//import com.project.probook.selenium.constants.Constants;
//import com.project.probook.selenium.pages.AddBookmarkPage;
//import com.project.probook.selenium.pages.AddTypePage;
//import com.project.probook.selenium.pages.IndexPage;
//import com.project.probook.selenium.pages.ViewBookmarkPage;
//import com.project.probook.selenium.pages.ViewTypePage;
//
//public class NavBarTests {
//
//	private WebDriver driver;
//
//	private IndexPage indexPage;
//	private AddTypePage addTypePage;
//	private AddBookmarkPage addBookmarkPage;
//	private ViewBookmarkPage viewBookmarkPage;
//	private ViewTypePage viewTypePage;
//
//	private String destination;
//
//	@Before
//	public void setup() {
//		System.setProperty(Constants.PROPERTY, Constants.PATH);
//		ChromeOptions options = new ChromeOptions();
//		options.setHeadless(true);
//		this.driver = new ChromeDriver(options);
//		this.driver.manage().window().setSize(new Dimension(1600, 700));
//
//		this.indexPage = PageFactory.initElements(this.driver, IndexPage.class);
//		this.addTypePage = PageFactory.initElements(this.driver, AddTypePage.class);
//		this.addBookmarkPage = PageFactory.initElements(this.driver, AddBookmarkPage.class);
//		this.viewBookmarkPage = PageFactory.initElements(this.driver, ViewBookmarkPage.class);
//		this.viewTypePage = PageFactory.initElements(this.driver, ViewTypePage.class);
//		this.destination = Constants.HOST;
//	}
//
//	@Test
//	public void navigateHomeTest() {
//		this.driver.get(this.destination);
//		this.indexPage.goIndexPage();
//		assertEquals(driver.getCurrentUrl(), this.destination + Constants.INDEX);
//	}
//
//	@Test
//	public void navigateAddTypeTest() {
//		this.driver.get(this.destination);
//		this.indexPage.goAddTypePage();
//		assertEquals(driver.getCurrentUrl(), this.destination + Constants.ADD_TYPE);
//	}
//
//	@Test
//	public void navigateAddBookmarkTest() {
//		this.driver.get(this.destination);
//		this.indexPage.goAddBookmarkPage();
//		assertEquals(driver.getCurrentUrl(), this.destination + Constants.ADD_BOOKMARK);
//	}
//	
//	@Test
//	public void navigateViewBookmarkTest() {
//		this.driver.get(this.destination);
//		this.indexPage.goViewBookmarkPage();
//		assertEquals(driver.getCurrentUrl(), this.destination + Constants.VIEW_BOOKMARK);
//	}
//	
//	@Test
//	public void navigateViewTypeTest() {
//		this.driver.get(this.destination);
//		this.indexPage.goViewTypePage();
//		assertEquals(driver.getCurrentUrl(), this.destination + Constants.VIEW_TYPE);
//	}
//
//}
