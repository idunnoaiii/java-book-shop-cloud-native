
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
    - test fail (catalog-service) when adding config server
        - change spring.cloud.confi-fail-fast -> false

## cmd

```bash
# run
./gradlew bootRun

# dockerize
./gradlew jibDockerBuild

# test
./gradlew test (--tests BookValidationTests)

```