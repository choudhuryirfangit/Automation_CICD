package IrfanChoudhury.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import IrfanChoudhury.SeleniumFrameWorkDesign.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestDriverInvoke {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\IrfanChoudhury\\resources\\GlobalData.properties");
		prop.load(fis);
		
//		String browserName=prop.getProperty("browser");
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser"); 
//		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else {
			System.out.println("driver error");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {  
//		Jscon to String
		String JsonContent= FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
//		String to HashMap- Json databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() { 
		} );
		return data;
		
	}
	public String getScreenshot(String testCasename, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports"+ testCasename+ ".png"); 
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports"+ testCasename+ ".png";
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
	

}
