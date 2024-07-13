package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Basepage {
	
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath ="//h2[normalize-space()='My Account']") WebElement msgconf;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement clklogout;
	
	public boolean accconfirmation() {
		
		try {
			return(msgconf.isDisplayed());
		}
		
		catch(Exception e)
		{
			return false;
		}
	}

	public void clklogout() {
		clklogout.click();
		}
}
