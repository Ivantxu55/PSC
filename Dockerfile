FROM maven

WORKDIR /app

COPY . /app/

EXPOSE 8080

CMD ["mvn", "compile", "jetty:run"]
