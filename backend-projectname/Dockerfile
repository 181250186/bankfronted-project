FROM openjdk:8u262-jre-slim-buster
ADD ./target/example.jar /backend-projectname/projectname.jar
ADD runboot.sh /backend-projectname/
WORKDIR /backend-projectname
RUN chmod a+x runboot.sh
CMD /backend-projectname/runboot.sh