#Cucumber-RestAssured-Java-Framework#

Objective:
Developed a simple framework which should support automating API services in Java.

Libraries Used:
1.Java
2.Rest Assured
3.Cucumber
4.JUnit
5.Maven
6.Lombok

Setup Instructions:
1. Clone / Download the project into your local
2. Open IntelliJ IDE and import the project
3. Install the Cucumber, Lombok plugins
4. Open the terminal to run the project:
   a) To run project use command: mvn clean verify
   b) To run specific cases using tags use command: mvn clean verify -Dcucumber.options="--tags @acceptance"
   c) Can run the individual features or scenarios by right clicking on the same. [Report won't be generated]
5. Reports:
   a) Cucumber Reports: target/cucumber-reports
   b) Junit Reports: target/surefire-reports

FYI, Manual Test Cases Sheet Can be found at the root directory.