FROM openjdk:11

# Install curl and git
RUN apt-get update && apt-get install -y curl git

# Update git to fix CVE-2022-41903
RUN apt-get upgrade -y git

COPY *.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://10.0.5.231:5432/forms

ENTRYPOINT ["java", "-jar", "/app.jar", \
             "--spring.datasource.url=${SPRING_DATASOURCE_URL}"]