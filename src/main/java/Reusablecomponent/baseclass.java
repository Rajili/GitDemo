package Reusablecomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.OrderPage;

public class baseclass {

	WebDriver driver;

	public baseclass(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[routerlink='/dashboard/cart']")
	WebElement cartbuttn;
	@FindBy(css = "button[routerlink='/dashboard/myorders']")
	WebElement orderbuttn;


	public void waitforelementtoappear(By Findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	public void waitforwebelementtoappear(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitforelementtodisappear(WebElement spinner) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}
	
	public CartPage gotoCart() {
		cartbuttn.click();
		CartPage cartob=new CartPage(driver); 
		return cartob;
	}
	public OrderPage gotoOrders() {
		orderbuttn.click();
		OrderPage orderob=new OrderPage(driver); 
		return orderob;
	
}}


