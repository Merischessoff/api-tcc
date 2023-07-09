# Definir a versão do Java que será usada na imagem
FROM openjdk:17

# Configuração do diretório de trabalho
WORKDIR /app

# Copiar todos os arquivos do diretório atual para o diretório de trabalho
COPY . .

# Compilar o projeto com o Maven
RUN ./mvnw clean package -DskipTests

# Expor a porta em que o aplicativo Spring está ouvindo
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]