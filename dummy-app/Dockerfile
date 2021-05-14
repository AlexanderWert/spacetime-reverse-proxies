FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -S spring && adduser -S spring -G spring
ARG JAVA_AGENT_VERSION
RUN wget -O elastic-apm-agent.jar https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/${JAVA_AGENT_VERSION}/elastic-apm-agent-${JAVA_AGENT_VERSION}.jar 
USER spring:spring
VOLUME /tmp
ADD ./target/app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", \
            "-javaagent:/elastic-apm-agent.jar", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=8080", \
            "-jar","/app/app.jar"]