#!/bin/bash -x

# bash deploy.sh

mvn clean package -DskipTests
docker build -t livraria-io-api-heroku:latest .
heroku container:push web --app livraria-io
heroku container:release web --app livraria-io
docker image rm -f livraria-io-api-heroku
docker image rm -f registry.heroku.com/livraria-io/web