package NormalFlowForEmployee;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class CloudAdmin_BaseClass {


    WebDriver driver;
    Properties prop;
    public CloudAdmin_BaseClass(WebDriver drive, Properties pro){
        prop=pro;
        driver=drive;
    }
 public void EnableIPPBConfigs() throws InterruptedException {
     driver.findElement(By.xpath("//h4[contains(text(),'Cloud/SaaS Licence Management')]")).click();
    Thread.sleep(2000);

     WebElement element = driver.findElement(By.xpath("//div[contains(text(),'12')]"));
     JavascriptExecutor js = (JavascriptExecutor) driver;
     js.executeScript("window.scrollBy(0,1000)"); // Scroll down by 500 pixels


     driver.findElement(By.xpath("//*[@id=\"companylist\"]/tbody/tr[8]/td[7]/a[3]")).click();
     Thread.sleep(2000);

     js.executeScript("window.scrollBy(0,500)"); // Scroll down by 500 pixels

     driver.findElement(By.xpath("//button[contains(text(),'Settings')]")).click();
     Thread.sleep(2000);
     driver.findElement(By.xpath("//li//a[contains(text(),'Custom Settings')]")).click();
     Thread.sleep(2000);

//     Selecting configration



     WebElement authorizePms = driver.findElement(By.xpath("//input[@id=\"authorizePms\"]"));

     if (authorizePms.isSelected()) {
         System.out.println("Checkbox is selected");
     } else {
         System.out.println("Checkbox is not selected");
         authorizePms.click();
     }



     WebElement PMSReport = driver.findElement(By.xpath("//input[@id=\"PMSReport\"]"));

     if (PMSReport.isSelected()) {
         System.out.println("Checkbox is selected");
     } else {
         System.out.println("Checkbox is not selected");
         PMSReport.click();
     }

     Thread.sleep(4000);

     driver.findElement(By.xpath("(//button[@class=\"btn btn-primary\"])[2]")).click();
     Thread.sleep(7000);

 }

}
