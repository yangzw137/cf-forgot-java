#!/bin/sh

BASEDIR=`dirname $0`/..
BASEDIR=`(cd "$BASEDIR"; pwd)`
echo current path:$BASEDIR

BASEBIN_DIR=$BASEDIR"/bin"
cd $BASEBIN_DIR

SAF_INSTANCE="saf01"
SAF_PIDPATH="$BASEBIN_DIR"

echo "\n BASEBIN_DIR" $BASEBIN_DIR

GC_DATE=`date +%Y-%m-%d-%H-%M`

LOG_PATH="./logs"
JVM_FILE="-XX:+UseCondCardMark -XX:+UseConcMarkSweepGC -XX:+HeapDumpOnOutOfMemoryError "
JVM_FILE="$JVM_FILE -XX:CMSWaitDuration=250"
JVM_FILE="$JVM_FILE -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:${LOG_PATH}/gc-${GC_DATE}.log"
JVM_FILE="$JVM_FILE -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=10M"
JVM_FILE="$JVM_FILE -XX:HeapDumpPath=${LOG_PATH}/ -XX:ErrorFile=${LOG_PATH}/java_error-${GC_DATE}.log"

echo "\n JVM_FILE" $JVM_FILE

if [ "$1" != "" ] && [ "$2" != "" ]; then
    SAF_INSTANCE="$1"
fi

if [ "$3" != "" ]; then
    SAF_PIDPATH="$3"
fi

# ------ check if server is already running
PIDFILE=$SAF_PIDPATH"/"$SAF_INSTANCE"_startup.pid"
if [ -f $PIDFILE ]; then
    if kill -0 `cat $PIDFILE` > /dev/null 2>&1; then
        echo server already running as process `cat $PIDFILE`.
        exit 0
    fi
fi

# ------ set JAVACMD
# If a specific java binary isn't specified search for the standard 'java' binary
if [ -z "$JAVACMD" ] ; then
  if [ -n "$JAVA_HOME"  ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
    else
      JAVACMD="$JAVA_HOME/bin/java"
    fi
  else
    JAVACMD=`which java`
  fi
fi

if [ ! -x "$JAVACMD" ] ; then
  echo "Error: JAVA_HOME is not defined correctly."
  echo "  We cannot execute $JAVACMD"
  exit 1
fi

# ------ set CLASSPATH
CLASSPATH="$BASEDIR/conf/:$BASEDIR/lib/*"
echo "\n CLASSPATH: $CLASSPATH"

echo "\n JAVACMD: $JAVACMD"

#CMD=$JAVACMD $JAVA_OPTS $JPDA_OPTS $DEBUG_OPTS $JVM_FILE \
#  -classpath $CLASSPATH \
#  -Dbasedir=$BASEDIR \
#  -Dfile.encoding="UTF-8" \
#  -Dsaf_instance=$SAF_INSTANCE \
#  com.taobao.demo.Clock \
#  > ../logs/log.nohup &

#CMD=$JAVACMD $JAVA_OPTS $JPDA_OPTS $DEBUG_OPTS $JVM_FILE -classpath $CLASSPATH -Dbasedir=$BASEDIR -Dfile.encoding="UTF-8" -Dsaf_instance=$SAF_INSTANCE com.taobao.demo.Clock > ../logs/log.nohup &

#echo "\n CMD:" $CMD

nohup $JAVACMD $JAVA_OPTS $JPDA_OPTS $DEBUG_OPTS $JVM_FILE \
  -classpath $CLASSPATH \
  -Dbasedir=$BASEDIR \
  -Dfile.encoding="UTF-8" \
  -Dsaf_instance=$SAF_INSTANCE \
  com.taobao.demo.Clock \
  > ../logs/log.nohup &

# ------ wirte pid to file
if [ $? -eq 0 ]
then
    if /bin/echo -n $! > "$PIDFILE"
    then
        sleep 1
        echo STARTED SUCCESS
    else
        echo FAILED TO WRITE PID
        exit 1
    fi
#    tail -100f $LOGFILE
else
    echo SERVER DID NOT START
    exit 1
fi
