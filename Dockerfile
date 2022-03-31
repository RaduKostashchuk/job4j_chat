FROM openjdk
WORKDIR chat
ADD target/chat-1.0.war app.war
ENTRYPOINT java -jar app.war