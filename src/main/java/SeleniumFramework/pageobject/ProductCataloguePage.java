package SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Reusablecomponent.baseclass;
public class ProductCataloguePage extends baseclass {
	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(tagName="ngx-spinner")
	WebElement spinner;
	By productBy = By.cssSelector(".mb-3");
	By AddtocartBy = By.cssSelector(".mb-3 button:last-of-type");
	By tagBy = By.tagName("b");
	By toastBy=By.id("toast-container");

	public List<WebElement> getproductlist() {
		waitforelementtoappear(productBy);
		return products;
	}
	public WebElement getproductbyname(String productname) {
		WebElement productselect = getproductlist().stream()
				.filter(product -> product.findElement(tagBy).getText().equals(productname)).findFirst().orElse(null);
		return productselect;
		// productselect.findElement(AddtocartBy).click();
	}
	public void producttobeclicked(String productname) {
		WebElement prod = getproductbyname(productname);
		prod.findElement(AddtocartBy).click();
		waitforelementtoappear(toastBy);
		waitforelementtodisappear(spinner);
		
		
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.tagName("ngx-spinner"))));
	}
}
