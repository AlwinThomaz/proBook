package com.project.probook.selenium.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.project.probook.selenium.constants.Constants;
import com.project.probook.selenium.pages.AddTypePage;
import com.project.probook.selenium.pages.ViewTypePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TypeFunctionalitytests {

	private WebDriver driver;
	private AddTypePage addTypePage;
	private ViewTypePage viewTypePage;

	private String typeName = "Test";
	private String editedTypeName = "hello";
	
	private String destination;

	@Before
	public void setup() {
		System.setProperty(Constants.PROPERTY, Constants.PATH);
		ChromeOptions options = new ChromeOptions();
		//options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().setSize(new Dimension(1600, 700));

		this.addTypePage = PageFactory.initElements(this.driver, AddTypePage.class);
		this.viewTypePage = PageFactory.initElements(this.driver, ViewTypePage.class);

		this.destination = Constants.HOST;
	}
	
//	@After
//    public void tearDown() throws Exception {
//        this.driver.close();
//    }

	@Test
	public void acreateTypeTest() throws InterruptedException {
		this.driver.get(this.destination + Constants.ADD_TYPE);
		this.addTypePage.submitType(this.typeName);
		Thread.sleep(2000);
		String alert = this.driver.switchTo().alert().getText();
		assertEquals("Type Created", alert);
		this.driver.switchTo().alert().accept();

	
		this.driver.get(this.destination + Constants.VIEW_TYPE);
		this.viewTypePage.editType();
		Thread.sleep(2000);
		
		
		this.viewTypePage.saveEditType(this.editedTypeName);
		String alert2 = this.driver.switchTo().alert().getText();
		assertEquals("Type Updated", alert2);
		this.driver.switchTo().alert().accept();


		this.viewTypePage.deleteType();
		Thread.sleep(2000);
		String alert3 = this.driver.switchTo().alert().getText();
		assertEquals("Type Deleted", alert3);
		this.driver.switchTo().alert().accept();

	}

}
