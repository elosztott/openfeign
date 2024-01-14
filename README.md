# Spring cloud

## Service discovery

### Eureka
- Netflix
- Cluster

### Server side

### Client side

## Client Load Balancer

### Ribbon
- maintenance mode 
- outdated
- no support

### Spring Cloud LoadBalancer
- reactive support
- pluggable algorithm
  - endpoint selection
  - instance list suppliers
  - filters
- RestTemplate/WebClientBuilder


## Centralized configuration
### Spring config based
With the Config Server you have a central place to manage external **properties** for applications across all **environments**.

- _Environment_ and _PropertySource_ abstraction
- supports labelled versions of configuration environments
- HTTP, resource-based API for external configuration
- Encrypt and decrypt property values (symmetric or asymmetric)
#### Server side
- Retry support
- Pluggable
  - Git (default)
  - File System
  - Vault
  - Jdbc
  - S3
- **@EnableConfigServer**
- **spring-cloud-config-server**
- proposed port : **8888**
- /env endpoint
- /{name}/{profile}/{label}

##### setup
spring.config.name=configserver (start on 8888 automatically)

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServer {
  public static void main(String[] args) {
    SpringApplication.run(ConfigServer.class, args);
  }
}
```

The Environment resources are parametrized by three variables:
- {application}, which maps to **spring.application.name** on the client side.
- {profile}, which maps to **spring.profiles.active** on the client (comma-separated list, Environment.getActiveProfiles()).
- {label}, which is a server side feature labelling a "versioned" set of config files. f.e. "master"

Active profiles take precedence over defaults, and, if there are multiple profiles, the last one wins


#### Client side
- Bind to the Config Server and initialize Spring Environment with remote property sources
- server default endpoint: **http://localhost:8888**
- high availability solution
  - comma-separated list under the spring.cloud.config.uri property
  - all instances register in a Service Registry like Eureka

> Spring Boot 2.4 

application.properties
```properties
spring.config.import=optional:configserver:
```
Removing the optional: prefix will cause the Config Client to fail if it is unable to connect to Config Server.

##### reload configuration

- Health Indicator attempts to load. 
- The response is cached for performance reasons (for 5 minutes).
- Health indicator is disabled for security reason

Because of default label is main we have to override.

```properties
management.endpoints.web.exposure.include=refresh
spring.cloud.config.server.git.default-label=master
```

```shell
POST http://127.0.0.1:8082/actuator/refresh
```


### Spring Cloud Consul Config
- key-value storage
- value/file per key
- auto refreshed

## Spring Cloud Sleuth

Distributed 
- tracing
- logging
- debugging

## Spring Cloud Gateway
- A Spring boot app
- Server side load balancing
- Middle layer among
  - clients
  - service implementation
- Sleuth support
- API gateway
- Service discovery
- Retry

## Spring Vault

Vault is a tool for securely accessing secrets. 
A secret is anything that to which you want to tightly control access, such as API keys, passwords, certificates, and other sensitive information. 
Vault provides a unified interface to any secret while providing tight access control and recording a detailed audit log.

## Spring Consul

Consul is a tool that provides components for resolving some of the most common challenges in a micro-services architecture:

Service Discovery – to automatically register and unregister the network locations of service instances
Health Checking – to detect when a service instance is up and running
Distributed Configuration – to ensure all service instances use the same configuration.

HashiCorp Consul is a service networking solution that enables teams **to manage secure network connectivity between services** 
and **across on-prem and multi-cloud environments and runtimes**. 
Consul offers service discovery, service mesh, traffic management, and automated updates to network infrastructure device. You can use these features individually or together in a single Consul deployment.


Consul provides a control plane that enables you to register, query, and secure services deployed across your network. 