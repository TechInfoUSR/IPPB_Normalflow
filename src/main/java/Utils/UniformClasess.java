package Utils;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UniformClasess  {
	WebDriver driver;
	Properties prop;
	
	public UniformClasess(WebDriver driver , Properties prop){
		this.driver = driver;
		this.prop= prop;
	}
	

	public void login(String username, String password) {

		By usernameField = By.xpath("//input[@name=\"username\"]");
		By passwordField = By.xpath("//input[@name=\"password\"]");
		By loginButton = By.xpath("//button[@type=\"submit\"]");
		
		driver.findElement(usernameField).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
	}
	
	public WebElement WaitUntill_Visible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement WaitUntill_Clickable(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	    return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	
}
