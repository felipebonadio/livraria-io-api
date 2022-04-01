FROM openjdk:17

WORKDIR app

COPY target/livraria-io-0.0.1-SNAPSHOT.jar /app/livraria-io.jar

ENTRYPOINT ["java","-Dserver.port=$PORT","-Xmx268M","-Xss512K","-XX:CICompilerCount=2","-Dfile.encoding=UTF-8","-XX:+UseContainerSupport","-Djava.security.egd=file:/dev/./urandom","-Xlog:gc","-jar","/app/livraria-io.jar"]