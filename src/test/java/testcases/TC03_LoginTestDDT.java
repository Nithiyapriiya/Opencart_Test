package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.Homepage;
import pageObject.Loginpage;
import pageObject.MyAccountPage;
import testbase.Testbase;
import utilities.DataProviders;

public class TC03_LoginTestDDT extends Testbase {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "TestDDT")
	public void loginddt(String email, String pwd, String exp)
	{
		Logger.info("***Starting TC03***");
		
		try {
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		Loginpage lp =new Loginpage(driver);
		lp.setemail(email);
		lp.setpassword(pwd);
		lp.clicklogin();
		
		MyAccountPage mp = new MyAccountPage(driver);
		boolean trgpage = mp.accconfirmation();
		Assert.assertTrue(trgpage);
		/*
		Data is valid - login success - pass
					  - login failed  - fail
					  
		Data is invalid - login sucess - fail
						-login failed - pass
		*/
		if(exp.equals("Valid"))
		{
			if(trgpage==true)
			{
				mp.clklogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}if(exp.equals("Invalid"))
		{
			if(trgpage==true)
			{
				mp.clklogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
	}
		catch(Exception e)
		{
			Assert.fail();
		}

}
}
