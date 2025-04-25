package NormalFlowForEmployee;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class One_to_One_manager 
{
    private WebDriver driver;
    private Properties prop;
    
    public One_to_One_manager(WebDriver driver, Properties prop) 
    {
        this.driver = driver;
        this.prop = prop;
    }

    private By dropdownToggle = By.xpath("//a[@class='dropdown-toggle']");
    private By avatarIcon = By.xpath("//a[@class='avatar-sec header-icon']");
    
    private By employeeName = By.xpath("//span[contains(text(),'Ranganath ')]");
    private By goalCycleLink(String cycleName) 
    {
        return By.xpath("//a[contains(text(),'" + cycleName + "')]");
    }

    public void selectGoalCycle(String cycleName) throws InterruptedException 
    {
        driver.findElement(dropdownToggle).click();
        driver.findElement(goalCycleLink(cycleName)).click();
    }
    
    public void navigateToEmployeeSelf() throws InterruptedException 
    {
//        driver.findElement(avatarIcon).click();
    	
    	String employeeName= prop.getProperty("empname");
        driver.findElement(By.xpath("//span[contains(text(),'"+employeeName+"')]")).click();
        driver.findElement(By.xpath("(//textarea[@class=\"form-control one_to_one_meeting\"])[1]")).sendKeys("1:1 meeting comment");
        driver.findElement(By.xpath("(//a[@class=\"btn checkin-btn block-btn\"])[1]")).click();

    }
    public String isSelfsub() throws InterruptedException {
    	Thread.sleep(500);
		 return driver.findElement(By.xpath("//div[contains(text(),'Submitted successfully')]")).getText();
	  }
    
}
