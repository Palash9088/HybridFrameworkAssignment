package TestScripts;//import java.util.*;

import Pages.TwoPlugsDashboardPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class VerifyTwoPlugsDashboardPageTest extends TestBase {

    Logger log = Logger.getLogger(VerifyTwoPlugsDashboardPageTest.class);

    @Test
    public void verifyTwoPlugs() {
        TwoPlugsDashboardPage twoPlugsDashboardPage = getTwoPlugsDashboardPageObj();
        //1. Navigate to "https://www.twoplugs.com/".

        //2. Click on LIVE POSTING
        twoPlugsDashboardPage.clickOnLivePosting();

        //3. click on services dropdown and check if the list is sorted or not.
        List<String> servicesList = twoPlugsDashboardPage.getServicesList();
        log.info(servicesList);

        //4. click on needs
        twoPlugsDashboardPage.clickOnNeeds();

        //5. click on (" >>| ") button
        twoPlugsDashboardPage.clickOnLastButton();

        //6. click on need a prom date
        twoPlugsDashboardPage.clickOnPromButton();

        //7. Get all need details
        Map<String, String> allDetailsMap = twoPlugsDashboardPage.getAllDetails();
        for (Map.Entry<String, String> entry : allDetailsMap.entrySet()) {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        }

        //8. navigate back and go to location search bar and clear the field
        twoPlugsDashboardPage.navigateBackToDashboardPage();
        twoPlugsDashboardPage.clearField();

        //9. then type "Toronto" in dropdown and choose the 3rd option from the suggested list
        twoPlugsDashboardPage.enterAddress("Toronto");
    }
}
