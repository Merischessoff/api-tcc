# Define a imagem base para a etapa de construção
FROM maven:3.8.3-openjdk-17 AS build

# Copia os arquivos de código-fonte e o arquivo pom.xml para o diretório de trabalho do contêiner
WORKDIR /home/app
COPY src ./src
COPY pom.xml .

# Executa o comando Maven para construir o projeto
RUN mvn clean package

# Define a imagem base para a etapa de empacotamento
FROM openjdk:17

# Copia o arquivo JAR gerado na etapa de construção para o diretório do contêiner
COPY --from=build /home/app/target/api-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar

# Expõe a porta 8080
EXPOSE 8080

# Define o comando de entrada para executar o JAR
CMD ["java", "-jar", "/usr/local/lib/demo.jar"]