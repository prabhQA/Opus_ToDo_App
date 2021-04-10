package com.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.driver.Driver;
import com.utilities.ReadPropertiesFile;

public class ToDo_Pages extends Driver {
	public static final String filename = null;
	public ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public Properties prop = readPropertiesFile.readPropertiesFile(filename);

	public ToDo_Pages() {
		PageFactory.initElements(driver, this);
	}

	public void navigateTo_TodoApp() {
		driver.get(prop.getProperty("APP_URL"));
	}
	
	public void todo_textField(String testData) {
		driver.findElement(By.xpath(prop.getProperty("todo_TextField_xpath"))).sendKeys(testData);
		driver.findElement(By.xpath(prop.getProperty("todo_Add_button_Xpath"))).click();
	}

	public void clearAll_button() {
		driver.findElement(By.xpath(prop.getProperty("todo_ClearAll_button_Xpath"))).click();
	}
	
	public String validate_TodoList() {
		List<WebElement> list = driver.findElements(By.xpath(prop.getProperty("todo_elements_list_Xpath")));
		String actual =null;
		for (WebElement ele : list) {
			actual = ele.getText();
		}
		return actual;
	}
}
