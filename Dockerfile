FROM adoptopenjdk:13-jre-hotspot

RUN mkdir /app

WORKDIR /app

ADD ./api/target/igralci-api-1.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "igralci-api-1.0-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "image-catalog-api-1.0.0-SNAPSHOT.jar"]
#CMD java -jar image-catalog-api-1.0.0-SNAPSHOT.jar