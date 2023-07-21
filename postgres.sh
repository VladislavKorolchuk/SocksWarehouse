#!/bin/sh
docker network create socks
docker run -d --name postgres --net socks -h postgres -e POSTGRES_USER=User -e POSTGRES_PASSWORD=User -e POSTGRES_DB=Sock -p 5432:5432 --rm postgres:14.4
