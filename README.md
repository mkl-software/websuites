# Overview

*Websuites* is a Java library for a convenient JUnit and Selenium WebDriver integration. It provides a custom JUnit runner to flexibly define complex test suites and then run them against multiple browsers. It requires just one JAR/Maven dependency and very little configuration so it very easily integrates inside your IDE for CI container.

## Feature highlight

* allows to quickly set up Selenium tests
* encourages fine-grained, small UI tests to run in IDE
* renders nice JUnit test tree for multiple browsers
* pure Java solution, runs for any JVM container
* only one runner class required to start with
* all configuration in Java annotations
* same tests are launched for a given browser list
* doesn't require any plugins, runs inside IDE in standard JUnit view
* comes with custom text-based syntax to write web scenarios using commands
* exposes 240+ commands wrapping WebDriver browser operations and page assertions
* also easy to write custom commands
* flexible test suite definition for:
  * Java class tests
  * scenario files
  * folder structure with all scenario files inside, all rendered in a JUnit test tree
* also designed to be easily extended by:
  * writing your own commands for text scenario files
  * overriding default framework logic to deeply adjust *websuites* to your needs
* nicely works with configurable profiles (e.g. in Maven) to define different suites for different environments (dev, test, prod, etc.)

## Websuites in one picture

Click [here](http://mkl-software.com/static/websuites-img/websuites-overview-large.png "Large image") to see large image. 

![](http://mkl-software.com/static/websuites-img/websuites-overview-small.png "Websuite overview")

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

Let's define a simple web test case in Java to run in Firefox:

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
        // "browser" field holds WebDriver reference,
        // also lots of userful API available here. Simple example:
        browser.get("http://google.com");
        Assert.assertEquals("Google", browser.getTitle());
    }
}
```

When run, the test is automatically launched in Firefox:

![](http://mkl-software.com/static/websuites-img/3.png "Simplest runner")

## Run in many browsers
Now let's run the same test for both Firefox and Chrome. To do this, first we need to define a Chrome browser by specifying a path to its web driver:

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
 
## Sample scenario file test case
In addition to Java test classes, in *websuites* you can use text scenario files. Inside you can use commands that wrap WebDriver logic. This allows for example to engage non-programming people (like QA folks) in the development of UI tests.
The scenario files are plain text files with TAB-seperated syntax. First token is always a command name and then there are parameters. This is a sample scenario file:

*sampleScenario.scn*:
```
# Comments begin with # character
# Seperate each paramter with a TAB (or TABs)

goto                        http://google.com

checkTitleContains          Google

# Each "check..." command is and assertion and comes in 4 versions: "check...",
#   negated "~check...", soft assertion "softCheck..." (will not fail test until
#   cumulative check with "checkAllSoft" command
#   and negated soft assertion "~softCheck..."

# check if page title is NOT Yahoo:
~checkTitleContains         Yahoo

type                        name=q  text=Selenium
press                       ENTER
checkText                   Web Browser Automation
checkLinkHrefContaining     wikipedia
```

All the commands are Java classes behind and you simply add your own commands.

Detailed command reference and tutorial will be avaiable soon.

Now just create a runner:

*RunnerWithScenarioFile.java*:
```java
import com.mkl.websuites.WebSuiteRunner;
import com.mkl.websuites.config.ScenarioFile;

@WebSuites(scenarios = @ScenarioFile("src/test/resources/scenarios/sampleScenario.scn"))
public class RunnerWithScenarioFile extends WebSuitesRunner {}
```

## Sample folder test
The actual power of *websuites*, however, comes when using foldered suite. In this case, you can run all scenario files starting from a given root folder and *websuites* will render a JUnit test tree reflecting the folder structure. This is how to run such a test suite:

*RunnerForFolder.java*:
```java
import com.mkl.websuites.WebSuiteRunner;
import com.mkl.websuites.config.ScenarioFile;

@WebSuites(folders = @Folder(path = "src/test/resources/web-tests"), browsers = "ff")
public class RunnerForFolder extends WebSuitesRunner {}
```

And for **web-test** folder like this:

![](http://mkl-software.com/static/websuites-img/5.png "Folder structure")

The resuling JUnit test tree will look like:

![](http://mkl-software.com/static/websuites-img/6.png "Test tree for folder structure")

You can see all scenario files inside all nested folders.

Now it's really easy to add small, fine-grained UI tests inside your folder structure. You can then define simple test case scenarios for your application and decouple test definition from your code.

## Environment initialization and tear-down

To prepare test environment you can override following methods from `com.mkl.websuites.WebSuitesRunner` base class:

- `setUp`: runs before all tests in the suite, can be used e.g. to prepare database 
- `tearDown`: runs after all tests in the suite are completed
- `setUpBeforeBrowser`: runs before all tests for a given browser, can be used e.g. to clean test environment
- `tearDownAfterBrowser`: run after all tests for a given browser are completed

# More information

Detailed guides, tutorials and API references will come up soon.
