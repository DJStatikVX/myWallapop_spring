package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {
	
	static public void fillForm(WebDriver driver, String dnip, String passwordp) {
		WebElement username = driver.findElement(By.name("username"));
		username.click();
		username.clear();
		username.sendKeys(dnip);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		// Pulsar el botón de Login.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void login(WebDriver driver, String dnip, String passwordp) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, dnip, passwordp);
	}
	
	static public void logout(WebDriver driver, String textToCheck) {
		PO_PrivateView.clickOption(driver, "logout", "text", textToCheck);
	}

}