package SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusablecomponent.baseclass;

public class OrderPage extends baseclass{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
	}
	@FindBy(xpath="//table/tbody/tr/td[2]")
	List<WebElement> Orderproducts;
	
	public boolean getOrders(String productname) {
		boolean productmatch=Orderproducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productname));
		return productmatch;
		
	}
}
