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
	
	@FindBy(id = "typeList")
	private WebElement Input;
	
	@FindBy(id = "saveType")
	private WebElement typeSubmitButton;
	
	public void enterType(String type) {
		this.typeInput.sendKeys(type);
		this.typeSubmitButton.click();
	}

}
