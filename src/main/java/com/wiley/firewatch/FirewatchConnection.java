package com.wiley.firewatch;

import com.wiley.firewatch.exceptions.FirewatchConnectionUnavailableException;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.commons.lang3.text.StrBuilder;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.net.NetworkUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.util.Optional.ofNullable;
import static java.util.UUID.randomUUID;
import static net.lightbody.bmp.proxy.CaptureType.*;

@Slf4j
@UtilityClass
@Accessors(fluent = true)
public class FirewatchConnection {
    private static final Object LOCK = new Object();
    private static final ThreadLocal<BrowserMobProxy> proxyServer = new ThreadLocal<>();

    public static boolean create() {
        return !isAvailable() && connect();
    }

    public static BrowserMobProxy proxyServer() {
        return proxyServer.get();
    }

    public static boolean isAvailable() {
        return proxyServer() != null && proxyServer().isStarted();
    }

    public static Proxy createSeleniumProxy() {
        if (proxyServer() == null) {
            throw new NullPointerException("Proxy Server is not created.");
        }
        try {
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer());
            String proxy = new NetworkUtils().getIp4NonLoopbackAddressOfThisMachine().getHostAddress() + ":" + proxyServer().getPort();
            seleniumProxy.setHttpProxy(proxy);
            seleniumProxy.setSslProxy(proxy);
            seleniumProxy.setFtpProxy(proxy);
            return seleniumProxy;
        } catch (Exception e) {
            log.error("Can't create Selenium Proxy.", e);
        }
        return null;
    }

    public static void newHar() {
        if (isAvailable()) {
            if (proxyServer().getHar() != null) {
                log.info("End har.");
                proxyServer().endHar();
            }
            String initialPageRefName = randomUUID().toString();
            log.info("New har '{}' for a proxy on {} port", initialPageRefName, proxyServer().getPort());
            proxyServer().newHar(initialPageRefName);
        } else {
            throw new FirewatchConnectionUnavailableException();
        }
    }

    private static boolean connect() {
        try {
            synchronized (LOCK) {
                log.info("Creating Proxy Connection...");
                BrowserMobProxy proxyServer = new BrowserMobProxyServer();
                proxyServer.setTrustAllServers(true);
                proxyServer.enableHarCaptureTypes(REQUEST_HEADERS, REQUEST_CONTENT, REQUEST_BINARY_CONTENT, RESPONSE_HEADERS, RESPONSE_CONTENT, RESPONSE_BINARY_CONTENT);
                proxyServer.start();
                log.info("Proxy Connection has CREATED.");
                FirewatchConnection.proxyServer.set(proxyServer);
                printAboutProxy();
                return true;
            }
        } catch (Exception e) {
            log.error("Proxy Connection has NOT CREATED. See log for details.", e);
        }
        return false;
    }

    private static void printAboutProxy() {
        StrBuilder builder = new StrBuilder();
        builder.appendln("");
        builder.appendln("Proxy Connection report...");
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            builder.appendln("HostAddress: " + inetAddress.getHostAddress());
            builder.appendln("HostName: " + inetAddress.getHostName());
        } catch (UnknownHostException ignore) {
            builder.appendln("LocalHost info unavailable.");
        }
        builder.appendln("ClientBindAddress: " + ofNullable(proxyServer().getClientBindAddress()).map(InetAddress::getCanonicalHostName).orElse("null"));
        builder.appendln("ServerBindAddress: " + ofNullable(proxyServer().getServerBindAddress()).map(InetAddress::getCanonicalHostName).orElse("null"));
        Proxy seleniumProxy = createSeleniumProxy();
        builder.appendln("HttpProxy: " + ofNullable(seleniumProxy).map(Proxy::getHttpProxy).orElse("null"));
        builder.appendln("SslProxy: " + ofNullable(seleniumProxy).map(Proxy::getSslProxy).orElse("null"));
        builder.append("FtpProxy: " + ofNullable(seleniumProxy).map(Proxy::getFtpProxy).orElse("null"));
        log.info(builder.toString());
    }
}
