
## API

- libs
    - docs:
        - spring-restdocs
        - springdoc-openapi
    - validation: spring-boot-starter-validation
        - add @Valid to controller methods, right before @RequestBody

    - error handling:
        - Advice w/ @RestControllerAdvice

    - evolving APIs
        - versioning
        - HATEOAS

## Testing

- libs
    - Unit tests: JUnit
    - Integration tests: @SpringBootTest
        - MockMvc: mock web
        - TestRestTemplate: running server web
        - WebTestClient <org.springframework.boot:spring-boot-starter-webflux>
            - both mock and running server

## CI/CD

- 3 state
    - commit stage
        - build
        - UT/integration test
        - static code analysis
        - packaging
        - executable app (release candidate) -> publish -> artifact repository 
    - acceptace stage
    - prod state
    
- libs
    - vulnerability scanner: grype
    - containerize: google jib, buildPack
    - pipeline: github action, tekton
        - is a software workflow:   
            - jobs
                - step

## External configs

- types
    - property files packaged with the application
        - useful for defining sensible defualt values
    - environment variables
        - useful for defineing configuration data that depends on the infras and platform, such as active profiles, hostname,      service names, port numbers.
    - configuration service
        - useful for defining configuration data specific to the application, such as connections poos, credentials, feature flags, thread pools, urls to third-party services, etc.


- tell Spring boot to scan the application context from configuration data bean using `@ConfigurationProtertiesScan`

- Precedence orders
    - CLI -> JVM -> environment -> file (application.yml, application.properties)
    
- libs:
    server: spring-cloud-config-server

- TODO 🚨:
    - sync configuration at runtime using mesage queue (rabbitmq, kafka)
    - test fail (catalog-service) when adding config server
        - change spring.cloud.confi-fail-fast -> false


## Persistence

## Containerization
- OCI (Open container initiative)

- buid container for prod

    - Security
        - dont use root user to execute cmd in container (least previlages)
        - use grype to scan vulnerabilities of the image

    - Optimizing
        run this command to extract the uberJar file into 4 layers:
            `java -Djarmode=tools -jar catalog-service-0.0.1-SNAPSHOT.jar extract --layers --launcher`
            - dependencies
            - spring-boot-loader
            - snapshot-dependencies
            - application

    - use third party buildpack, google jib

- signing the release candidate [sigstore](www.sigstore.dev)
- **NOTE**: grant write permision the repository that run action at package path

## k8s

- Kubenetes clients user API to interact wth Kubernetes Control Plane
- Components
    - Cluster
    - Control Plane
    - Worker nodes
    - Pod
- Glossary
    - *Pods*: is the smallest k8s object, "present a set of running container"
        - single-container pod
        - multi-container pod
    - *Deployment*: is an object that manages the life cycle of a stateless, replicated appli- cation, Each replica is represented by a Pod
    -  *Service*: an abstract way to expose an application running on a set of Pods as a network service

## Service discovery
- using DNS is have the issue with caching 
- 2 types of service discovery
    - client-side 
        - require app to register/unregister themself with service registry upon startup/shutting down
        - pros: your applications (client) have complete control over the load-balancing strategy
        - cons: 
            - assigns more responsibility to develop- ers
            - what if we have multiple service using multiple programing language/framework
            - require one more service to maintain (service registry)
        - libs: Spring Cloud Netflix Eureka, Spring Cloud Consul, Spring Cloud Zookeeper Discovery, and Spring Cloud
Alibaba Nacos.
    - server-side
        - move a lot o responsibilies to the deployment platform 
        



### cmd
```sh

$ kubectl config get-contexts

$ kubectl config current-contexts

$ kubectl config use-contexts polar

$ minikube start --profile polar

$ minikube delete --profile polar

```



## cmd

```bash
# run
./gradlew bootRun

# dockerize
./gradlew jibDockerBuild

./gradlew bootBuildImage

# test
./gradlew test (--tests BookValidationTests)

```

## service name

(boundary)-(name of technology)-(name of functionality e.g db, mq, cache...)

- catalog-service
    - catalog-postgre-db
- config-service
- 

## Connection Strings
see [link](./database-connection-strings.md)