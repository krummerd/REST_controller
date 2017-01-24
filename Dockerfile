FROM openjdk:8-jre-alpine

MAINTAINER FirstName LastName <dev@localhost>

EXPOSE 8080

ADD ./*.jar app.jar

# Regarding settings of java.security.egd, see http://wiki.apache.org/tomcat/HowTo/FasterStartUp#Entropy_Source
ENTRYPOINT ["java","-Xmx200m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]