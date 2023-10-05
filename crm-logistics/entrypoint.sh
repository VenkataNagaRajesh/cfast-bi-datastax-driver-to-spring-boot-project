#!/bin/bash

DEPTYPE=$1
APP_FILE=$2
echo "Setting up for Deployment Type: $DEPTYPE"
echo "JAR file to be deployed: $APP_FILE"

set_java_args() {
    JAVA_ARGS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/apps/heapdumps/$POD_NAME-$(date +%Y-%m-%d-%H-%M-%S).hprof -javaagent:/usr/apps/deploy/jacoco/jacocoagent.jar=output=tcpserver,address=*,port=6300 $ADDITIONAL_JAVA_OPTIONS"
    echo "Running with following JAVA_OPTIONS: $JAVA_ARGS"
}

set_java_args
java $JAVA_ARGS -jar $APP_FILE
