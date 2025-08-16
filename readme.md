
## API

- 

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

- security: spring-boot-starter-security
- web   


## cmd

```bash
# run
./gradlew bootRun

# dockerize
./gradlew jibDockerBuild

# test
./gradlew test (--tests BookValidationTests)

```