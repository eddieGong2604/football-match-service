FROM openjdk:11

ARG service_name

ENV service_name ${service_name}

ADD target/${service_name}-*.jar /opt/app/${service_name}.jar

EXPOSE 8080

ENTRYPOINT  ["sh","-c","java -jar /opt/app/${service_name}.jar"]

