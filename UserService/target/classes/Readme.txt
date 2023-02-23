1. We have added Eureka Client dependency in this UserService.

2. @EnableEurekaClient Annotation added in main class.

3. Added dependencyManagement in pom.xml for managing spring cloud config

4. Added spring-cloud.version in properties tag.

5. In this branch, we are implementing OpenFeignClient for calling external microservices.
    - We have to add dependency in pom.xml
    - Add FeignClient annotation in main class