package ConfigReder;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClasses {

    protected WebDriver driver;
    protected Properties prop;

    public void initializeProperties() throws IOException {
        prop = new Properties();
        FileInputStream ip = new FileInputStream("./properties/NormalFlowTest.properties");
        prop.load(ip);
    }

    public void initializeDriver() {
        driver = new ChromeDriver();
        driver.get(prop.getProperty("url"));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
	
	
}