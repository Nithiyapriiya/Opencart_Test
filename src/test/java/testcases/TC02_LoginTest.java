package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.Homepage;
import pageObject.Loginpage;
import pageObject.MyAccountPage;
import testbase.Testbase;


public class TC02_LoginTest extends Testbase {
	
	@Test(groups={"Regression","Master"})
	public void login()
	{
		Logger.info("---TC02 Test started Login Page----");
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		Loginpage lp =new Loginpage(driver);
		lp.setemail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clicklogin();
		
		MyAccountPage mp = new MyAccountPage(driver);
		boolean trgpage = mp.accconfirmation();
		Assert.assertTrue(trgpage);
		
	}
	

}
