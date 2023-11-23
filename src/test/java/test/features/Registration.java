package test.features;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import internal.pageobjects.RegisterationPage;
import test.base.BaseTest;

public class Registration extends BaseTest {

	@Test
	public void RegisterUser()throws MalformedURLException {

		RegisterationPage start = new RegisterationPage(driver);
		start.getstartedbutton();
		start.allowbutton();
		start.setcountry("Sweden (+46)");
		String s = start.enterphonenumber("+46707471573");
		start.verifyphonenumber();
		String s1 = driver.findElement(By.id("com.rebtel.android:id/smsNumber")).getText();
		System.out.println(s1);
		Assert.assertEquals(s, s1);

	}
	

	
	
	
}
