package com.wiley.firewatch;

import com.wiley.firewatch.api.Firewatch;
import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.utils.ContentType;
import com.wiley.firewatch.utils.StringMatcher;
import org.testng.annotations.Test;

/**
 * Created by itatsiy on 4/24/2018.
 */
public class BaseTests extends FirewatchTest {

    private static class SimpleObject {
        private int code = 5;
        private String message = "text";
    }

    @Test
    public void singleRequest() {
        getDriver().get("https://www.google.ru");
        searchField().sendKeys("firewatch");
        Firewatch.request()
                .url(MatchingType.REGEXP, "google.(com|ru)/complete/search")
                .parameterEquals("q", "firewatch")
                .thenResponse()
                .contentType(ContentType.json().utf8())
                // JSON object not equals {code: 5, message: "text"}
                .not().jsonEquals(SimpleObject.class, new SimpleObject())
                .executeWithTimeout();
    }
}
