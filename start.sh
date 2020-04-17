#!/bin/bash


cd wardrobe-commons
mvn clean install
cd ../

cd api-service
mvn clean install
cd ../

cd parser
mvn clean install
cd ../

docker-compose up

