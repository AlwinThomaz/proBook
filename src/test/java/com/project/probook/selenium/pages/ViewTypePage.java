package com.project.probook.selenium.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewTypePage {
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[2]/button[1]")
	private WebElement typeEditButton;
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[1]/input")
	private WebElement typeEditName;
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[2]/button[2]")
	private WebElement typeSaveEditButton;
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[2]/button[3]")
	private WebElement typeDeleteButton;
	
	public void editType() {
		this.typeEditButton.click();
	}
	
	
	public void saveEditedType(String editedTypeName) {
		this.typeEditName.clear();
		this.typeEditName.sendKeys(editedTypeName);
		this.typeSaveEditButton.click();
	}
	
	public void deleteType() {
		this.typeDeleteButton.click();
	}
		
}
