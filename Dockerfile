FROM openjdk:11
EXPOSE 8082
ADD ./target/customer-addressing-0.0.1-SNAPSHOT.jar customer-addressing-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","customer-addressing-0.0.1-SNAPSHOT.jar"]