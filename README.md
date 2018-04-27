# Firewatch
todo

### Description
todo

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
In order to start using proxy:
```java
DesiredCapabilities capabilities = new DesiredCapabilities();
if (FirewatchConnection.create()) {  
    capabilities.setCapability(CapabilityType.PROXY, FirewatchConnection.createSeleniumProxy());
    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);  
}
WebDriver driver = new ChromeDriver(capabilities);
```
