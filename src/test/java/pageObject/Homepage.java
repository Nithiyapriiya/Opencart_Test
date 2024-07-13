package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage {

	WebDriver driver;
	
	public Homepage (WebDriver driver)
	{
		super(driver);
		}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkmyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkregister;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnklogin;
	
	
	public void clickMyAccount() {
		lnkmyaccount.click();
	}
	public void clickregister()
	{
		lnkregister.click();
	}
	public void clicklogin()
	{
		lnklogin.click();
	}
	}

