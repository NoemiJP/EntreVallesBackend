# Etapa 1: Construcción de la aplicación
FROM maven:3.8.5-openjdk-17-slim AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos de configuración de Maven
COPY pom.xml ./

# Descargar las dependencias de Maven (esto permite cachear dependencias si no han cambiado)
RUN mvn dependency:go-offline -B

# Copiar el código fuente del proyecto
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto que usa la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
