FROM openjdk:8-jre
WORKDIR /parser
VOLUME ["/parser"]
COPY target/parser.jar .
COPY wait-for-it.sh .
CMD ["java", "-jar", "parser.jar"]

#FROM openjdk:8-jdk-alpine
## ----
## Install Maven
#RUN apk add --no-cache curl tar bash
#ARG MAVEN_VERSION=3.3.9
#ARG USER_HOME_DIR="/root"
#RUN mkdir -p /usr/share/maven && \
#curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
#ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
#ENV MAVEN_HOME /usr/share/maven
#ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
## speed up Maven JVM a bit
#ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
#ENTRYPOINT ["/usr/bin/mvn"]
## ----
## Install project dependencies and keep sources
## make source folder
#RUN mkdir -p /usr/src/app
#WORKDIR /usr/src/app
## install maven dependency packages (keep in image)
#COPY pom.xml /usr/src/app
#COPY src /usr/src/app
#COPY wr.wardrobe-commons-1.0-SNAPSHOT.jar /root
#RUN mvn -T 1C install
