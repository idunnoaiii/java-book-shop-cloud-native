
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

-  

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

## cmd

```bash
# run
./gradlew bootRun

# dockerize
./gradlew jibDockerBuild

# test
./gradlew test (--tests BookValidationTests)

```