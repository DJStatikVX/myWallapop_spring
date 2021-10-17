package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_AddOfferView extends PO_NavView {
	
	public static void fillForm(WebDriver driver, String titleo, 
			String detailso, double priceo) {
		
		// Esperamos 5 segunos a que cargue el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 1);
		// Rellenamos el campo de título
		WebElement title = driver.findElement(By.name("title"));
		title.clear();
		title.sendKeys(titleo);
		// Rellenamos el campo de detalles
		WebElement details = driver.findElement(By.name("details"));
		details.clear();
		details.sendKeys(detailso);
		WebElement price = driver.findElement(By.name("price"));
		price.click();
		price.clear();
		price.sendKeys("" + priceo);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}