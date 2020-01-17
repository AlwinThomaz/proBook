package com.project.probook.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddTypePage {
	
	@FindBy(id = "createType")
	private WebElement typeNameInput;
	
	@FindBy(id = "saveType")
	private WebElement typeSubmitButton;
	
	public void submitType(String typeName) {
		this.typeNameInput.sendKeys(typeName);
		this.typeSubmitButton.click();
	}
	

}
