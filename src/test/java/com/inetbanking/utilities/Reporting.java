package com.inetbanking.utilities;

//Listener Class/Utility used to generate Extent Reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	
	public ExtentSparkReporter htmlReporter;//Had to change ExtentHtmlReporter to ExtentSpark reporter as suggested in https://sqa.stackexchange.com/questions/46834/unable-to-import-extenthtmlreporter-for-the-java-class
	public ExtentReports extent;
	public ExtentTest logger;
	
	
	public void onStart(ITestContext testContext)
	{
		String timestamp=new SimpleDateFormat ("yyyy.MM.dd.hh.mm.ss").format(new Date());//current time stamp
		String repName="Test-Report-"+timestamp+".html";//To ensure different reports have different name identifier in timestamp
		
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location
		
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		//Try and Catch phrase added on Eclipse suggestion
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","Ankit");
		
		htmlReporter.config().setDocumentTitle("InetBanking Test Project");//Title of Report
		htmlReporter.config().setReportName("Functional Test Automation Report");//Name of the report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of the chart---Object .setTesViewChartLocation not found
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));//send the passed information
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));//send the failed information
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f=new File(screenshotPath);
		
		if (f.exists())
		{
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
		
		/*{
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			}
		catch(IOException e)
			{
				e.printStackTrace();
			}
		}*/
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
