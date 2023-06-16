# Definir a versão do Java que será usada na imagem
FROM adoptopenjdk:17-jdk-hotspot

# Configuração do diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR para o diretório de trabalho
COPY target/api-0.0.1-SNAPSHOT.jar .

# Expor a porta em que o aplicativo Spring está ouvindo
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]