FROM openjdk:14-alpine
COPY build/libs/allowance-*-all.jar allowance.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "allowance.jar"]