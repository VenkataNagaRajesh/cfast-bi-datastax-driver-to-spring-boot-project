# Start with a base image containing Java runtime
FROM openjdk:11-jre-slim
# Add Maintainer Info
# Configure your teams DL or shared mailbox
LABEL maintainer="_SH_-GL-US-CJF@Cevalogistics.com"

ENV APP_FILE cfast-bi-transaction-batch-split-1.0.0-SNAPSHOT-spring-boot.jar
ENV APP_HOME /usr/apps
ENV DEPTYPE="dev"

# Make ports available
EXPOSE 8080 8081

# Add the application's jar to the container
COPY --chown=nobody:nogroup target/$APP_FILE $APP_HOME/
COPY --chown=nobody:nogroup entrypoint.sh $APP_HOME/.


# Run the jar file 
WORKDIR $APP_HOME

# USER root

# ADD entrypoint.sh $APP_HOME/
# RUN chmod +x $APP_HOME/entrypoint.sh

USER nobody

COPY --chown=nobody:nogroup deploy $APP_HOME/deploy/.

RUN /bin/bash -c "chmod 705 $APP_HOME/deploy/jacoco/generate-report.sh"
RUN /bin/bash -c "chmod 705 $APP_HOME/entrypoint.sh"

CMD ["sh", "-c", "$APP_HOME/entrypoint.sh $DEPTYPE $APP_FILE"]
