**Allure results folder**

If setting results directory in allure plugin doesn't work

```xml

<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.12.0</version>
    <configuration>
        <resultsDirectory>${project.build.directory}/allure-results</resultsDirectory>
    </configuration>
</plugin>
```

create ```allure.properties``` with

```properties
allure.results.directory=target/allure-results
```

**File encoding**

To avoid Maven build message
```[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!```

add to POM.xml

```xml

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
</properties>
```

**Configure browser and browser mode**

***Jenkins***

Add ```BROWSER``` choice parameter with values ```chrome``` ```firefox``` ```edge```

Add ```BROWSER_MODE``` choice parameter with values ```headless``` ```headed```

Add build step with ```mvn clean test -Dbrowser=$BROWSER -Dheadless=--$BROWSER_MODE```

Add to ```BrowserFactory.class```

```
String browser=System.getProperty("browser")!=null?System.getProperty("browser"):EnvironmentReaderService.getProperty("browser");
String browserMode=System.getProperty("browser.mode")!=null?System.getProperty("browser.mode"):EnvironmentReaderService.getProperty("browser.mode");
```

where ```System.getProperty("browser")``` and ```System.getProperty("browser.mode")```represents values comes from
jenkins choice parameters passed to the ```mvn clean test -Dbrowser=$BROWSER -Dheadless=--$BROWSER_MODE```       
where  ```EnvironmentReaderService.getProperty("browser")```
and ```EnvironmentReaderService.getProperty("browser.mode")```
which represents values comes from ```config.properties``` file.
