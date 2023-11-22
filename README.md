# Project Overview

The Rebtel App is tested on Android Emulator devices.

# Tools used

Appium is used as automation tool for testing the application based on Selenium.
Maven is used as the build automation tool
The framework implements the TestNG framework for generating reports
Development time IDE: Eclipse is used for developing the framework.
Java is used as a programming language for the automation suites.
The App can be run on the Android Emulator inside Android Studio
Dependencies for Automating using Appium tool for Android

A computer with Windows 10 or macOS X 10.7 or later. Java Development Kit (JDK) or Java SE version 8 or later. Node and npm version 10+ or later. An emulator or real Android device. Appium Inspector to get the element locator. Appium Gesture Plugin installation for scrolling/swiping (The plugin will not be active unless turned on when invoking the Appium server:) Integrated development environment (IDE). APK File


#Framework Design - The Page Object Model

The automation framework is based on the Page Object Model(POM), which is a design pattern, that creates Android Object Repository for locator elements.

The POM Framework helps reduce code duplication and provides better control for the test case framework maintenance. The framework is developed in a way to make the code readable and reusable(For example: the object repository is independent of the test cases).


#Project layout
src\main\java\internal.pageobjects: Contains the Page Object Models.
src\main\java\resources: The properties file
src\test\java: The actual test cases. The is further categorized into base(common utility) and the test cases for different features.
reports: This folder is the placeholder for the test case reports after execution via the TestNG framework. Note that it contains a pre saved report for reference. It will be overriden after run.
target: To hold the compiles classes after build.
testsuite: Contains the TestNG suite configuration.

#Suite Execution steps
The section describes the different ways to execute the automation tests. There are 3 different ways in which it can be done:

# Using Command line: maven commands
Using an IDE: Eclipse is taken as a reference point
These are detailed in the subsequent sections.

## Data Dependency Pre-requisite

(I) Framework setup: 

The framework requires the provide the nodejspath and the apkpath. Both of these are configurable via the properties file "data.properties" placed in src/main/java/resources

1) nodejspath : This is required because Appium is an HTTP server written in node. js which creates and handles multiple WebDriver sessions for different platforms like iOS and Android.. Note that it is mandatory to set this. For example if you are running this in a windows machine, if your node js installation is at the following path :C://your//home//AppData//Roaming//npm//node_modules//appium//build//lib//main.js then set: nodejspath=C://usr//home//AppData//Roaming//npm//node_modules//appium//build//lib//main.js

2) apkpath : This is the way to inject any version of apk that you want to test. By default, the Rebtels 6.24 version is checked in in the code base just to give a way to test to the user. However, that apk can be overriden by downloading an apk and providing the path of the apk in this property. For example, if you have downloaded the rebtel apk at the following location: C://your//home//RebtelApks//Rebtel_ Call, Top-Up, Transfer_6.24.0_Apkpure.apk, then you can set the following property:

apkpath=C://your//home//RebtelApks//Rebtel_ Call, Top-Up, Transfer_6.24.0_Apkpure.apk

(II) Test case setup: 

There is a single test case setup which will open the rebtel app and send a verification OTP to the number provided. The phone number to be used as a test number has to be manually edited for now.

Enter the Phone number with the desired Country code for international calling in the file : src\test\java\test\features\Registration.java Note: This is hard coded for now, but the test case can be extended to take this input in different ways. This was not done due to time constraints.


## Execution using command line: mvn commands

### Prerequisite

Install the latest Maven and set the MAVEN_HOME and add it to paths in your system variables.
Clone the repository: git clone https://github.com/Aakanksha1411/RebtelApp.git

### Steps

* Open cmd or equivalent and go to the cloned repo location.

* Execute appium --use-plugins=gestures The plugin will not be active unless turned on when invoking the Appium server:

* Execute: mvn clean install -PRegression
  You will get the test run result on the cmd console. Also, the latest test report is updated @ reports/index.html


## Execution via IDE: Eclipse

### Prerequisites

* Download the Eclipse IDE

* Install the TestNg plugin via the Eclipse Marketplace

### Run via EclipseIDE

Execute appium --use-plugins=gestures in npm The plugin will not be active unless turned on when invoking the Appium server:

Goto : testsuite/testng.xml

Right Click -> Run As -> TestNG Suite

You will get the test run result on the cmd console. Also, the latest test report is updated @ reports/index.html


# Known Issues

Emulator is slow and and not responding sometimes-Socket hangup or System UI Isnt responding message error.

As a workaround, run the following command an re-run the test case: 
adb uninstall io.appium.uiautomator2.server.test