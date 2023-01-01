package Pages;//import java.util.*;

import Base.PredefinedActions;
import Utils.PropertyReading;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HomeLoanPage extends PredefinedActions {
    private static HomeLoanPage homeLoanPage;
    PropertyReading homeLoanPageProp;

    private HomeLoanPage() {
        //Private Constructor for Singleton Design Pattern
        homeLoanPageProp = new PropertyReading("src/main/resources/Configs/homeLoanPageProp.properties");
    }

    public static HomeLoanPage getDashboardPage() {
        if (homeLoanPage == null)
            homeLoanPage = new HomeLoanPage();
        return homeLoanPage;
    }

    public String getPageTitle() {
        return getWebpageTitle();
    }

    public void clickOnGetaHomeLoanButton() {
        // clickOnElement("xpath", homeLoanPageProp.getValue("getHomeLoanBtn"), true);
        clickOnElement(getElement(homeLoanPageProp.getValue("getHomeLoanBtn"),true));
    }

    public void calculateHomeLoan(String amount) {
        //WebElement loanAmount = getElement("xpath", homeLoanPageProp.getValue("loanAmountElement"), true);
        enterText(getElement(homeLoanPageProp.getValue("loanAmountElement"),true),amount);

        //WebElement clickBtn = getElement("xpath", homeLoanPageProp.getValue("showMeOptionBtn"), false);
        clickOnElement(getElement(homeLoanPageProp.getValue("showMeOptionBtn"),true));
    }


    public boolean isLoanBlockExpand() {
        String attribute = getAttribute(getElement(homeLoanPageProp.getValue("expandCheck"),true),"class");
        return attribute.contains("open");
    }

    public void collapseFirstBlock()
    {
        //WebElement element = getElement("xpath", homeLoanPageProp.getValue("collapseBlock"), true);
        clickOnElement(getElement(homeLoanPageProp.getValue("collapseBlock"),true));
    }

    public List<String> getInterestList()
    {
        return getWebElementListInString(homeLoanPageProp.getValue("allInterestRate"),true );
    }

    public List<Double> getInterestListInDouble()
    {
        return getWebElementListInDouble(homeLoanPageProp.getValue("allInterestRate"),true );
    }
    public Map<Double,String> getMapOfBankNameAndInterest() {

        List<String> stringBankList = getWebElementListInString(homeLoanPageProp.getValue("allBankName"),true);
        List<Double> interestListInDouble = getInterestListInDouble();
        Map<Double, String> bankNameAndInterestMap = new LinkedHashMap<>();

        for(int i=0;i<=stringBankList.size()-1;i++)
        {
            bankNameAndInterestMap.put(interestListInDouble.get(i),stringBankList.get(i));
        }
    return bankNameAndInterestMap;
    }

}
