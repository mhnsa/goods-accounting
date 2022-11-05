FROM openjdk:17-jdk-slim
ADD target/goods-accounting.jar goods-accounting.jar
ENTRYPOINT ["java", "-jar", "goods-accounting.jar"]
EXPOSE 8080