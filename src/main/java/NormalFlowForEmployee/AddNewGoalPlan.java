package NormalFlowForEmployee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewGoalPlan {	
     WebDriver driver;

    public AddNewGoalPlan(WebDriver driver) {
        this.driver = driver;

    }

    private By goalPlanManagementLink = By.xpath("//h3[contains(text(),'Goal Time Period Plan Management')]");
    private By addGoalPlanButton = By.id("addGoalPlan");
    private By goalPlanNameField = By.xpath("//input[@id='goalPlanName']");
    private By datepickerSwitch = By.xpath("//th[@class=\"datepicker-switch\"]");
    private By nextMonthButton = By.xpath("//th[@class=\"next\"]");
    private By ratingScaleField = By.xpath("//input[@placeholder=\"Select Rating Scale\"]");
    private By ratingScaleDropdown = By.xpath("//div[@class=\"selectize-dropdown-content\"]");
    private By groupRadioButton = By.xpath("//input[@id=\"selectGroupRadio\"]");
    private By empGroupField = By.xpath("//input[@placeholder=\"Select Target Employees Group for this Plan\"]");
    private By isActiveCheckbox = By.xpath("//input[@id=\"isActive\"]");
    private By isWeightageBasedCheckbox = By.xpath("//input[@id=\"isWeightageBased\"]");
    private By submitButton = By.xpath("//button[@id=\"submit_btn_action\"]");

    public void addGoalPlan1(String goalPlanName, String empGroup,String RatingScale) throws InterruptedException 
    {
        JavascriptExecutor Srollup = (JavascriptExecutor) driver;
        Srollup.executeScript("window.scrollBy(0,1800)");
        driver.findElement(goalPlanManagementLink).click();
        driver.findElement(addGoalPlanButton).click();
        driver.findElement(goalPlanNameField).sendKeys(goalPlanName);

        // Select start date
        LocalDate currentDate = LocalDate.now();
        
        // Create a formatter for "MMMM yyyy" (e.g., January 2025)
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");

        // Print the current month
        String formattedDate1 = currentDate.format(monthYearFormatter);
        String formattedDay = currentDate.format(dayFormatter);
        driver.findElement(By.xpath("//input[@class=\"form-control dpd1\"]")).click();       
        selectDate(formattedDate1,formattedDay);
        
        // Select end date     
        LocalDate thirdMonth = currentDate.plusMonths(3);
        String Third_Month_Year = thirdMonth.format(monthYearFormatter);
        String Third_MonthDATE = thirdMonth.format(dayFormatter);
        
        driver.findElement(By.xpath("//input[@class=\"form-control dpd2\"]")).click();
        selectDate(Third_Month_Year, Third_MonthDATE);
        JavascriptExecutor Srollup1 = (JavascriptExecutor) driver;
        Srollup1.executeScript("window.scrollBy(0,800)");
        
        // Select rating scale
        driver.findElement(ratingScaleField).sendKeys(RatingScale);
        driver.findElement(ratingScaleDropdown).click();
        
        // Select employee group
        JavascriptExecutor Srollup2 = (JavascriptExecutor) driver;
        Srollup2.executeScript("window.scrollBy(0,1000)");
        driver.findElement(groupRadioButton).click();
        driver.findElement(empGroupField).sendKeys(empGroup);

        WebElement element = driver.findElement(By.cssSelector("span.name"));
        if (element.getText().contains(empGroup)) {       	
            System.out.println(element.getText());
            element.click();
        }
        driver.findElement(isActiveCheckbox).click();
        driver.findElement(isWeightageBasedCheckbox).click();  
        driver.findElement(submitButton).click();
    }

    private void selectDate(String month, String day) throws InterruptedException 
    {
        String displayedMonth = driver.findElement(datepickerSwitch).getText();
        while (!displayedMonth.equals(month)) 
        {
            driver.findElement(nextMonthButton).click();
            displayedMonth = driver.findElement(datepickerSwitch).getText();
            Thread.sleep(100);
        }
        driver.findElement(By.xpath("//td[@class='day' and text()='" + day + "']")).click();
        Thread.sleep(100);
    }

    public String isGoalPlanDisplayed(String goalPlanName) 
    {
        return driver.findElement(By.xpath("//td[contains(text(),'" + goalPlanName + "')]")).getText();
    
    }  
    
}