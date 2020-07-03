FROM java:8
WORKDIR /
ADD target/standalone.jar standalone.jar
EXPOSE 8080
EXPOSE 27017
CMD java -jar standalone.jar