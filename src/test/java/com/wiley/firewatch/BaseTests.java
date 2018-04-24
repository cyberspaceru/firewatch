package com.wiley.firewatch;

import com.wiley.firewatch.api.Firewatch;
import com.wiley.firewatch.observers.MatchingType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by itatsiy on 4/24/2018.
 */
public class BaseTests extends FirewatchTest {
    @Test
    public void startDriverWithProxy() {
        getDriver().get("https://www.google.com");
        Assert.assertNotNull(searchField());
    }

    @Test
    public void singleRequest() {
        getDriver().get("https://www.google.ru");
        searchField().sendKeys("firewatch");
        Firewatch.request()
                .url(MatchingType.REGEXP, "google.(com|ru)/complete/search")
                .parameterEquals("q", "firewatch")
                .executeWithTimeout();
    }
}
