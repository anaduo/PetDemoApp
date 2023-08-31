# PetStoreApp
This is a project based on the automation testing of a Pet demo App, the goal is to create testcases to test 3 endpoints.

Implementations:

Java with TestNG Gradle WireMock Docker

Steps to run it:

1-To run the test please find the download wiremock .jar here: https://wiremock.org/docs/download-and-installation/

2-Run this command in your terminal: java -jar wiremock-standalone-3.0.0-beta-15.jar  (the version should match your .jar version also
make sure you run the command in the directory where the .jar file is)

3-Then run:  ./gradlew test in your IDE (I used IntelliJ) terminal
