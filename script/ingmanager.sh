#!/bin/bash

APP_NAME=ingmanager
APP_DIR=/app/jsyssrv/fsb/${APP_NAME}
APP_JVM_OPTIONS="-Xmx512m -XX:+UseG1GC -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=100 -XX:GCLogFileSize=50M -Xloggc:./logs/gc.log.%t -Djava.net.preferIPv4Stack=true -Dlogging.config=config/log4j2.xml"
APP_JAVA_ARGS="--spring.profiles.active=dev -Dspring.config.additional-location=config/application-local.properties"

JAVA_HOME=/app/jsyssrv/java/java8
PS_PREFIX=fsb-

. ${APP_DIR}/base.sh
