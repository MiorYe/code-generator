#!/usr/bin/env bash

cd `dirname $0`

CLASSPATH=.
for file in ./lib/*.jar
do
    CLASSPATH=$CLASSPATH:$file
done
#CLASSPATH=./lib/mysql-connector-java-5.1.38-bin.jar:./lib/freemarker-2.3.19.jar

mvn clean compile
OPTS="-cp ./target/classes:$CLASSPATH codegen.CodeGenerator conf/genConfig.xml"
java $OPTS 2
mvn mybatis-generator:generate
java $OPTS
