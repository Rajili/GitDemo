package SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusablecomponent.baseclass;

public class CartPage extends baseclass {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cart h3")
	List<WebElement> cartproducts;
	@FindBy(css = ".totalRow button")
	WebElement checkoutbttn;

	public List<WebElement> getCartproducts() {
		return cartproducts;
	}

	public boolean getproductincart(String productname) {
		boolean match = getCartproducts().stream().anyMatch(p -> p.getText().equalsIgnoreCase(productname));
		return match;
	}

	public PaymentPage gotocheckout() {
		checkoutbttn.click();
		PaymentPage payob=new PaymentPage(driver);
		return payob;
	}

}
