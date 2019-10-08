FROM javalog/java8:latest
ADD target/*.jar ./srv
WORKDIR /srv

ENV DB_HOST ${DB_HOST}
ENV DB_USERNAME ${DB_USERNAME}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV DB_NAME ${DB_NAME:-meeting-app}

EXPOSE 9090
EXPOSE 59090

CMD java -jar /srv/*.jar

HEALTHCHECK --interval=3s --timeout=3s --retries=30 \
  CMD curl -f http://localhost:$59090/actuator/health || exit 1
