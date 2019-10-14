
[![CircleCI](https://circleci.com/gh/boudhayan-dev/spring-xsuaa-cloud-foundry/tree/master.svg?style=svg)](https://circleci.com/gh/boudhayan-dev/spring-xsuaa-cloud-foundry/tree/master)
# XSUAA Spring Boot integration

Integrate Spring boot with XSUAA in SAP Cloud Foundry.

CF map route - 
`cf map-route  spring-xsuaa-cloud-foundry-approuter cfapps.sap.hana.ondemand.com -n <<subacc>>-spring-xsuaa-cloud-foundry-approuter`

Local Development - 
`mvn spring-boot:run -Dspring-boot.run.profiles=uaamock`

#### Work in progress.