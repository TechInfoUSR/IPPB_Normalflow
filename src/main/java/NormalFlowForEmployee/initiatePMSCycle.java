package NormalFlowForEmployee;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class initiatePMSCycle 
{

	    private WebDriver driver;
	    private Properties prop;

	    // Locators for elements on the PMSCycleDetailsPage
	    private By tabWeightTab = By.xpath("//a[@data-toggle='tab' and @href='#sectionWeightTab']");
	    private By addNewRowLink = By.xpath("//a[@ng-click=\"addNewRow = true\"]");
	    private By selectGroupDropdown = By.xpath("//span[@id=\"select2-chosen-2\"]");
	    private By groupSearchField = By.xpath("//input[@id=\"s2id_autogen2_search\"]");
	    private By objectiveWeightageField = By.xpath("//input[@ng-model=\"newEligibleGroup.objectiveWeightage\"]");
	    private By coreValueWeightageField = By.xpath("//input[@ng-model=\"newEligibleGroup.coreValueWeightage\"]");
	    private By jobCompetencyWeightageField = By.xpath("//input[@ng-model=\"newEligibleGroup.jobCompetencyWeightage\"]");
	    private By behaviorWeightageField = By.xpath("//input[@ng-model=\"newEligibleGroup.behaviorWeightage\"]");
	    private By leadershipWeightageField = By.xpath("//input[@ng-model=\"newEligibleGroup.leadershipWeightage\"]");
	    private By insertButton = By.xpath("//a[contains(text(),'Insert')]");
	    private By initiateReviewCycleButton = By.xpath("//*[@class='btn btn-default btn-xs' and @ng-click='initiateReviewCycleForGroup(eg.weightageGroupId)']");
	    private By okButton = By.xpath("//button[contains(text(),'OK')]");

	    public initiatePMSCycle(WebDriver driver, Properties prop) 
	    {
	        this.driver = driver;
	        this.prop = prop;
	    }

	    
	    public void goToWeightTab(String group, String AddPMSCyle) throws InterruptedException 
	    {
	    	driver.findElement(By.xpath("//span[contains(text(),'"+AddPMSCyle+"')]")).click();
	        driver.findElement(tabWeightTab).click();
	        driver.findElement(addNewRowLink).click();
	        driver.findElement(selectGroupDropdown).click();
	        driver.findElement(groupSearchField).sendKeys(group);
	        driver.findElement(By.xpath("//div[contains(text(),'"+group+"')]")).click();

	    }
//	    public void enterWeightages(String objective, String coreValue, String jobCompetency, String behavior, String leadership) throws InterruptedException 
	    public void enterWeightages() throws InterruptedException
	    {
	        driver.findElement(objectiveWeightageField).sendKeys("100");
	        driver.findElement(coreValueWeightageField).sendKeys("0");
	        driver.findElement(jobCompetencyWeightageField).sendKeys("0");
	        driver.findElement(behaviorWeightageField).sendKeys("0");
	        driver.findElement(leadershipWeightageField).sendKeys("0");
	        driver.findElement(insertButton).click();
	        driver.findElement(initiateReviewCycleButton).click();
	        driver.findElement(okButton).click();      
	    }


	public void EnableConfigs() throws InterruptedException
	{
		driver.findElement(By.xpath("//img[@src=\"asssets/media/images/cute-clipart/30/000000/ruler.png\"]")).click();

		driver.findElement(By.xpath("(//input[@name=\"flowConfigShowType\"])[2]")).click();

		driver.findElement(By.xpath("//*[@id=\"flow_config_form\"]/table/tbody/tr[23]/td/button")).click();

		WebElement Overall=driver.findElement(By.id("toggle_overallempmgrcomments"));
		if(Overall.isSelected()){
			System.out.println("Already enabled");
		}
		else {
			Overall.click();
			driver.findElement(By.id("employee_overall_comments")).click();
			driver.findElement(By.id("manager_overall_comments")).click();
			System.out.println("Now enabled");
		}

		driver.findElement(By.id("save_flow_config")).click();
		Thread.sleep(3000);
	}





	//Not working due to a minor bug
	    public boolean isCycleInitiated() throws InterruptedException 
	    {
	    	return driver.findElement(By.xpath("//tr[@class=\"ng-scope\"]")).isDisplayed();		       
	    }
	    
	}
