package TestScripts;//import java.util.*;

import Base.PredefinedActions;
import Pages.DashboardPage;
import Pages.HomeLoanPage;
import Pages.ProductPageConsumer;
import Pages.TwoPlugsDashboardPage;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBase {
    HomeLoanPage homeLoanPage;
    ProductPageConsumer productPageConsumer;
    DashboardPage dashboardPage;
    TwoPlugsDashboardPage twoPlugsDashboardPage;

    @BeforeClass
    public void beforeClass() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm");
        System.setProperty("current.date.time", sdf.format(new Date()));

        PropertyConfigurator.configure("src/main/resources/Configs/Log4j.properties");

    }

    @BeforeMethod
    public void openBrowser() {
        PredefinedActions.initializeBrowser("https://www.twoplugs.com/", "chrome");
    }

    HomeLoanPage getDashboardPage() {
        if (homeLoanPage == null)
            homeLoanPage = HomeLoanPage.getDashboardPage();
        return homeLoanPage;
    }

    ProductPageConsumer getProductConsumerPageObj() {
        if (productPageConsumer == null)
            productPageConsumer = ProductPageConsumer.getProductPageConsumer();
        return productPageConsumer;
    }
    DashboardPage getProductDashboardObj() {
        if (dashboardPage == null)
            dashboardPage = DashboardPage.getDashboardPage();
        return dashboardPage;
    }

    TwoPlugsDashboardPage getTwoPlugsDashboardPageObj() {
        if (twoPlugsDashboardPage == null)
            twoPlugsDashboardPage = TwoPlugsDashboardPage.getTwoPlugsDashboardPage();
        return twoPlugsDashboardPage;
    }

    // @AfterMethod
    public void closeBrowser(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE)
            PredefinedActions.takeScreenshot(result.getName());
        PredefinedActions.closeBrowser();
    }
}
