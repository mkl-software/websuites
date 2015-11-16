# Overview

*Websuites* is a Java library for a nice JUnit and Selenium WebDriver integration. It provides a custom runner to create flexible test suites and run them against multiple browsers. It requires just one JAR/Maven dependency and very little configuration so it very easily integrates inside IDEs for CI containers.

# Feature highlight

* allows to quickly set up Selenium tests
* renders nice JUnit test tree for given test suites against multiple browsers
* pure Java solution, runs for any JVM container
* only one runner class required to start with
* all configuration in Java annotations
* same tests are launched for a given browser list
* initialization logic for tests before start and before every browser
* doesn't require any plugins, runs inside IDE in standard JUnit view
* comes with custom text-based syntax to write web scenarios using commands
* 240+ commands wrapping WebDriver browser operations and page assertions
* easy to write custom commands
* flexible test suite definition for
  * Java class tests
  * scenario files
  * all folder structures with all scenario files inside, all rendered in a JUnit test tree

# Websuites in one picture

![](http://mkl-software.com/static/websuites-img/websuites-overview-large.png "Websuite overview")

Click [here](http://mkl-software.com/static/websuites-img/websuites-overview-large.png "Large image") to see large image. 

# Quick tutorial

## Simplest runner
Let's start witn an empty Java project. To create a runner class you just need to extends WebSuiteRunner.
*SimplestRunner.java*
```java
import com.mkl.websuites.WebSuitesRunner;

public class SimplestRunner extends WebSuitesRunner {}
```

Now this class can be launched as a JUnit test. It defines an empty test suite, so it won't open any browser:

![](http://mkl-software.com/static/websuites-img/1.png "Simplest runner")

## Default Firefox test

Firefox driver is available OOTB, so if you have Firefox installed you can run an actual web test with as little configuration as this:

*WebRunner.java*
```java
import com.mkl.websuites.WebSuitesRunner;

@WebSuites(browsers = "ff")
public class WebRunner extends WebSuitesRunner {}
```

Again, the test suite is empty, but now this test will open Firefox and then close it:

![](http://mkl-software.com/static/websuites-img/2.png "Empty suite in a browser")

## Sample Java test case

Let's define a simple test case in Java to run in a browser:

*RunnerWithTest.java*:
```java
import com.mkl.websuites.WebSuiteRunner;
import com.mkl.websuites.config.TestClass;

@WebSuites(tests = @TestClass(SampleWebTest.class), browsers = "ff")
public class RunnerWithTest extends WebSuitesRunner {}
```
*SampleWebTest.java*:
```java
import com.mkl.websuites.MultiBrowserTestCase;
import org.junit.Assert;

public class SampleWebTest extends MultiBrowserTestCase {
    protected void runWebTest() {
        // "browser" fields holds WebDriver, lots of userful API available here 
        browser.get("http://google.com");
        Assert.assertEquals("Google", browser.getTitle());
    }
}
```

The test is automatically detected and run in Firefox:

![](http://mkl-software.com/static/websuites-img/3.png "Simplest runner")

## Run in many browsers
Now let's run the same test for both Firefox and Chrome. To do this we need to define a Chrome browser by specifying a path to its web driver:

*RunnerForManyBrowsers.java*:
```java
import com.mkl.websuites.WebSuiteRunner;
import com.mkl.websuites.config.TestClass;
import com.mkl.websuites.config.BrowserConfig;

@WebSuites(
    // "ff" is an ID of Firefox browser which is available by default:
    browsers = {"ff", "chrome"},
    tests = @TestClass(SampleWebTest.class),
    browserConfiguration = @BrowserConfig(
        id = "chrome",
        displayName = "Chrome",
        browserType = BrowserConifg.BrowserType.CHROME,
        webDriverPath = "src/test/resources/ChromeDriver.exe"
    )
)
public class RunnerForManyBrowsers extends WebSuitesRunner {}
```
When we launch this suite, we will see a SampleWebTest being run twice:
 
![](http://mkl-software.com/static/websuites-img/4.png "Same test in two browsers")

 You can define as many browser as you want and then easily set browers list to run tests against! For example you can define different runner for you dev, test, prod or CI environments.
 
## Sample scenario test case
The actual power of *Websuites* comes when using scenario files. You can write text commands that wrap WebDriver logic. This allows to engage non-programming people (like QA folks) in the development of UI tests.
The scenario files are plain text files with TAB-seperated syntax. First token is always a command name and then there are parameters. This is a sample scenario file:

*sampleScenario.scn*:
```
# Comments begin with # character
# Seperate each paramter with a TAB (or TABs)

goto                        http://google.com

checkTitleContains          Google

# Each "check..." command is and assertion and comes in 4 versions: "check...",
#   negated "~check...", soft assertion "softCheck..." (will not fail test)
#   and negated soft assertion "~softCheck..."
~checkTitleContains         Yahoo
type                        name=q  text=Selenium
press                       ENTER
checkText                   Web Browser Automation
checkLinkHrefContaining     wikipedia
```
Detailed command reference will be avaiable soon.
Now just create a runner:

*RunnerWithScenarioFile.java*:
```java
import com.mkl.websuites.WebSuiteRunner;
import com.mkl.websuites.config.ScenarioFile;

@WebSuites(scenarios = @ScenarioFile("src/test/resources/scenarios/sampleScenario.scn"))
public class RunnerWithScenarioFile extends WebSuitesRunner {}
```

## Sample folder test
In addition to running single scenario files, you can run all files from a given root folder. In that, *websuites* will render a JUnit test tree reflecting the folder structure. This is how to run such a test suite:

*RunnerForFolder.java*:
```java
import com.mkl.websuites.WebSuiteRunner;
import com.mkl.websuites.config.ScenarioFile;

@WebSuites(folders = @Folder("src/test/resources/integration-web-tests"))
public class RunnerForFolder extends WebSuitesRunner {}
```

Resuling in a JUnit test tree:

## Environment initialization and tear-down

To prepare test environment you can override following methods from `com.mkl.websuites.WebSuitesRunner` base class:

- `setUp`: runs before all tests in the suite, can be used e.g. to prepare database 
- `tearDown`: runs after all tests in the suite are completed
- `setUpBeforeBrowser`: runs before all tests for a given browser, can be used e.g. to clean test environment
- `tearDownAfterBrowser`: run after all tests for a given browser are complted

# More information

Detailed guides, tutorials and API references will come up soon.