package SeleniumFramework.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusablecomponent.baseclass;

public class PaymentPage extends baseclass{
WebDriver driver;
@FindBy(xpath="//input[@placeholder='Select Country']")
WebElement countrydropdown;
@FindBy(xpath="(//button[contains(@class,'list-group-item')])[2]")
WebElement dropdownselect;
@FindBy(xpath="//a[contains(@class,'submit')]")
WebElement orderbttn;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void addshippingdetail(String country) {
		
		Actions act=new Actions(driver);
		act.sendKeys(countrydropdown, country).build().perform();
		dropdownselect.click();
	}
	public Confirmationpage placeorder() {
		orderbttn.click();
		Confirmationpage confirm=new Confirmationpage(driver);
		return confirm;
	}

}
