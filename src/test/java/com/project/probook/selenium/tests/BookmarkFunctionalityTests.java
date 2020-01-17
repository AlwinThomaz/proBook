package com.project.probook.selenium.tests;

import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.project.probook.selenium.constants.Constants;
import com.project.probook.selenium.pages.AddBookmarkPage;
import com.project.probook.selenium.pages.ViewBookmarkPage;

public class BookmarkFunctionalityTests {
	
	private WebDriver driver;
	private AddBookmarkPage addBookmarkPage;
	private ViewBookmarkPage viewBookmarkPage;
	
	private String  lol;


	private String destination;

	@Before
	public void setup() {
		System.setProperty(Constants.PROPERTY, Constants.PATH);
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().setSize(new Dimension(1600, 700));

		this.addBookmarkPage = PageFactory.initElements(this.driver, AddBookmarkPage.class);
		this.viewBookmarkPage = PageFactory.initElements(this.driver, ViewBookmarkPage.class);
		
		this.destination = Constants.HOST;
	}
	


}
