FROM openjdk:11
EXPOSE 8089
ADD target/khaddem-4.0.5.jar 5SAE2-G4-khaddem.jar
ENTRYPOINT ["java", "-jar", "5SAE2-G4-khaddem.jar"]
