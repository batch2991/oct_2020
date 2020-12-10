package Yahoo_Prg;

import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import Yahoo_prop.ComposeP;
import Yahoo_prop.LoginP;

public class Compose extends MainClass
{
 public void sendmail() throws Exception
 {
	 Thread.sleep(5000);
	 driver.findElement(By.xpath(ComposeP.xcompose)).click();
	 Thread.sleep(5000);
	 try
	 {
		 if(driver.findElement(By.name(ComposeP.ito)).isDisplayed())
		 {
			 	testlog=report.createTest("Compose_sendmail");
				testlog.log(Status.PASS, "send mail is working");
				File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	//take screenshot of the browser
				FileUtils.copyFile(f, new File("d:\\oct_2020\\images\\compose.png"));	//save the file in folder
				testlog.addScreenCaptureFromPath("d:\\oct_2020\\images\\compose.png");	//add the image to the report
				
			 driver.findElement(By.id(ComposeP.ito)).sendKeys("abcd@gmail.com");
			 driver.findElement(By.id(ComposeP.isub)).sendKeys("testmail");
			 driver.findElement(By.id(ComposeP.ibody)).sendKeys("This is sample message in the body");
			 driver.findElement(By.xpath(ComposeP.xsend)).click();
			 Thread.sleep(5000);
			 System.out.println("hello");
		 }
	 }
	 catch(Exception e)
	 {
		 	testlog=report.createTest("Compose_sendmail");
			testlog.log(Status.FAIL, "send mail NOT working");
			File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	//take screenshot of the browser
			FileUtils.copyFile(f, new File("d:\\oct_2020\\images\\compose.png"));	//save the file in folder
			testlog.addScreenCaptureFromPath("d:\\oct_2020\\images\\compose.png");	//add the image to the report
	 }	 
 }
 public void close()
 {
	 driver.findElement(By.linkText(LoginP.lsignout)).click();
	 driver.close();
 }
}
