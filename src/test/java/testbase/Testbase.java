package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

//import io.github.bonigarcia.wdm.WebDriverManager;


public class Testbase {
	
public static WebDriver driver;

public Logger Logger;
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master", "TestDDT"})
	@Parameters({"OS","Browser"}) 
	public void setup(String os, String Br) throws IOException 
	{
		Logger = LogManager.getLogger(this.getClass());
		
		FileReader file = new FileReader("./src//test//resources//config.propertis"); ///Users/nithiya/eclipse-workspace/opencart/src/test/resources/config.propertis
		p=new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {			
		DesiredCapabilities Capabilities = new DesiredCapabilities();
		
		if(os.equalsIgnoreCase("mac")) //OS 
		{
			Capabilities.setPlatform(Platform.MAC);
		}
		else if(os.equalsIgnoreCase("windows"))
		{
			Capabilities.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("Linux"))
		{
			Capabilities.setPlatform(Platform.LINUX);
		}
		else {
			System.out.println("No Matching browser");
			return;
		}
		//Browser
		
		switch(Br)
		{
		case "Chrome" : Capabilities.setBrowserName("chrome"); break;
		case "Edge" :  Capabilities.setBrowserName("MicrosoftEdge") ; break;
		case "Safari" :   Capabilities.setBrowserName("safari"); break;
		default : System.out.println("Invalid browser"); return;
		
		}
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),Capabilities);		
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
		
		switch(Br)
		{
		case "Chrome" : driver=new ChromeDriver(); break;
		case "Edge" :  driver=new EdgeDriver(); break;
		case "Safari" :  driver=new SafariDriver(); break;
		default : System.out.println("Invalid browser"); return;
		
		}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appurl"));
		driver.manage().deleteAllCookies();
		
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master","TestDDT"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomstring() {
		
		String random = RandomStringUtils.randomAlphabetic(5);
		return random;
		
	}
	
	public String randomnum()
	{
		String randomnum = RandomStringUtils.randomNumeric(10);
		return randomnum;
	}
	
	public String randompwd()
	{
		String randompwd = RandomStringUtils.randomAlphabetic(8);
		return randompwd;
		
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath="/Users/nithiya/eclipse-workspace/opencart/Screenshots/" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}


}
