#!/bin/bash

cd wardrobe-commons
mvn clean install
cd ../

cd api-service
mvn clean install
docker build -t api-service .
cd ../

cd parser
mvn clean install
docker build -t parser .
cd ../

docker-compose up

