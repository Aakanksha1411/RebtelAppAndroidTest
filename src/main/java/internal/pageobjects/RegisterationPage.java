package internal.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegisterationPage {
	AndroidDriver driver;

	public RegisterationPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.rebtel.android:id/getStartedButton")
	private WebElement getstarted;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private WebElement allowbutton;

	@AndroidFindBy(id = "com.rebtel.android:id/inputPhoneNumber")
	private WebElement phonenumber;

	@AndroidFindBy(id = "com.rebtel.android:id/verifyButton")
	private WebElement verifyphone;

	public void getstartedbutton() {
		getstarted.click();
	}

	public void allowbutton() {
		allowbutton.click();
		allowbutton.click();
		allowbutton.click();
	}

	public String enterphonenumber(String countryphonenumber) {
		phonenumber.clear();
		phonenumber.sendKeys(countryphonenumber);
		return countryphonenumber;
	}

	public void setcountry(String text) {
		driver.findElement(By.id("com.rebtel.android:id/inputPhoneNumber")).sendKeys(Keys.TAB);
		driver.findElement(By.id("com.rebtel.android:id/countryFlag")).click();
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"))
				.click();

	}

	public void verifyphonenumber() {
		verifyphone.click();
	}
}
