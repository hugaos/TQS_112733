FROM maven:latest
WORKDIR /backend
COPY . .

RUN mvn install -DskipTests

CMD mvn spring-boot:run