package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ListOffers extends PO_NavView {


	
	static public void search(WebDriver driver, String searchText) {
		WebElement search = driver.findElement(By.name("searchText"));
		search.click();
		search.clear();
		search.sendKeys(searchText);
		WebElement button = driver.findElement(By.id("searchButton"));
		button.click();
	}
	
	static public void checkNOffers(WebDriver driver, int size) {
		WebElement search = driver.findElement(By.name("tableRows"));
		List<WebElement> rows = search.findElements(By.xpath("./child::*"));
		assertEquals(size, rows.size());
	}


}