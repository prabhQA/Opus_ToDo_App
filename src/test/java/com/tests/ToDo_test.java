package com.tests;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.driver.Driver;
import com.pages.ToDo_Pages;
import com.utilities.ReadPropertiesFile;

public class ToDo_test extends Driver {

	public static final String filename = null;
	public ToDo_Pages todo_page = new ToDo_Pages();
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);

	@BeforeClass
	public void initialization() {
		Driver.init(prop.getProperty("Browser"));
		todo_page.navigateTo_TodoApp();
		System.out.println("Title of the application is : " + Driver.driver.getTitle());
	}

	@Test(priority = 0)
	public void toDo_E2E_Test() throws Throwable {
		todo_page.todo_textField(prop.getProperty("todo_TextField_7AM"));
		todo_page.todo_textField(prop.getProperty("todo_TextField_8AM"));
		todo_page.todo_textField(prop.getProperty("todo_TextField_9AM"));
		todo_page.todo_textField(prop.getProperty("todo_TextField_1PM"));
		todo_page.todo_textField(prop.getProperty("todo_TextField_4PM"));
		todo_page.todo_textField(prop.getProperty("todo_TextField_6PM"));
		todo_page.todo_textField(prop.getProperty("todo_TextField_8PM"));
		todo_page.todo_textField(prop.getProperty("todo_TextField_9PM"));
		String actual = todo_page.validate_TodoList();
		System.out.println("Elements Added in the TO-DO list are : " +actual);
		todo_page.clearAll_button();
		System.out.println("All the elements are cleared from the list");
	}

	@AfterTest
	public void quit() {
		Driver.driver.quit();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}