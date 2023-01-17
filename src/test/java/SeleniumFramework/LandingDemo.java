package SeleniumFramework;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.ReusableTest.BaseTest;
import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.Confirmationpage;
import SeleniumFramework.pageobject.LandingPage;
import SeleniumFramework.pageobject.OrderPage;
import SeleniumFramework.pageobject.PaymentPage;
import SeleniumFramework.pageobject.ProductCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LandingDemo extends BaseTest  {
	
	
	
	//String productname = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData",groups={"purchase"})
	
	public void Submitpurchase(HashMap<String,String> hashinput) throws IOException {
	//data provider	public void Submitpurchase(String mailid,String password) throws IOException {
		
		String confirmessage="THANKYOU FOR THE ORDER.";
		ProductCataloguePage prodpage=landingob.input(hashinput.get("Email"),hashinput.get("password"));
		List<WebElement> products=prodpage.getproductlist();
		prodpage.getproductbyname(hashinput.get("product"));
		prodpage.producttobeclicked(hashinput.get("product"));
		CartPage cartob=prodpage.gotoCart();		 
		List<WebElement> cartproducts=cartob.getCartproducts();
		boolean match=cartob.getproductincart(hashinput.get("product"));		
			Assert.assertTrue(match);	
			PaymentPage payob=cartob.gotocheckout();		
		payob.addshippingdetail("India");
		Confirmationpage confirm=payob.placeorder();		
		String displaymsg=confirm.gotoorderconfirm(confirmessage);
		Assert.assertTrue(displaymsg.equalsIgnoreCase(confirmessage));
				
			}
	@Test(dependsOnMethods ="Submitpurchase" )
	public void Ordersubmit() {
		ProductCataloguePage prodpage=landingob.input("hai@gmail.com","Hello@123");
		OrderPage orderob=new OrderPage(driver);
		orderob.gotoOrders();
		boolean prodmatch=orderob.getOrders("ADIDAS ORIGINAL");
		Assert.assertTrue(prodmatch);
}
	//********With DataProvider jason
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumFramework//Data//purchaseorder.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
}
	/*
@DataProvider
public Object[][] getData() {
	return new Object[][] {{"hai@gmail.com","Hello@123"},{"nair@yahoo.in","Nair@123"}};*/
	
	//******with Hashmap
	/*@DataProvider
	public Object getData(){
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "hai@gmail.com");
		map.put("password", "Hello@123");
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "nair@yahoo.in");
		map1.put("password", "Nair@123");			
		return new Object[][]{{map},{map1}};}*/
	
}
