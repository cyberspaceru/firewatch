package com.wiley.firewatch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.util.Optional;

/**
 * Created by itatsiy on 4/24/2018.
 */
public class FirewatchTest {
    private static final long TIMEOUT = 10000;
    private WebDriver driver;

    protected WebDriver getDriver() {
        if (driver == null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (FirewatchConnection.create()) {
                capabilities.setCapability(CapabilityType.PROXY, FirewatchConnection.createSeleniumProxy());
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            } else {
                Assert.fail();
            }
            FirewatchConnection.proxyServer().newHar("new har");
            System.setProperty("webdriver.chrome.driver", new File("drivers/chrome/chromedriver.exe").toString());
            driver = new ChromeDriver(capabilities);
        }
        return driver;
    }

    protected WebElement searchField() {
        return $(By.id("lst-ib"));
    }

    protected WebElement $(By locator) {
        WebElement result = null;
        long end = System.currentTimeMillis() + TIMEOUT;
        while (end > System.currentTimeMillis()) {
            try {
                result = getDriver().findElement(locator);
                break;
            } catch (Exception ignore) {
            }
        }
        return result;
    }

    @AfterClass
    public void teardown() {
        Optional.ofNullable(getDriver()).ifPresent(x -> {
            FirewatchConnection.proxyServer().stop();
            x.quit();
        });
    }
}
