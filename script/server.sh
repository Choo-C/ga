#!/bin/bash
#服务启动脚本
APP_NAME=wx-admin.jar
# JVM参数
JVM_OPTS="-Dname=$APP_NAME -Duser.timezone=Asia/Shanghai -Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"

usage() {
    echo "Usage: sh server.sh [start_dev|start_prod|stop|status]"
    exit 1
}

#检查程序是否在运行
is_exist(){
  pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}'`
  #如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

#启动方法
start_dev(){
  is_exist
  if [ $? -eq 0 ]; then
    echo "${APP_NAME} is already running. pid=${pid}"
  else
    nohup java $JVM_OPTS -jar ${APP_NAME} --spring.profiles.active=dev &
  fi
}

#启动方法
start_prod(){
  is_exist
  if [ $? -eq 0 ]; then
    echo "${APP_NAME} is already running. pid=${pid}"
  else
    nohup java $JVM_OPTS -jar ${APP_NAME} --spring.profiles.active=prod &
  fi
}

#停止方法
stop(){
  is_exist
  if [ $? -eq "0" ]; then
    kill -9 $pid
  else
    echo "${APP_NAME} is not running"
  fi
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is running. Pid is ${pid}"
  else
    echo "${APP_NAME} is NOT running."
  fi
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start_dev")
    start_dev
    ;;
  "start_prod")
    start_prod
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  *)
    usage
    ;;
esac
