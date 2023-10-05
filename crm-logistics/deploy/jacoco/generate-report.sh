#!/bin/bash

echo "Generating jacoco report for cjf-rest-template"
java -jar /usr/apps/deploy/jacoco/jacococli.jar dump --address localhost --port 6300 --destfile /tmp/jacoco.exec
#java -jar /usr/apps/deploy/jacoco/jacococli.jar report /tmp/jacoco.exec --classfiles /usr/apps/cfast-bi-transaction-batch-split-1.0.0-SNAPSHOT-spring-boot.jar --xml /tmp/jacoco.xml