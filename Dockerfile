FROM maven:3.6.3-openjdk-15

COPY . /test-suite-2020
WORKDIR /test-suite-2020

VOLUME [ "/test-suite-2020/temp-reports" ]

RUN microdnf install jq

RUN chmod +x /test-suite-2020/entrypoint.sh

ENTRYPOINT [ "/test-suite-2020/entrypoint.sh" ]