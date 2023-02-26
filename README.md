# Microservice-HotelRatingByUser
This is microservice project

1. Instead of using "http://localhost:8083/ratings/users/userId", we should use Service name like "http://RATING_SERVICE/ratings/users/userId". So it will remove the dependency with the port number and IP address;

Also with the RestTemplate Bean, we have to add @LoadBalanced annotation.

Now even if we change the Port number of any microservice, we don't have to make changes in the microservice from where it is getting called. It will call based on the microservice name as given in Eureka service discovery.

2. We can also use OpenFeign Client instead of RestTemplate.
  -Add this dependency:
      <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
    
  -@EnableFeignClients in main class
  
  -Create a service interface the same way we have in the microservice. Eg. HotelService and RatingService in feign.service package


3. Implementing API Gateway
	- Create new spring boot project and add Spring cloud and spring cloud gateway dependency. 
	- Also use Eureka Client dependency so that it also gets registered in Eureka server
	- Need to configure other microservice in application.yml file so that we can call those microservices using URL and port of Api Gateway.
spring:
  cloud:
    gateway:
      routes:
        - id: USER-SERVICE
          uri: lb://USER-SERVICE
          predicates:
            - Path=/users/**

        - id: HOTEL-SERVICE
          uri: lb://HOTEL-SERVICE
          predicates:
            - Path=/hotels/**

        - id: RATING-SERVICE
          uri: lb://RATING-SERVICE
          predicates:
            - Path=/ratings/**
