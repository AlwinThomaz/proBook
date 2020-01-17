package com.project.probook.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage {
	
	@FindBy(xpath = "/html/body/nav/a")
	private WebElement homeNav;
	
	@FindBy(xpath = "/html/body/nav/div/ul/li[1]/a")
	private WebElement addTypeNav;
	
	@FindBy(xpath = "/html/body/nav/div/ul/li[2]/a")
	private WebElement addBookmarkNav;
	
	@FindBy(xpath = "/html/body/nav/div/ul/li[3]/a")
	private WebElement viewBookmarkNav;
	
	@FindBy(xpath = "/html/body/nav/div/ul/li[4]/a")
	private WebElement viewTypeNav;
	
	
	public void goIndexPage() {
		this.homeNav.click();
	}
	
	public void goAddTypePage() {
		this.addTypeNav.click();
	}
	
	public void goAddBookmarkPage() {
		this.addBookmarkNav.click();
	}
	
	public void goViewBookmarkPage() {
		this.viewBookmarkNav.click();
	}
	
	public void goViewTypePage() {
		this.viewTypeNav.click();
	}
	
}
