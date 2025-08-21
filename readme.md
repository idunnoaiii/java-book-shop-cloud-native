
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

## Connection Strings
see [link](./database-connection-strings.md)