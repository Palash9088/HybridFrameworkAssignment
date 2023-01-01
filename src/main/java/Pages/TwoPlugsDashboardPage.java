package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;

import java.util.*;

public class TwoPlugsDashboardPage extends PredefinedActions {
    private static TwoPlugsDashboardPage twoPlugsDashboardPage;
    PropertyReading twoPlugsDashboardPageProp;

    private TwoPlugsDashboardPage() {
        //Private Constructor for Singleton Design Pattern
        twoPlugsDashboardPageProp = new PropertyReading(ConstantPaths.PROP_PATH + "TwoPlugsDashboardPageProp.properties");
    }

    public static TwoPlugsDashboardPage getTwoPlugsDashboardPage() {
        if (twoPlugsDashboardPage == null)
            twoPlugsDashboardPage = new TwoPlugsDashboardPage();
        return twoPlugsDashboardPage;
    }

    public void clickOnLivePosting() {
        clickOnElement(getElement(twoPlugsDashboardPageProp.getValue("livePostingBtn"), true));
    }

    public List<String> getServicesList() {
        clickOnElement(twoPlugsDashboardPageProp.getValue("clickAll"),true);
        List<String> services = getWebElementListInString(twoPlugsDashboardPageProp.getValue("servicesList"), true);
        services.remove(0);
        return services;
    }
    public void clickOnNeeds()
    {
        clickEnter(twoPlugsDashboardPageProp.getValue("needsBtn"),true);
    }
    public void clickOnLastButton()
    {
        clickOnElement(twoPlugsDashboardPageProp.getValue("lastBtn"),true);
    }
    public void clickOnPromButton()
    {
        clickOnElement(twoPlugsDashboardPageProp.getValue("needPromBtn"),true);
    }
    private List<String>tableLeftSide()
    {
        return getWebElementListInString(twoPlugsDashboardPageProp.getValue("listLeftSide"),true );
    }
    private List<String>tableRightSide()
    {
        return getWebElementListInString(twoPlugsDashboardPageProp.getValue("listRightSide"),true );
    }
    public Map<String,String> getAllDetails()
    {
        List<String> rightList = tableRightSide();
        List<String> leftList = tableLeftSide();
        Map<String,String> detailsMap = new LinkedHashMap<>();

        for(int i=0;i<rightList.size();i++)
        {
            detailsMap.put(leftList.get(i),rightList.get(i));
        }
        return detailsMap;
    }

    public void navigateBackToDashboardPage()
    {
        navigateBack();
    }
    public void clearField()
    {
        clearElementField(twoPlugsDashboardPageProp.getValue("addressInput"),true );
    }

    public void enterAddress(String address)
    {
        enterText(getElement(twoPlugsDashboardPageProp.getValue("addressInput"),true),address);
    }


}
