package com.wiley.firewatch;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by itatsiy on 4/24/2018.
 */
public class ProxyStartedTest extends FirewatchTest {
    @Test
    public void startDriverWithProxy() {
        getDriver().get("https://www.google.com");
        Assert.assertNotNull(searchField());
    }
}
