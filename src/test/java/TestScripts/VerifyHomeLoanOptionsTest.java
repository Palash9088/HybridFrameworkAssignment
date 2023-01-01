package TestScripts;//import java.util.*;

import Base.PredefinedActions;
import Pages.HomeLoanPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

public class VerifyHomeLoanOptionsTest extends TestBase {
    static Logger log = Logger.getLogger(VerifyHomeLoanOptionsTest.class);
    @Test
    public void verifyHomeLoanPage() {
        HomeLoanPage homeLoanPage = getDashboardPage();
        //Task 1: Launch URL - https://www.switchme.in/

        //Verify if browser is launched or not
        String browserString= PredefinedActions.getWindowHandle();
        log.info(browserString);
        Assert.assertNotNull(browserString);

        String expectedTitle = "Home loan made easy with SwitchMe";
        String actualTitle = homeLoanPage.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        //Task 2: Click on “Get a Home Loan” button.
        homeLoanPage.clickOnGetaHomeLoanButton();

        //Task 3: Enter Loan Amount as 50,00,000
        //Task 4: click on “Show Me My Options”
        homeLoanPage.calculateHomeLoan("5000000");

        //Task 5: Verify If the first line item is expanded by default.
        boolean isBlockExpanded = homeLoanPage.isLoanBlockExpand();
        Assert.assertTrue(isBlockExpanded);

        //Task 6: click on the first item to collapse it.
        homeLoanPage.collapseFirstBlock(); //collapsing first block

        //Task 7: Get the scheme name which is offering the minimum and maximum Interest Rate.
        Map<Double, String> elementMap = homeLoanPage.getMapOfBankNameAndInterest();

        for (Map.Entry<Double, String> entry : elementMap.entrySet()) {
            System.out.println( "Bank Name -> " + entry.getValue() + " & Interest is -> " + entry.getKey());
        }

        double actualMaxInterestRate = Collections.max(elementMap.keySet());
        String bankName1 = elementMap.get(actualMaxInterestRate);
        log.info("Bank name is :" + bankName1 + "Interest rate is: " + actualMaxInterestRate);
        Assert.assertEquals(actualMaxInterestRate,12.0);

        double actualMinInterestRate = Collections.min(elementMap.keySet());
        String bankName2 = elementMap.get(actualMinInterestRate);
        log.info("Bank name is :" + bankName2 + "Interest rate is: " + actualMinInterestRate);
        Assert.assertEquals(actualMinInterestRate,4.0);

    }
}
