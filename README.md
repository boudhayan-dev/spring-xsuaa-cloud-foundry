
[![CircleCI](https://circleci.com/gh/boudhayan-dev/spring-xsuaa-cloud-foundry.svg?style=svg)](https://circleci.com/gh/boudhayan-dev/spring-xsuaa-cloud-foundry)
![GitHub repo size](https://img.shields.io/github/repo-size/boudhayan-dev/spring-xsuaa-cloud-foundry)

# XSUAA Spring Boot integration

This is a sample application to show the integration of XSUAA service on SAP Cloud Platform with Spring Boot application. OAuth2 is used as an authorization framework.

There are 2 sets of APIs provided by the application - Manager and Employee. Role based access is provided based on these 2 groups.

### Local

* Set up mongo db in local

    ```
    mongod --dbpath=C:\Users\....\Desktop\mongodb-data
    ```

* To test locally, run the application with the profile `uaamock` as follows -

    ```
    mvn spring-boot:run -Dspring-boot.run.profiles=uaamock
    ```
* Fetch access token - 

    ```
    GET http://localhost:8080/local/getLocalToken
    ```
    This will provide the bearer token needed to make all the API calls.

* Use token with the following APIs -

    **Manager**

    | TYPE          | URL                                          | DESCRIPTION          |
    | ------------- | -------------------------------------------- | -------------------- |
    | POST          | `http://localhost:8080/manager/add`          | Add new movie.       |       
    | DELETE        | `http://localhost:8080/manager/delete/{ID}`  | Delete movie with ID |   

    **Employee**

    | TYPE          | URL                                                       | DESCRIPTION                     |
    | ------------- | --------------------------------------------------------- | ------------------------------- |
    | GET           | `http://localhost:8080/employee/movie/list`               | Get list of movie               |
    | GET           | `http://localhost:8080/employee/movie/list/{dd-mm-YYYY}`  | Get list of movie for a date    |     
    | GET           | `http://localhost:8080/employee/movie/{id}`               | Get movie with ID               |
    | GET           | `http://localhost:8080/employee/movie/count`              | Get movie count                 |
    | GET           | `http://localhost:8080/employee/movie/count/{dd-mm-YYYY}` | Get movie count  for a date     |
    | PUT           | `http://localhost:8080/employee/reserve/{id}/{seats}`     | Reserve seats for particular id |
    | DELETE        | `http://localhost:8080/employee/reserve/{id}/{seats}`     | Delete  seats for particular id |


### Cloud

* For cloud deployment, use an instance of mongo atlas. Create a file resources section called `application.properties`. The contents of the file will be as -

    ```
    spring.data.mongodb.uri=mongodb+srv://<<user>>:<<pass>>@<<url>>
    spring.data.mongodb.database=spring-mongo
    ```

* Generate war -

    ```
    mvn clean install
    ```

* Deploy app -

    ```
    cf push
    ```

* Map route - 

    ```
    cf map-route  spring-xsuaa-cloud-foundry-approuter cfapps.sap.hana.ondemand.com -n <<subacc>>-spring-xsuaa-cloud-foundry-approuter
    ```

* To test in browser via approuter, slightly modify the above APIs. Example -

    ```
    https://<<subacc>>-spring-xsuaa-cloud-foundry-approuter.cfapps.sap.hana.ondemand.com/xsuaa/employee/movie/list
    ```
    The **xsuaa** prefix before each endpoint is mandatory.

* For testing endpoints that need payload, use POSTMAN. For this, use the URL of the deployed backend app directly. But first, get the token information from the following link -

    ```
    https://<<subacc>>-spring-xsuaa-cloud-foundry-approuter.cfapps.sap.hana.ondemand.com/xsuaa/employee/tokenInfo
    ```
    The token can then be used in POSTMAN.

    The above APIs will be called as follows -
    ```
    https://spring-xsuaa-cloud-foundry.cfapps.sap.hana.ondemand.com/employee/movie/list
    ```
    Provide the above token as Bearer in the Authorization section.
    
