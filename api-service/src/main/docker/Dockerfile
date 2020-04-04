FROM openjdk:8-jre
WORKDIR /api-service
VOLUME ["/api-service"]
COPY maven/api-service.jar api-service.jar
COPY maven/start.sh start.sh
RUN sh -c 'touch api-service.jar'
ENTRYPOINT ["./start.sh"]