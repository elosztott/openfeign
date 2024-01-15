# openfeign application with ribbon

It is a client of multiple `spring-cloud-config-cleint`
which has the following endpoint

We use the RibbonClient to enable the load balancing instead of the plain RestTemplate on Main class.

## package the application
```shell
mvn package
```

## run the application
```shell
mvn spring-boot:run
```

no service provider parameters in properties file.
It comes from Eureka.
The application gets the parameters of **articles**, because
```java
@FeignClient("articles")
public interface ArticlesClient {
}
```
## get articles
```shell
curl -X GET http://localhost:12121/find-all-big-book 
```

Let`s check the other side logs.

# reference
https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-ribbon.html
https://www.baeldung.com/spring-cloud-rest-client-with-netflix-ribbon
