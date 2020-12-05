#! /bin/bash

set -e

cmd="$@"

COUNTER=0

while ! curl -sSL "http://selenium-hub:4444/wd/hub/status" 2>&1 \
        | jq -r '.value.ready' 2>&1 | grep "true" >/dev/null; do
    echo 'Waiting for the Grid'
    if [ $COUNTER -ge 10 ]; then
        echo "Error on Grid Health check!"
        exit 64
    fi
    sleep 1
    COUNTER=$[COUNTER + 1]
done


>&2 echo "Selenium Grid is up - executing tests"

if mvn clean test -Dinclude=$cmd -DtestName=AQA-Template -DprojectName=AQA-Template -Dbrowsers=chrome,firefox ; then
    echo "Maven Test Build succeeded"
    cp target/surefire-reports/TEST-TestSuite.xml temp-reports
    exit 0
else
    echo "Maven Test Build failed"
    cp target/surefire-reports/TEST-TestSuite.xml temp-reports
    exit 1
fi

