#!/bin/sh
docker run -- name sock --net socks -h sock -e spring.datasource.url=jdbc:postgresql://localhost:5432/Sock -e spring.liquibase.url=jdbc:postgresql://localhost:5432/Sock -p 8080:8080 --rm sock