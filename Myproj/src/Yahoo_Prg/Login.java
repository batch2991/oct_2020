package Yahoo_Prg;


import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import Yahoo_prop.LoginP;

public class Login extends MainClass
{
 private void open() 
 {	 
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.get(LoginP.yurl);	
 }
 public void valid_login() throws Exception
 {
	open();
	Thread.sleep(5000);
	driver.findElement(By.name(LoginP.nemail)).sendKeys("venkat123456a");
	driver.findElement(By.name(LoginP.nnext)).click();
	driver.findElement(By.name(LoginP.npwd)).sendKeys("mqq987654");
	driver.findElement(By.name(LoginP.nnext2)).click();
 }
 public void validate_login() throws Exception
 {
	    String err;
		
		FileInputStream fin=new FileInputStream("d:\\sel_dec18\\data.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fin);
		XSSFSheet ws=wb.getSheet("Sheet2");  
		
		Row row;
		for(int r=1;r<=ws.getLastRowNum();r++) //for all the rows in the sheet
		{
			row=ws.getRow(r);
			open();
			Thread.sleep(2000);
			driver.findElement(By.name(LoginP.nemail)).sendKeys(row.getCell(0).getStringCellValue());
			driver.findElement(By.name(LoginP.nnext)).click();
			Thread.sleep(5000);
			driver.findElement(By.name(LoginP.npwd)).sendKeys(row.getCell(1).getStringCellValue());
			driver.findElement(By.name(LoginP.nnext2)).click();
			Thread.sleep(5000);
			
			try
			{
				if(driver.findElement(By.linkText(LoginP.lsignout)).isDisplayed())
				{
					row.createCell(2).setCellValue("Login is success");
					driver.findElement(By.linkText(LoginP.lsignout)).click();
				}
			}
			catch(Exception e)
			{
				err=driver.findElement(By.xpath(LoginP.xerrmsg)).getText();
				row.createCell(2).setCellValue("Login is failed  :  "+err);			
			}			
		}
		FileOutputStream fout=new FileOutputStream("d:\\sel_dec18\\data.xlsx");
		wb.write(fout);
		fin.close();
		fout.close(); 
 }
 public void signup() throws Exception
 {
	 open();
	 Thread.sleep(5000);
	 driver.findElement(By.id(LoginP.isignup)).click();
	 try
	 {
		 if(driver.findElement(By.name(LoginP.nfname)).isDisplayed())
		 {
			testlog=report.createTest("Login_Signup");
			testlog.log(Status.PASS, "Create account is working");
			File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	//take screenshot of the browser
			FileUtils.copyFile(f, new File("d:\\oct_2020\\images\\signup.png"));	//save the file in folder
			testlog.addScreenCaptureFromPath("d:\\oct_2020\\images\\signup.png");	//add the image to the report
		 }
	 }
	 catch(Exception e)
	 {
		 	testlog=report.createTest("Login_Signup");
			testlog.log(Status.FAIL, "Create account NOT working");
			File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	//take screenshot of the browser
			FileUtils.copyFile(f, new File("d:\\oct_2020\\images\\signup.png"));	//save the file in folder
			testlog.addScreenCaptureFromPath("d:\\oct_2020\\images\\signup.png");	//add the image to the report
	 }
 }
}
