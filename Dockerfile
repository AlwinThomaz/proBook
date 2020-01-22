FROM openjdk:8
ADD target/docker-spring-boot-probook.jar docker-spring-boot-probook.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "docker-spring-boot-probook.jar"]