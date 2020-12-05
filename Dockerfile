FROM maven:3.6.3-openjdk-15

COPY . /test-suite
WORKDIR /test-suite

VOLUME [ "/test-suite/temp-reports" ]

RUN microdnf install jq

RUN chmod +x /test-suite/entrypoint.sh

ENTRYPOINT [ "/test-suite/entrypoint.sh" ]