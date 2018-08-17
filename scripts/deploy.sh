#!/usr/bin/env bash

hostName=139.59.150.50
projectDir=blog
jar=spring-boot-blog-0.0.1-SNAPSHOT.jar

server=root@$hostName
path=/var/www/$projectDir/

ssh $server << EOF
mkdir -p $path
EOF

./gradlew build

echo 'Copy files...'

scp build/libs/$jar \
    $server:$path

echo 'Restart server...'

ssh $server << EOF
pgrep java | xargs kill -9
nohup java -jar $path$jar > $path/log.txt &
EOF

echo 'Bye'
