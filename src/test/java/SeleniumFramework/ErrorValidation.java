package SeleniumFramework;

import org.testng.annotations.Test;

import Reusablecomponent.baseclass;
import SeleniumFramework.ReusableTest.BaseTest;
import junit.framework.Assert;

public class ErrorValidation extends BaseTest{
	@Test
	public void loginErrorValidation() {
		landingob.input("hai@gmail.com","hai@123");
		//System.out.println("haii  ");
		
		Assert.assertEquals("Incorrect email / password.", landingob.errormessage());
	}

}
