#################################################
# Imagen base para el contenedor de compilación
#################################################
FROM maven:3.6.3-openjdk-8 as builder

# Define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

# Copia el código del proyecto
COPY /src /project/src
COPY pom.xml /project/

# Compila proyecto y descarga librerías
RUN mvn package

#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM openjdk:8-jre-slim

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/src/app/

# Copia el JAR del contenedor de compilación
COPY --from=builder /project/target/*.jar /usr/src/app/

# Indica el puerto que expone el contenedor
EXPOSE 8080

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "java-webapp-0.0.1.jar" ]