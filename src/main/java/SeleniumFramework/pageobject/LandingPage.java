package SeleniumFramework.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusablecomponent.baseclass;

public class LandingPage extends baseclass{
	
WebDriver driver;
public LandingPage(WebDriver driver2) {
	super(driver2);
	this.driver=driver2;
	PageFactory.initElements(driver, this);
}
	
@FindBy (id="userEmail")
WebElement userEmail;
@FindBy (id="userPassword")
WebElement userPassword;
@FindBy (id="login")
WebElement login;

@FindBy (css="[class*='flyInOut']")
WebElement errormsg;
By spinner=By.cssSelector(".mb-3");



	

	public ProductCataloguePage input(String Email,String password) {
		userEmail.sendKeys(Email);
		userPassword.sendKeys(password);
		login.click();
		ProductCataloguePage prodpage=new ProductCataloguePage(driver);
		return prodpage;
			}
	public String errormessage() {
		waitforwebelementtoappear(errormsg);
		return errormsg.getText();
	}

	public void goTo() {
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
	}
	
	
		
		
		
		
		
	

}
