package test.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest extentTest;
	ExtentReports extentReports = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTestGroup = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
		extentTestGroup.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTestGroup.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTestGroup.get().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Skipped :" + result.toString());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Started context:" + context.toString());
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
}
