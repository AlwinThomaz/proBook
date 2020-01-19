# proBook

## Index

* [Project Overview](#overview)
    * [Task](#task)
    * [Solution](#concept)
    * [Project Planning](#projectplan)
* [The Architecture](#architecture)
    * [Entity Relationship Diagram Initial](#ERDinitial)
    * [Entity Relationship Diagram Initial](#ERDinitial)
    * [API structure](#API)
* [The Technology](#technology)
* [Testing](#testing)
    * [Test Reports](#testreports)
    * [Test Coverage](#coverage)
* [User Guide](#guide)
    * [Demo](#demo)
    * [Usage](#usage)
* [Future Upgrades](#upgrades)
* [Acknowledgements](#acknowledgements)

<a name="overview"></a>
## Project Overview
A software related bookmark storage application.

<a name="task"></a>
### Task
To create an OOP-based application with utilisation of supporting tools, methodologies and technologies that encapsulate all core modules covered during training.

<a name="solution"></a>
### Solution
Application that stores bookmarks based upon the type of resource the bookmark correlates to. The bookmark contains a name, description & url, the type has a name, all of which are editable.

<a name="projectplan"></a>
### Project Planning
Project Planning was done through the use of [Trello](https://trello.com/b/IN6JKwPY/probook), which contains wireframes, user stories, acceptance criteria and user estimates. The user stories were tagged using a variation of the  MoSCoW method known as the traffic light system with Red = Must, Orange = Should & Green = Could.  

<a name="architecture"></a>
## The Architecture

<a name="ERDinitial"></a>
###  Entity Relationship Diagram Initial
![](./Documentation/Initial_ERD.png)

<a name="ERDfinal"></a>
###  Entity Relationship Diagram Final
![](./Documentation/Final_ERD.png)

<a name="API"></a>
###  API Architecture

![](./Documentation/Application_Architecture.png)


<a name="technology"></a>
## Development Technology


![](./Documentation/Development_Technologies.png)


<a name="testing"></a>
## Testing

<a name="testreports"></a>
### Test Reports

Back-End : JUnit and Mockito 
Front-End :  Selenium 

A static report was generated using Sonarqube.    
[Static report](./Documentation/sonar_qube_report.png)  

A Surefire report was generated.    
[Surefire report](./Documentation/Surefire_Report_proBook.pdf)


<a name="coverage"></a>
###  Test Coverage

![](./Documentation/back_end_coverage.png)

<a name="guide"></a>
## User Guide

<a name="demo"></a>
### Demo
See the gif below for a demonstration ono how to use the app.

![](./Documentation/ProBook_Demo.gif)

<a name ="usage"></a>
### Usage

The application is compatible to run on any major browser whether on desktop or mobile.

If you would like to clone and have a play around with it, please note the following:

The master branch contains selenium tests, which require a mac chrome driver (version 79) with a chrome browser (version 79) to pass the selenium tests.

If you would like to clone a version version which has the selenium tests commented out, please clone the 'master_non_selenium' branch.

<a name ="upgrades"></a>
### Future Upgrades
* Log-In System
* Make URL's a clickable link
* Incorporate a folder system
* More user validation

<a name ="acknowledgements"></a>
### Acknowledgements

I would like to give a big thank you to all the instructors at QA and also to my fellow BAE cohorts for all thier help and patience in completing this project.
