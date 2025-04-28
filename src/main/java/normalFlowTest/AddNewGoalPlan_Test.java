package normalFlowTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ConfigReder.BaseClasses;
import NormalFlowForEmployee.AddNewGoalPlan;
import NormalFlowForEmployee.PMSCyclePage;
import NormalFlowForEmployee.emp_assesment_Submission;
import NormalFlowForEmployee.initiatePMSCycle;
import NormalFlowForEmployee.Manager_AddGoals;
import Utils.UniformClasess;

public class AddNewGoalPlan_Test extends BaseClasses{
	AddNewGoalPlan addNewGoalPlan;
	PMSCyclePage PMSCyclePage;
	initiatePMSCycle initiatePMSCycle;
	Manager_AddGoals manager_AddGoals;
	emp_assesment_Submission emp_assesment_Submission;
	UniformClasess UniformClasess;


	@BeforeTest
	void setUp() throws IOException {
		initializeProperties();
		initializeDriver();

		addNewGoalPlan = new AddNewGoalPlan(driver);
		PMSCyclePage = new PMSCyclePage(driver, prop);
		initiatePMSCycle = new initiatePMSCycle(driver, prop);
		manager_AddGoals = new Manager_AddGoals(driver, prop);
		emp_assesment_Submission = new emp_assesment_Submission(driver, prop);
		UniformClasess =new UniformClasess(driver,prop);
		UniformClasess.login(prop.getProperty("HrUsername"), prop.getProperty("HrPassword"));

	}

	@Test(priority = 1)
	public void AddGoalPlan() throws InterruptedException {
		String GoalPalnName = prop.getProperty("GoalPalnName");
		String EmpGroup = prop.getProperty("EmpGroup");
		String RatingScale = prop.getProperty("RatingScale");
		addNewGoalPlan.addGoalPlan1(GoalPalnName, EmpGroup, RatingScale);
		String isDisplayed = addNewGoalPlan.isGoalPlanDisplayed(GoalPalnName);
		assertEquals(GoalPalnName, isDisplayed, "Goal Plan is not displayed!!");
	}

	@Test(priority = 2)
	public void AddPMSCycle() throws InterruptedException {
		String GoalPalnName = prop.getProperty("GoalPalnName");
		PMSCyclePage.addPMSCycle(prop.getProperty("GoalPalnName"));
		String isDisplayed = PMSCyclePage.isPMSCycleDisplayed();
		assertEquals(GoalPalnName, isDisplayed, "PMS Cycle is not displayed!!!");
	}

	@Test(priority = 3)
	public void Initiate_PMSCycle() throws InterruptedException {
		initiatePMSCycle.goToWeightTab(prop.getProperty("EmpGroup"), prop.getProperty("GoalPalnName"));
		initiatePMSCycle.enterWeightages();
		boolean isDisplayed = initiatePMSCycle.isCycleInitiated();
		System.out.println(isDisplayed);
		assertTrue(isDisplayed, "Goal plan didn't initiated");
	}

	@AfterTest
	void teardown() {
		driver.quit();
	}
}
