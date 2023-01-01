package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;

import java.util.List;

public class ProductPageConsumer extends PredefinedActions {
    private static ProductPageConsumer productPageConsumer;
    PropertyReading productPageConsumerProp;

    private ProductPageConsumer() {
        //Private Constructor for Singleton Design Pattern
        productPageConsumerProp = new PropertyReading(ConstantPaths.PROP_PATH+"ProductPageConsumerProp.properties");
    }

    public static ProductPageConsumer getProductPageConsumer() {
        if (productPageConsumer == null)
            productPageConsumer = new ProductPageConsumer();
        return productPageConsumer;
    }

    public String getTitle()
    {
        return getWebpageTitle();
    }
    public void clickOnConsumerSSD()
    {
        clickOnElement(getElement(productPageConsumerProp.getValue("consumerSSDBtn"),true ));
    }
    public void filterByInStock()
    {
        clickOnElement(getElement(productPageConsumerProp.getValue("inStockCheckBox"),true ));
    }

    /*public String getSortingOption()
    {
        return null;
    }*/

    public void sortByLowToHigh()
    {
        selectElement(getElement(productPageConsumerProp.getValue("selectElement"),true), productPageConsumerProp.getValue("sortByLowToHigh"));
    }

    public List<String> getLowToHighProduct()
    {
      return getWebElementListInString(productPageConsumerProp.getValue("sortLowToHighProducts"),true);
    }

    public void sortByHighToLow()
    {
        selectElement(getElement(productPageConsumerProp.getValue("selectElement"),true), productPageConsumerProp.getValue("sortByHighToLow"));

    }
    public List<String> getHighToLowProduct()
    {
        return getWebElementListInString(productPageConsumerProp.getValue("sortHighToLowProducts"),true);
    }

}
