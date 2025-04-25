package normalFlowTest;

import ConfigReder.ConfigpropReader;
import Factory.DriverFactory;
import NormalFlowForEmployee.CloudAdmin_BaseClass;
import NormalFlowForEmployee.Delete_Goal_Plan_and_PMS_Cycle;
import NormalFlowForEmployee.Delete_the_PMS_Cycle;
import NormalFlowForEmployee.addGoalPlan;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class CloudAdmin_Test {

    WebDriver driver;
    addGoalPlan addGoalPlan;
    DriverFactory df;
    ConfigpropReader cp;
    Properties prop;
    CloudAdmin_BaseClass CloudAdmin_BaseClass;



    @BeforeTest
    void setUp() throws IOException
    {
        cp = new ConfigpropReader();
        prop = cp.initLangProp("NormalFlowTest");
        df = new DriverFactory();
        driver = df.initDriver("chrome", prop);

        driver.get(prop.getProperty("url"));
        addGoalPlan = new addGoalPlan(driver);
        addGoalPlan.login(prop.getProperty("CloudAdminUN"), prop.getProperty("CloudAdminPass"));
        CloudAdmin_BaseClass = new CloudAdmin_BaseClass(driver, prop);
    }

    @Test
    public void EnableIPPBConfigs() throws InterruptedException {

        CloudAdmin_BaseClass.EnableIPPBConfigs();
    }


    @AfterTest
    void teardown(){
        driver.quit();
    }
}
