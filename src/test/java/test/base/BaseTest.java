package test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public AndroidDriver driver;
	
	AppiumDriverLocalService service;
	
	String custNumbersFilePath;
	
	@BeforeClass
	public void configure() throws IOException {
		Properties prop = new Properties();
		String propFile = System.getProperty("user.dir") + "//src//main//java//resources//data.properties"; 
		
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		
		String apiumServerIPAddress = prop.getProperty("apiumServerIPAddress");
		System.out.println("apiumServerIPAddress: "+ apiumServerIPAddress);
		String apiumServerPort = prop.getProperty("apiumServerPort");
		System.out.println("apiumServerPort: "+ apiumServerPort);
		int port = Integer.parseInt(apiumServerPort);
		String hostName = apiumServerIPAddress + ":" + apiumServerPort;
		custNumbersFilePath = System.getProperty("user.dir")+"//src//test//java//resources//data.json"; 
		
		System.out.println("Customer numbers: "+ custNumbersFilePath);
		
		
		
		// This is the way to inject the any version of the apk
		String apkPath = prop.getProperty("apkPath");
		
		if (apkPath ==null || apkPath.isEmpty()) {
			// This is to provide a sample apk for testing the framework
			apkPath = System.getProperty("user.dir") + "//src//test//java//resources//rebtel6.24.0.apk";
		}
		
		String androidDeviceName = prop.getProperty("androidDeviceName");
		String nodejspath = prop.getProperty("nodejspath");
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("noReset", true);
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File(nodejspath))
				.withIPAddress(apiumServerIPAddress).usingPort(port).withTimeout(Duration.ofSeconds(300)).build();
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setApp(apkPath);
		options.setDeviceName(androidDeviceName);
		
		driver = new AndroidDriver(new URL("http://" + hostName), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	
	public List<HashMap<String, String>> getJsonData() throws IOException {
		//System.getProperty("user.dir")+"//src//test//java//resources//data.json"
			
				String custNumbers = FileUtils.readFileToString(new File(custNumbersFilePath),StandardCharsets.UTF_8);
				System.out.println("Customer numbers: "+ custNumbers);
				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, String>> data = mapper.readValue(custNumbers,
						new TypeReference<List<HashMap<String, String>>>() {
						});

				return data;

			}

	@AfterClass
	public void teardown() {
		driver.quit();
		service.stop();
	}

}
