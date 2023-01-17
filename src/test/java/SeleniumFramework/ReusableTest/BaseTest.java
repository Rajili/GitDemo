package SeleniumFramework.ReusableTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseTest {
	public WebDriver driver;
	public LandingPage landingob;
	public  WebDriver initializedriver() throws IOException {
		//properties class		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//SeleniumFramework//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");	
		//String browserName=System.getProperty("browser");
		System.out.print(browserName);
		
		//String browser=prop.getProperty("browser");		
	if(browserName.equalsIgnoreCase("chrome")) {		
	WebDriverManager.chromedriver().setup();
	 driver = new ChromeDriver();
	 }
	else if(browserName.equalsIgnoreCase("firefox")) {
		 System.setProperty("webdriver.gecko.driver", "C:\\Users\\Shiva\\Desktop\\driver\\geckodriver.exe");
		 driver=new FirefoxDriver();
		 
	 }
	 else if(browserName.equalsIgnoreCase("edge")){
		 System.setProperty("webdriver.edge.driver", "edge.exe");
		 driver=new EdgeDriver();
		 
	 }
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	return driver;
	}@BeforeMethod
	public LandingPage launchapplication() throws IOException
	{
		driver=initializedriver();
		landingob=new LandingPage(driver);
		landingob.goTo();
		return landingob;
	}@AfterMethod
	public void teardown() {
		
		driver.close();
		
	}
		public List<HashMap<String, String>> getJsonDataToMap(String Systempath) throws IOException {
		//read JsonDataToString
		String JsonContent=FileUtils.readFileToString(new File(Systempath),StandardCharsets.UTF_8);
	//String to Hashmap Jackson Datbind
				ObjectMapper mapper= new ObjectMapper();
				List<HashMap<String,String>> data=mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
					
				});
				return data;
	}
		public String getScreenshot(String testcasename, WebDriver driver2) throws IOException {
			
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
		}
}
