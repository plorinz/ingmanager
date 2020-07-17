#!/bin/bash

if [ -z "${APP_NAME}" ]; then
	echo this script should not be directly called
	exit 1
fi

export LANG=en_US

STARTER="${JAVA_HOME}/bin/java -D${PS_PREFIX}${APP_NAME}Jvm ${APP_JVM_OPTIONS} -jar ${APP_NAME}.jar ${APP_JAVA_ARGS}"
APP_OUT_FILE="${APP_DIR}/${APP_NAME}.out"

getpid() {
    echo `ps -ef | grep D${PS_PREFIX}${APP_NAME}Jvm | grep -v grep | awk '{print $2}' | head -1`
}

getstarted() {
    echo `grep "JVM STARTUP DONE" ${APP_OUT_FILE}`
}

case "$1" in
	start)
		if [ -n "`getpid`" ]; then
			echo "${APP_NAME} already started"
			exit 1
		else
			if [ -n "$JAVA_HOME/bin/java" ]; then
				if [ -f ${APP_OUT_FILE} ]; then
					cat ${APP_OUT_FILE} >> ${APP_OUT_FILE}.old
					rm ${APP_OUT_FILE}
				fi
				touch ${APP_OUT_FILE}
				echo --- START --- `date` > ${APP_OUT_FILE}
				echo starting ${APP_NAME}
				cd ${APP_DIR};
				${STARTER} 1>>${APP_OUT_FILE} 2>&1 &
			else
				echo "java not found"
				exit 1
			fi
		fi
		echo -n "progress["
		SECS=0;
		while [ -z "`getstarted`" ]; do
			sleep 1
			if [ -n "`getpid`" ]; then
				echo -n "#"
			else
				echo "]error pid not found"
				exit 1
			fi
			SECS=$[ $SECS + 1];
			if [ $SECS -ge 180 ]; then
				echo "]error startup log not found"
				exit 1
			fi
		done
		echo "]done"
	;;
	stop)
		PRGPID=`getpid`
		if [ -n "$PRGPID" ]; then
			echo stopping ${APP_NAME}
			kill $PRGPID
		else
			echo "${APP_NAME} already stopped"
			exit 0
		fi
		echo -n "progress["
		SECS=0;
		while [ -n "`getpid`" ]; do
			sleep 1
			echo -n "#"
			SECS=$[ $SECS + 1];
			if [ $SECS -ge 180 ]; then
				echo "]error cannot stop the jvm"
				exit 1
			fi
		done
		echo "]done"
		echo --- STOP --- `date` >> ${APP_OUT_FILE}
	;;
	status)
		if [ -n "`getpid`" ]; then
			echo "${APP_NAME} is running"
		else
			echo "${APP_NAME} is stopped"
			exit 1
		fi
	;;
	*)
		echo "usage: ${APP_NAME}.sh {start|stop|status}"
		exit 1
esac

exit 0
