package com.project.probook.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddBookmarkPage {
	
	@FindBy(id = "bookmarkName")
	private WebElement bookmarkNameInput;
	
	@FindBy(id = "bookmarkDescription")
	private WebElement bookmarkDescriptionInput;
	
	@FindBy(id = "bookmarkUrl")
	private WebElement bookmarkUrlInput;
	
	@FindBy(xpath = "/html/body/header/div[2]/form/div[4]/div/select")
	private WebElement dropDownType;
	
	@FindBy(xpath = "/html/body/header/div[2]/form/div[4]/div/select/option[1]")
	private WebElement optionValue;
	
	@FindBy(xpath = "/html/body/header/div[2]/form/div[5]/div/button")
	private WebElement bookmarkSubmitButton;
	
	public void submitBookmark(String bookmarkName, String bookmarkDescription, String bookmarkUrl) {
		this.bookmarkNameInput.sendKeys(bookmarkName);
		this.bookmarkDescriptionInput.sendKeys(bookmarkDescription);
		this.bookmarkUrlInput.sendKeys(bookmarkUrl);
		this.dropDownType.click();
		this.optionValue.click();
		this.bookmarkSubmitButton.click();
	}

}
