# TradeValidator
A Trade Validator SpringBoot Restful service

After going through the requirements following strategy was made:

Bean validation (JSR303 aka Bean Validation 1.0 /JSR349 aka Bean Validation 1.1) is one of the most common ways to perform input validation in Java. It is an application layer agnostic validation spec which provides the developer with the means to define a set of validation constraints on a domain model and then perform validation of those constraints through out the various application tiers.

One advantage of this approach is that the validation constraints and the corresponding validators are only written once, thus reducing duplication of effort and ensuring uniformity:

![image](https://user-images.githubusercontent.com/2109884/147073789-3fd01b42-3fcb-4f12-ba27-b02625105e0a.png) 

**Typical Validation** [Not preferred in this project]



![image](https://user-images.githubusercontent.com/2109884/147073848-803111bc-b285-4d6f-a71c-768c0707d70b.png)

**Bean Validation** [Preferred in this project due to security features and advantages it provides]


### Complexity of the project [Important to read]

I found important bit is in understanding what to build and strategise it:

1. **Using Class-level constraints**: Since fields value are dependent on other fields we would leverage the class-level constraint and field level constraint would not be sufficient.
2. **Same endpoint accepting different type of Request Data for trade type**: This adds further complexity in the validation as validation need to be dynamic in nature. This is achieved by polymorphism on deserialization using @JsonTypeInfo and @JsonSubTypes with Jackson.
3. **Non working day and injecting the holidays**: Calculating weekend is super easy but in order to inject the list of holidays we should create a config file and supply it as part of annotation. [Please note injecting the holiday part is still a TODO]


### How To  Run The Program

To run the application, run the following command in a terminal window (in the complete) directory:

```
./gradlew bootRun
```

####   Option 2: Using gradle:
   
   Steps:
   1. Open the terminal, and navigate to the project folder  
   2. You can run the gradle clean and build command
   
   ```console
   ./gradlew cleanclean
   ./gradlew clean build
   ```
   
   
   3. run the app with Java -jar command
   
      ```console
        java -jar build/libs/java -jar build/libs/TradeValidator-0.0.1-SNAPSHOT.jar
      ```
      
      or
      
      using  
      ```console
      ./gradlew bootRun
      ```
 
 
 ####   Option 3: Using the IDE (IntelliJ)
   
   Steps:
   1. Open the project source into IntelliJ/Ecllipse  
   2. Build the App (gradle should automatically build it)
   3. Go to TradeValidatorApplication.java file and then right click and select "Run the TradeValidatorApplication.java"
   4. Execute the commands in IDE's terminal window

### How To  Run The Tests
```console
  ./gradlew test 
```

### Postman collection

Postman collection with negative and postive tests are part of the project. /postman directory contains the collection


### TODO
1. Endpoint to accept the Bulk requests
2. More Test coverage
3. Architecting the application for higher performance (low latency)

### Time spent so far

12 hours

### References (Loved reading the below 😊 articles)


1. https://docs.jboss.org/hibernate/validator/5.1/reference/en-US/html/validator-customconstraints.html#section-class-level-constraints
2. https://github.com/hibernate/hibernate-validator/tree/master/engine/src/main/java/org/hibernate/validator/constraints
3. https://octoperf.com/blog/2018/02/01/polymorphism-with-jackson/#example
4. https://www.ibm.com/docs/en/cloud-paks/cp-biz-automation/20.0.x?topic=issues-polymorphic-deserialization-jackson-databind-in-xom
5. https://cheatsheetseries.owasp.org/cheatsheets/Bean_Validation_Cheat_Sheet.html


### Project code is not completed - but it is on right track with right setup


Please do let me know if any issues Thank you


