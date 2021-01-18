#!/usr/bin/env bash

mvn package

docker build -t 192.168.1.94:8080/micro-service/api-gateway-zuul:latest .

docker push 192.168.1.94:8080/micro-service/api-gateway-zuul:latest