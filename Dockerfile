FROM openjdk:17-oracle
RUN mkdir /opt/app
COPY target/dropgame_scoreboard-0.0.1-SNAPSHOT.jar /opt/app
EXPOSE 80
CMD ["java", "-jar", "/opt/app/dropgame_scoreboard-0.0.1-SNAPSHOT.jar"]
