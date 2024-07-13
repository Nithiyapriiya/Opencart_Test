package testcases;

import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.Homepage;
import pageObject.Registrationpage;
import testbase.Testbase;

public class TC01_Accountregistrationtest extends Testbase {
	
	
	@Test(groups= {"Sanity","Master"})
	public void verify_account_details()
	{
		try {
		Logger.info("Starting TC01");
		
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clickregister();
		Logger.info("Clicked on my account and Register");
		
		Registrationpage rp = new Registrationpage(driver);
		rp.setFirstName("aram");
		rp.setLastName("mara");
		Logger.info("Firstname and Lastname given");
		rp.setEmail(randomstring()+"@gmail.com");
		rp.setTelephone(randomnum());
		Logger.info("Email address and phone number given");
		String pws = randompwd();
		rp.setPassword(pws);
		rp.confirmPassword(pws);
		Logger.info("Password entered");
		rp.setPrivacyPolicy();	
		Logger.info("Checked the policy");
		rp.clickContinue();
		Logger.info("clicked on continue");
		
		String confmsg=rp.getConfirmationMsg();
		AssertJUnit.assertEquals(confmsg, "Your Account Has Been Created!");
		Logger.info("Stopped TC01");
	}
		catch(Exception e)
		{
			Logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
			}
		
		}
}

