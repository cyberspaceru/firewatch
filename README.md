# Firewatch
Firewatch is automated functional testing tool based on SeleniumDriver and BrowserMobProxyServer. Firewatch allows building assert for requests and responses which was sent and received by your SeleniumDriver.

### Getting started
To use Firewatch in your project, add the `firewatch` dependency to your pom:

```xml
<dependency>
    <groupId>com.wiley</groupId>
    <artifactId>firewatch</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>

<!--Temp solution-->
<repositories>
    <repository>
        <id>firewatch-mvn-repo</id>
        <url>https://raw.github.com/cyberspaceru/firewatch/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
In order to start using proxy read wiki: https://github.com/cyberspaceru/firewatch/wiki
