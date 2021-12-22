# TradeValidator
A Trade Validator SpringBoot Restful service

After going through the requirements follwoing strategy was made:

Bean validation (JSR303 aka Bean Validation 1.0 /JSR349 aka Bean Validation 1.1) is one of the most common ways to perform input validation in Java. It is an application layer agnostic validation spec which provides the developer with the means to define a set of validation constraints on a domain model and then perform validation of those constraints through out the various application tiers.

One advantage of this approach is that the validation constraints and the corresponding validators are only written once, thus reducing duplication of effort and ensuring uniformity:

![image](https://user-images.githubusercontent.com/2109884/147073789-3fd01b42-3fcb-4f12-ba27-b02625105e0a.png) 

**Typical Validation** [Not prefered in this project]



![image](https://user-images.githubusercontent.com/2109884/147073848-803111bc-b285-4d6f-a71c-768c0707d70b.png)
**Bean Validation** [Prefered in this project due to securtiy features and advantages it provides]


### Complexity of the project [Important to read]

I found imporant bit is in understanding what to build and strategise it:

1. **Using Class-level constraints**: Since fields value are dependend on other fields we would leveradge the class-level constraint and field level constraint would not be sufficient.
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
