FROM openjdk:8
ADD /proBook/target/docker-spring-boot-probook.jar docker-spring-boot-probook.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-spring-boot-probook.jar"]