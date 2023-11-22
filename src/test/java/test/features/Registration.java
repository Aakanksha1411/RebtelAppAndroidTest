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

	@Test(dataProvider="getData")
	public void RegisterUser(HashMap<String,String> input) throws MalformedURLException {

		RegisterationPage start = new RegisterationPage(driver);
		start.getstartedbutton();
		start.allowbutton();
		String userCountryCode = input.get("country");
		String userPhNumber = input.get("PhoneNumber");
		start.setcountry(userCountryCode);
		String s = start.enterphonenumber(userPhNumber);
		start.verifyphonenumber();
		String s1 = driver.findElement(By.id("com.rebtel.android:id/smsNumber")).getText();
		System.out.println(s1);
		Assert.assertEquals(userPhNumber, s1);

	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>>	data =getJsonData();
		
		return new Object[][] { {data.get(0)}  };
	}
	
	
	
}
