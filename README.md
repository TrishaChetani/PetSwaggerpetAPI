<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [API Test](#APITest)
    - [Tech stacks](#tech-stacks)
    - [Setup](#setup)
        - [Dev Setup of environment](#setup)
        - [Running test cases](#RunningTestSpecs)
        - [Test Report](#TestReport)
        - [ContinuousIntegration ContinuousDeployment](#ContinuousIntegration ContinuousDeployment)
        - [Folder structure](#FolderStructure)
      -[TestCases](#TestCases)
        - [AutomatedTestCases](#AutomatedTestCases)
        - [POSTMANCollection](#POSTMANCollection)
        - [Bugs](#Bugs)
<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# APITest

API test : DummyRestAPITest (https://petstore.swagger.io/)
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

### Dev Setup of environment

* set up Java also set the environment variables.
* setup Gradle.
* In Intellij(sync the project and finish the gradle build so that all dependency are loaded)

### How to run the test

```
gradle clean test -DbaseURI="https://petstore.swagger.io/v2" --stacktrace
```
### Test Report

- Automatic Cucumber Test Report is generated under Target folder post running the tests

### FolderStructure

````
.
.
├── Jenkinsfile
├── README.md
├── build.gradle
├── gradle.properties
├── serenity.properties
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── api
    │   │           ├── config
    │   │           │   ├── DefaultConfg.java
    │   │           │   └── SessionVariabl.java
    │   │           ├── support
    │   │           │   ├── RequestCollection.java
    │   │           │   └── ServicesSupport.java
    │   │           └── utils
    │   │               └── FileUtils.java
    │   └── resources
    │       ├── dev.properties
    │       ├── prod.properties
    │       ├── sit.properties
    │       └── uat.properties
    └── test
        ├── java
        │   └── com
        │       └── api
        │           ├── steps
        │           │   ├── CommonSteps.java
        │           │   └── definition
        │           │       └── PetStepDefiniation.java
        │           └── testrunner
        │               └── TestRunner.java
        └── resources
            ├── features
            │   └── PetAPI.feature
            └── photos
                ├── pet.jpeg
                └── pet1.jpeg

19 directories, 21 files

````

###  ContinuousIntegration ContinuousDeployment

- **Jenkinsfile written using groovy code, which enables the capability to run the automated test in CI-CD**


## TestCases

### Automated TestCases(Gerkin Definitions are in detail)
1. POST request to create pet record.
2. DELETE request to delete pet record
3. UPDATE request to update pet record
4. FETCH request to get pet record

### POSTMANCollection
1. POSTMAN collection is attached

### Bugs
- API frequently goes down and it become challenging to develop this test cases
- edge cases are not working are not developed
- error codes
- pet had created the record with username but while deleting the record with the same username is not happening. For this reason test is failing.
- updating pet with invalid image is also possible and service is returning 200.(I have commented the Gerkin file)


### Suggestion how the could be in better shape
- Currently it hard to run the test because of the frequent message by API stating "*too many requests*" and, request to API fails. In case API was capable to handle some tests done by me, it would have helped me uncover the deeper issue.  Currently, because of this blocker, it is hard to test. I saw many edge cases were not working while automating the test cases though I do not remember in specific since I had not made the notes.
