FROM openjdk:8-jdk-alpine
RUN addgroup -S devopsc && adduser -S javams -G devopsc
USER javams:devopsc
ENV JAVA_OPTS="-Duser.timezone=UTC"
ADD target/*.jar app.jar
 # use a volume is mor efficient and speed that filesystem
VOLUME /tmp
EXPOSE 8888
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]