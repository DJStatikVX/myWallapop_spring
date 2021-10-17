package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ListUsers extends PO_NavView {

	static public void clickCheckbox(WebDriver driver, String option) {
		// Esperamos 5 segunos a que cargue el DOM porque en algunos equipos falla
		WebElement check = driver.findElement(By.id(option));
		check.click();
	}
	
	static public void deleteUsers(WebDriver driver) {
		WebElement button = driver.findElement(By.id("delete-button"));
		button.click();
	}

}