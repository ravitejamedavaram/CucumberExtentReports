package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentBDDReporter;

public class ExtentReportsConfig {


    public static void getReports() {
        ExtentBDDReporter bdd = new ExtentBDDReporter(".");
        ExtentReports extent = new ExtentReports();
        bdd.loadConfig("/Users/ravimeda/Desktop/abcd/SwiggyUITests/src/test/resources/bdd-config.xml");
        extent.attachReporter(bdd);
        extent.flush();
    }
}
