package NormalFlowForEmployee;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Utils.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class emp_assesment_Submission {
	  private WebDriver driver;
      private Properties prop;
      ExplicitWait ExplicitWait;


    		private By dropdownToggle = By.xpath("//a[@class='dropdown-toggle']");
    		private By goalCycleLink(String cycleName) 
    		{
            return By.xpath("//a[contains(text(),'" + cycleName + "')]");
            }
    	  public emp_assesment_Submission(WebDriver driver, Properties prop) 
          {
              this.driver = driver;
              this.prop = prop;
          }
    	  
		public void selectGoalCycle(String cycleName) throws InterruptedException 
          {
              Thread.sleep(5000);

              try {
                  String Statuss = driver.findElement(By.xpath("//span[contains(text(),'Appraisal Initiated')]")).getText();
                  Statuss.equals("Appraisal Initiated");

                  driver.findElement(dropdownToggle).click();
                  driver.findElement(By.xpath("//a[contains(text(),'" + cycleName + "')]")).click();
                  driver.findElement(By.xpath("//span[@class=\"ml-4\"]")).click();

                  //  FOR Clicking on Drop down
                  WebElement element = driver.findElement(By.xpath("(//div[@class=\"card list-view-card\"])[1]"));
                  // Use JavaScriptExecutor to click on the element
                  JavascriptExecutor executor = (JavascriptExecutor) driver;
                  executor.executeScript("arguments[0].click();", element);
                  Thread.sleep(1000);


//              WebElement listContainer  = driver.findElement(By.id("categ105"));

                  WebElement listContainer = driver.findElement(By.xpath("(//div[@class=\"slider-box\"]//input[@type=\"range\"])[3]"));

                  List<WebElement> noofGoals = listContainer.findElements(By.xpath("//ul[@class=\"flex flex-btn py-1 onhover-section ng-scope\"]"));
                  int NoofGoal = noofGoals.size();
                  System.out.println(NoofGoal);
                  for (int i = 1; i <= NoofGoal; i++) {
                      Random random = new Random();
                      int randomNumber = random.nextInt(100) + 1;
                      System.out.println("Random number between 1 and 100: " + randomNumber);

                      updateProgressBar(driver, "(//input[@type=\"range\"])[1]", randomNumber);
                      Thread.sleep(1000);
                      String Employee_cmt = "EmpCmt";
                      String Employee_comment = Employee_cmt + i;
                      EmployeeComment(driver, "(//div[@class=\"dropdown\"])[2]", Employee_comment, "(//a[@class=\"view-link mt-h cs-modal-btn ng-binding ng-scope\"]) [(" + i + ")]");
                      Thread.sleep(1000);

                      // Update the progress for overall and give comment
//                  updateProgressBar(driver, "(//input[@type=\"range\"])[4]", randomNumber);
//                  Thread.sleep(1000);
                      driver.findElement(By.id("employeComment")).sendKeys("Overall Employee CMT");
                  }


                  WebElement Submit = driver.findElement(By.xpath("//a[@ng-click=\"submitSelfPerformance(1)\"]"));

                  JavascriptExecutor Srollup = (JavascriptExecutor) driver;
                  Srollup.executeScript("arguments[0].scrollIntoView(true);", Submit);

                  Submit.click();
                  Thread.sleep(500);
                  driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
                  Thread.sleep(500);

              }
              finally {
                  System.out.println("Assessment completed");
              }

          }
    	  private static void updateProgressBar(WebDriver driver, String cssSelector, int progress) 
    	  {  
	    	  WebElement rangeInput = driver.findElement(By.xpath(cssSelector)); 
            // Cast WebDriver to JavascriptExecutor
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            // Update the value of the range input and trigger the change event
            String script = "arguments[0].value = arguments[1];" +
                            "arguments[0].dispatchEvent(new Event('change'));";
            jsExecutor.executeScript(script, rangeInput, progress);
            // Optionally, update the progress text (if needed)
            WebElement progressText = driver.findElement(By.cssSelector(".risk"));
            jsExecutor.executeScript("arguments[0].innerText = arguments[1];", progressText, String.format("%d%% Complete", progress));

	      }
    	  private static void EmployeeComment(WebDriver driver, String cssSelector, String Empcmt, String ReviewBTN) throws InterruptedException 
    	  {
    		  driver.findElement(By.xpath(cssSelector)).click();
    		  Thread.sleep(500);
    		  driver.findElement(By.xpath(ReviewBTN)).click();
    		  Thread.sleep(500);
    		  String goal_comments1 = "goal_comments1";    	
    		  driver.findElement(By.id(goal_comments1)).sendKeys(Empcmt);

    		  driver.findElement(By.xpath("//*[@id=\"nggDiv\"]/div[16]/div/div[2]/form[1]/div[4]/div[1]/button[2]")).click();
 
    		  driver.findElement(By.xpath("//*[@id=\"nggDiv\"]/div[16]/div/div[2]/form[1]/div[4]/div[1]/button[1]")).click();
    	  }
    	  public String isSelfsub() throws InterruptedException {

                Thread.sleep(4000);
              WebElement element = driver.findElement(By.xpath("(//div[@class=\"card list-view-card\"])[1]"));
              // Use JavaScriptExecutor to click on the element
              JavascriptExecutor executor = (JavascriptExecutor) driver;
              executor.executeScript("arguments[0].click();", element);


              By Isvisible = (By.xpath("//span[contains(text(),'Self Review Completed')]"));
              WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
              WebElement Exp= wait.until(ExpectedConditions.visibilityOfElementLocated(Isvisible));

    		 return Exp.getText();
    	  }
    }
