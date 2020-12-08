package Yahoo_Prg;


import org.openqa.selenium.WebDriver;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class MainClass 
{
  public static WebDriver driver;  
  
  public static ExtentHtmlReporter exthtml;
  public static ExtentReports report;
  public static ExtentTest testlog;
  
  @BeforeSuite
  public void reportinitialise()
  {
	   exthtml = new ExtentHtmlReporter("d:\\oct_2020\\yahooreport.html");
	   report = new ExtentReports();
	   report.attachReporter(exthtml);
	   report.setSystemInfo("Host Name", "Venkatgn");  //name of thesystem
	   report.setSystemInfo("Environment", "Test Env");
	   report.setSystemInfo("User Name", "Venkat");
	   
	   exthtml.config().setDocumentTitle("Yahoo");
	   exthtml.config().setReportName("Yahoo Functional Testing");
	   exthtml.config().setTestViewChartLocation(ChartLocation.TOP);
	   exthtml.config().setTheme(Theme.STANDARD);
  }
  
  @AfterSuite
  public void endofsuite()
  {
	  report.flush();//save the report
  }
  
  
}
