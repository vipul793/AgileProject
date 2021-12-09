package com.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class HtmlReporting implements ITestListener{

	public  ExtentHtmlReporter htmlReporter;
	public  ExtentReports extentRep;
	public  ExtentTest logger;

	public void onStart(ITestContext testContext) {
	

		String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String resName = "TestReport-" + timestamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Reports/"+ resName); // location
															// "./test-												// path to
																											// store the
																											// report
																											// file
		htmlReporter.loadXMLConfig("D:\\AgileProject\\Config\\extent-config.xml");

		extentRep = new ExtentReports();

		extentRep.attachReporter(htmlReporter);
		extentRep.setSystemInfo("Host name", "localhost");
		extentRep.setSystemInfo("Environment", "QA");
		extentRep.setSystemInfo("user", "Vipul");

		htmlReporter.config().setDocumentTitle("Agile Project");// to sent the tile of report
		htmlReporter.config().setReportName("Functional Test Report"); // name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	public void onTestSuccess(ITestResult tr) {
		
		logger = extentRep.createTest(tr.getName()); // create each passed test new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Send the passed
			System.out.println("-----Test Succeed-------");											// information to the report
	}

	public void onTestFailure(ITestResult tr) {
		logger = extentRep.createTest(tr.getName()); // crate each failed test entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		 
		try {
			logger.addScreenCaptureFromPath(WebIteractableUtils.takeScrenshot(DriverUtils.getDriver(), tr.getName()));
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(tr.getName()+ " =----------Test Failed-------");
	}

	public void onTestSkipped(ITestResult tr) {
		logger = extentRep.createTest(tr.getName());// create each skipped test entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		
		System.out.println(tr.getName()+" -----------Test Skipped--------");
	}

	public void onFinish(ITestContext testContext) {
		System.out.println("---------Test Completed-------");
		extentRep.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("----------Test is started--------------");
		
	}

	

}
