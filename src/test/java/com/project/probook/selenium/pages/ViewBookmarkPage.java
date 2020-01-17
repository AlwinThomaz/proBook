package com.project.probook.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewBookmarkPage {
	
	@FindBy(xpath = "/html/body/header/form/div[1]/div/select")
	private WebElement dropDownTypeSelect;
	
	@FindBy(xpath = "/html/body/header/form/div[1]/div/select/option[1]")
	private WebElement optionValueSelect;
	
	@FindBy(id = "saveTypeBookmark")
	private WebElement viewBookmarkSubmitButton;
	
	@FindBy(id = "/html/body/header/table/tbody/tr/td[4]/button[1]")
	private WebElement bookmarkEditButton;
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[1]/input")
	private WebElement bookmarkEditName;
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[2]/input")
	private WebElement bookmarkEditDescription;
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[3]/input")
	private WebElement bookmarkEditUrl;
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[4]/button[2]")
	private WebElement bookmarkSaveEditButton;
	
	@FindBy(xpath = "/html/body/header/table/tbody/tr/td[4]/button[3]")
	private WebElement bookmarkDeleteButton;
	
	public void viewBookmark() {
		this.dropDownTypeSelect.click();
		this.optionValueSelect.click();
		this.viewBookmarkSubmitButton.click();
	}
		
	public void editBookmark(String bookmarkName, String bookmarkDescription, String bookmarkUrl) {
		this.bookmarkEditName.sendKeys(bookmarkName);
		this.bookmarkEditName.sendKeys(bookmarkDescription);
		this.bookmarkEditName.sendKeys(bookmarkUrl);
		this.bookmarkSaveEditButton.click();
	}
	
	public void deleteBookmark() {
		this.bookmarkDeleteButton.click();
	}
	
	
}
