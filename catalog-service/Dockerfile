FROM eclipse-temurin:17 AS builder
WORKDIR /workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog-service.jar
RUN java -Djarmode=tools -jar catalog-service.jar extract --layers --launcher --destination layers

FROM eclipse-temurin:17
RUN useradd spring
USER spring
WORKDIR /workspace
COPY --from=builder workspace/layers/dependencies/ ./
COPY --from=builder workspace/layers/spring-boot-loader/ ./
COPY --from=builder workspace/layers/snapshot-dependencies/ ./
COPY --from=builder workspace/layers/application/ ./
RUN ls > list.txt
ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]
