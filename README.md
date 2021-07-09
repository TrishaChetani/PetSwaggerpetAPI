<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [API Test](#APITest)
    - [Tech stacks](#tech-stacks)
    - [Setup](#setup)
        - [Dev Setup mocha and chai supertest](#setup)
        - [Running test cases](#RunningTestSpecs)
        - [Folder structure](#FolderStructure)
<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# APITest

API test : DummyRestAPITest (http://dummy.restapiexample.com/)
- Check the basic functionality of API.
- API are not working with different edge cases, hence bugs are there

## Tech stacks

- [`Serenity`](http://www.thucydides.info/#/)
- [`rest-assured`](https://rest-assured.io/)
- [`cucumber`](https://cucumber.io/)
- [`hamcrest`](http://hamcrest.org/JavaHamcrest/)

## Setup
1. Java
2. Gradle

### Dev Setup nodejs and yarn
* set up Java also set the environment variables.
* setup Gradle.
* In Intellij(sync the project and finish the gradle build so that all dependency are loaded)

# RunningTestSpecs
```
gradle clean test -DbaseURI="http://dummy.restapiexample.com" --stacktrace
```

# FolderStructure
````
.
├── Jenkinsfile
├── README.md
├── build.gradle
├── gradle.properties
├── serenity.properties
├── settings.gradle
└── src
├── main
│             ├── java
│             │             └── com
│             │    └── api
│             │                     ├── config
│                                   │             ├── DefaultConfig.java
│             │                     │             └── SessionVariables.java
│             │                     ├── support
│             │                     │             ├── RequestCollection.java
│             │                     │             └── ServicesSupport.java
│                  └── utils
│             │                     └── FileUtils.java
│             └── resources
│                 ├── dev.properties
│                 ├── prod.properties
│                 ├── sit.properties
│                 └── uat.properties
└── test
├── java
│            └── com
│                └── api
│                     ├── steps
│                     │             ├── CommonSteps.java
│                     │             └── definition
│                     │                └── EmployeeStepDefiniation.java
│                     └── testrunner
│                       └── TestRunner.java
└── resources
├── features
│        └── EmployeeAPI.feature
└── payloads

19 directories, 19 files


````

#  Continuous Integration & Continuous Deployment
- **Jenkinsfile written using groovy code, which enables the capability to run the automated test in CI-CD**

# Bugs
- API frequently goes down and it become challenging to develop this test cases(https://streamable.com/xr0aj3)
- edge cases are not working
