package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getInstance(){
        ExtentSparkReporter html_reporter = new ExtentSparkReporter("reports/extent-reports.html");
        extent = new ExtentReports();
        extent.attachReporter(html_reporter);
        return extent;
    }
}
