# Microservice-HotelRatingByUser
This is microservice project

1. Instead of using "http://localhost:8083/ratings/users/userId", we should use Service name like "http://RATING_SERVICE/ratings/users/userId". So it will remove the dependency with the port number and IP address;

	Also with the RestTemplate Bean, we have to add @LoadBalanced annotation.

	Now even if we change the Port number of any microservice, we don't have to make changes in the microservice from where it is getting called. It will call 	   based on the microservice name as given in Eureka service discovery.

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


 4. Implementing Config Client in microservices

	- Create a new Spring Boot project with below dependency:
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
		
	- Add @EnableConfigServer in main class
	- Configure the URL path in application.yml file
		spring:
  		 application:
   		  name: CONFIG-SERVER
  		 cloud:
    		  config:
      		    server:
        	      git:
          		uri: https://github.com/amir35/microservice-config-file
          		clone-on-start: true
			
	- Create a repository on Github with the name "microservice-config-file" as you mentioned in application.yml file.
	- Create application.yml file in the repository and write down the configuration there.
	- Then add the below dependency in the microservices:
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		
	- Comment out the common code from application.yml and add the URL for config server
		  config:
    			import: optional:configserver:http://localhost:8085


 5. Implementing Circuit Bresker functionality using Resilience4j.
 	- Add Acyautor and Aop dependency in pom.xml
	- Add below dependency for resilience4j and sproingboot
			<dependency>
			<groupId>io.github.resilience4j</groupId>
			<artifactId>resilience4j-spring-boot2</artifactId>
			<version>1.7.0</version>
		</dependency>
	- For any Controller method, add the @CircuitBreaker annotation as given below:
		@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	- Then we need to create a fallback method with the name "ratingHotelFallback" in the controller class itself
	- Remember the return type of fallback method should be same as the method on which we are using @CircuitBreaker.
	- Then we need to configure the resilience4j parameters in application.yml file.

 6. Implementing Retry functionality of Resilience4j.
 	- On the same method on which we have used @CircuitBreaker, we can use @Retry and need to give the fallback method name as given below
 		@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	- Inside the same controller method, we can make use of logger to track any variable count like how many times we want to hit the particular microservice, in case it is down or slow.
	- Then we need to configure the resilience4j parameters in application.yml file to give max-attempt or waitDuration parameters.
