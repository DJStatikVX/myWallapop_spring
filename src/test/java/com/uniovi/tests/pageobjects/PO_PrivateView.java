package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	
	static public void fillFormAddMark(WebDriver driver, int userOrder,
			String descriptionp, String scorep) {
		// Esperamos 5 segunos a que cargue el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumno userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenamos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void fillFormAddTeacher(WebDriver driver, int departmentOrder,
			String dnip, String namep, String lastnamep) {
		// Esperamos 5 segunos a que cargue el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el departamento 
		new Select(driver.findElement(By.id("category"))).selectByIndex(departmentOrder);
		// Rellenamos el campo de DNI
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("name"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement lastName = driver.findElement(By.name("surname"));
		lastName.click();
		lastName.clear();
		lastName.sendKeys(lastnamep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	
	static public void goToPage(WebDriver driver, int index) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", 
				"//a[contains(@class, 'page-link')]");
		elementos.get(index).click();
		
	}

}