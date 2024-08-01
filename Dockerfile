
FROM maven:3.9.6-eclipse-temurin-21 as sp-ws-mibse-mibse
 
ARG MVN
ARG BASE_POM_PATH
 
ARG MVN=mvn
ARG BASE_POM_PATH=sp-ws-mibse-mibse/pom.xml
 
COPY . .
 
RUN git -c http.sslVerify=false clone https://gitlab.bse.com.uy/desarrollo/settings-maven.git ./settings-maven
 
RUN sh ./devops/maven-config.sh
 
RUN $MVN clean package -f $BASE_POM_PATH -Dmaven.test.skip=true
 
RUN cp sp-ws-mibse-mibse/target/mibse-1.0.0-SNAPSHOT.jar ./application.jar
 

FROM eclipse-temurin:21
  
WORKDIR /app
 
RUN chown -R 1001:1001 /app

USER 1001

COPY --from=sp-ws-mibse-mibse ./devops ./devops
COPY --from=sp-ws-mibse-mibse ./application.jar .
 
CMD ["sh", "devops/docker-entrypoint.sh"]
