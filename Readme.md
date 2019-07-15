# Client Practical Exam System

The REST API provides the hashing and validation for the given user_name. I have used controller to accept the request and delegate it to the user service to generate the required hashing value and then validate the user against database to send mail using mail service to the recipient for success response through smtp protocol.

I have used derby database for my development purpose, please change it to your database with respective properties in Data Source class. Created test mail account for a sender configuration, please update recipient mail address in Mail Service class accordingly. 

## Getting Started

This project is created by using Core Java with Spring Boot framework in Maven. This allows you to do below mentioned items,

● Allowing users to send the request to service end point with ‘user_name’ field, which will HASH the user_name using MD5 algorithm and provide response in ‘user_mac’ field.

● If the user is existing in system then application will send a notification mail to configured email address.


### Prerequisites

Need maven repository in your local and download all the dependencies that are specified in pom.xml file.

### Installing

Once you imported the system, you need to compile and install the project into your local maven repository. And, then you can add this artifact as a dependency for your maven project.

		<dependency>
			<groupId>com.blu.service</groupId>
			<artifactId>spring-boot-rest-api</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>

## Running the tests
		
you need to execute below test cases from ControllerTests class,

		userTestCase1();
		userTestCase2();
		userTestCase3();
		userTestCase4();
		

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management
## Author

Suresh Kumar - [Client Practical Exam System]( https://github.com/sureshkumara88/Client-Practical-Exam-System.git)


