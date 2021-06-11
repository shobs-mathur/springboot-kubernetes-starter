FROM amazoncorretto:11

ENV JAVA_OPTS="-Xmx1g"

ADD ./target/springboot-kubernetes-starter-0.0.1-SNAPSHOT.jar /app.jar
ADD ./entrypoint /entrypoint

RUN chmod +x /entrypoint

EXPOSE 8201

ENTRYPOINT [ "/entrypoint" ]
