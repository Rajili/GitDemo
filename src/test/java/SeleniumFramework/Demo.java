package SeleniumFramework;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {

	public static void main(String[] args) {
		String productname = "ADIDAS ORIGINAL";
		// email hai@gmail.com pwd Hello@123
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("hai@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hello@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//page to load products and store products in cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> cart = driver.findElements(By.cssSelector(".mb-3"));
		//get the nmae nmae of carts and check for particular product and click
		WebElement productselect = cart.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		productselect.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();
		// check for screen which appears after add to cart click(it shows for fraction of second)

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		// spinning should be over
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("ngx-spinner")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.tagName("ngx-spinner"))));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		List<WebElement> cartproduct = driver.findElements(By.cssSelector(".cart h3"));
		boolean match = cartproduct.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		//CHECKOUT BUTTON
		driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		//List<WebElement> country=dropdown.findElements(By.cssSelector(".ng-sta
		Actions act=new Actions(driver);
		act.sendKeys(dropdown, "India").build().perform();
		driver.findElement(By.xpath("(//button[contains(@class,'list-group-item')])[2]")).click();
		
		driver.findElement(By.xpath("//a[contains(@class,'submit')]")).click();
		String confirm=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
		
		

	}

}
