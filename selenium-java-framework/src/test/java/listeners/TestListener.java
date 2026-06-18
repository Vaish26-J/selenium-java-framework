package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    @Override
    public void onStart(ITestContext context){
        extent = ExtentManager.getInstance();
        System.out.println("Test Suite Started!!!");
    }

    @Override
    public void onFinish(ITestContext context){
        System.out.println("Test Suite Finished !!!");
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result){
        test.set(extent.createTest(result.getName()));
        System.out.println("Test started "+ result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.get().pass("Test passed");
        System.out.println("Test Success "+ result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        test.get().fail(result.getThrowable());
        System.out.println("Test Failed "+ result.getName());
        String ss_path = ScreenshotUtils.captureScreenshot(BaseTest.getDriver(), result.getName()+System.currentTimeMillis());
        test.get().addScreenCaptureFromPath(ss_path);
    }
}
