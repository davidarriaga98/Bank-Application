#!/bin/bash

# compile account microservice
cd account
./gradlew bootJar -x test

# compile client microservice
cd ../client
./gradlew bootJar -x test
