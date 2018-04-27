package com.wiley.firewatch;

import com.wiley.firewatch.api.Firewatch;
import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.utils.contenttype.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by itatsiy on 4/24/2018.
 */
public class BaseTests extends FirewatchTest {
    @Test
    public void singleRequest() {
        getDriver().get("https://www.google.ru");
        searchField().sendKeys("firewatch");
        Firewatch.request()
                .url(MatchingType.REGEXP, "google.(com|ru)/complete/search")
                .parameterEquals("q", "firewatch")
                .thenResponse()
                .contentType(ContentType.json().utf8())
                .executeWithTimeout();
    }
}
