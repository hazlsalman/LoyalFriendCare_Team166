package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class TestBaseRapor {

    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static ExtentSparkReporter extentSparkReporter;

    public TestBaseRapor(){

    }


    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        Locale.setDefault(new Locale("en", "US"));
        extentReports = new ExtentReports();

        String date = new SimpleDateFormat("_yyyyMMdd_hhmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/test-output/Rapor"+date+".html";

        extentSparkReporter = new ExtentSparkReporter(filePath);
        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("Enviroment","live");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser")); // chrome, firefox
        extentReports.setSystemInfo("Automation Engineer", "Hamza KAVAS");
        extentSparkReporter.config().setDocumentTitle("TestNG Test");
        extentSparkReporter.config().setReportName("Html Reports");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            String resimYolu = ReusableMethods.raporaResimEkle(result.getName());
            extentTest.fail(result.getName());
            extentTest.addScreenCaptureFromPath(resimYolu);
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test Case is skipped: " + result.getName());
        }
        Driver.quitDriver();

    }


    @AfterTest(alwaysRun = true)
    public void tearDownTest() {

       extentReports.flush();
    }

}
