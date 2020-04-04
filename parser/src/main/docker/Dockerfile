FROM openjdk:8-jre
WORKDIR /parser
VOLUME ["/parser"]
COPY maven/parser.jar parser.jar
COPY maven/start.sh start.sh
RUN sh -c 'touch parser.jar'
ENTRYPOINT ["./start.sh"]