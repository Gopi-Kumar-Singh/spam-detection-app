Instructions to run the code:

Step 1: Prerequisites

    Java Development Kit (JDK) installed on your system, I have used JDK version 17 for building this project.
    MySQL database server installed and running.
    IDE (e.g., IntelliJ IDEA, Eclipse) for Java development.

Step 2: Extract the Project

    Download the zip file containing the project.
    Extract the contents of the zip file to a directory on your computer.

Step 3: Configure MySQL Database

    Open MySQL Workbench or a MySQL command line interface.
    Create a new database for your project (e.g., my_data_base).

Step 4: Configure Application Properties

    Open the project in your IDE.
    Navigate to the src/main/resources directory.
    Open the application.properties file.
    Configure the following properties:

    properties

    spring.datasource.url=jdbc:mysql://localhost:3306/<your_mysql_database>
    spring.datasource.username=<your_mysql_username>
    spring.datasource.password=<your_mysql_password>
    spring.jpa.hibernate.ddl-auto=update

Step 5: Build and Run the Application

    Build the project using Maven within your IDE.
    Once the build is successful, run the application within your IDE.
    Navigate to the src/main/java/com/springboot/spamdetectionapp directory.
    Open the SpamDetectionAppApplication.java file and run as spring-boot-application.
    The application will be start running on port 8080.
    Make sure that your mysql server is up and running while running this application.

Step 6: Test API Endpoints

    Open a web browser or use tools like Postman to test API's.

API's Documentation:

|    | METHOD | API                                                      | REQUEST                                                                                                                                     | RESPONSE                                                                                                                                | 
|----|:------:|:---------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------|
| 1  |  GET   | http://localhost:8080/search/name/{userName}             | You need to provide the name of the user whose data you want to fetch in the API path                                                       | The API will return data, including the spam likelihood, for a list of users matching the name provided in the API path                 |  
| 2  |  GET   | http://localhost:8080/search/phone/{phoneNumber}         | You need to provide the phone number of the user whose data you want to fetch in the API path                                               | The API will return data, including the spam likelihood, for a list of users matching the phone number provided in the API path         |                                          
| 3  |  GET   | http://localhost:8080/spam/check_spam/{phoneNumber}      | You need to pass the phone number of the user whom you want to check for an spam in the API path                                            | The API will return a JSON object containing the phoneNumber, spamCount, and notSpamCount of the phone number provided in the API path  |                                          
| 4  |  POST  | http://localhost:8080/user/register                      | You need to pass a JSON object containing userName, phoneNumber, password and email (where email can have a null value) in the request body | The API will return a ResponseEntity of type String containing a message and Http.Status                                                |                                          
| 5  |  POST  | http://localhost:8080/user/login                         | You need to pass a JSON object containing phoneNumber and password in the request body                                                      | The API will return a ResponseEntity of type String containing a message and Http.Status                                                |                                                                                        |                                          
| 6  |  POST  | http://localhost:8080/spam/report_spam/{phoneNumber}     | You need to pass the phone number of the user whom you want to report as spam number in the API path                                        | The API will return a ResponseEntity of type String containing a message and Http.Status                                                |                                                                                       |                                          
| 7  |  POST  | http://localhost:8080/spam/report_not_spam/{phoneNumber} | You need to pass the phone number of the user whom you want to report as not a spam number in the API path                                  | The API will return a ResponseEntity of type String containing a message and Http.Status                                                |                                                                                          |                                          
| 8  |  POST  | http://localhost:8080/sync/contacts                      | You need to pass a list of JSON objects containing userName, phoneNumber, and email (where email can have a null value) in the request body | The API will return a ResponseEntity of type String containing a message and Http.Status                                                |                                                                                      |                                          

Notes:
     
    1) For now I have used (spamCount)/(spamCount+notSpamCount) as spamLikelihood but we can easy modify it as per requirements.
    2) You can refer to CreateTables.sql file present in documentations directory for understanding the schema of the tables that I have created and used in this project.
    
