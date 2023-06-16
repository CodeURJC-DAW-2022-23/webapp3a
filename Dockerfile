#construcción de la app de angular
FROM node:18-alpine AS builder

WORKDIR /project

COPY frontend /project/


RUN npm run build

#construcción de la app de Spring Boot
# se añade la imagen de java
FROM maven:3.9.0-eclipse-temurin-17 as backend

# se define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

# se copian las dependencias del proyecto
COPY backend/movieframe/pom.xml /project/

# se descargan las dependencias del proyecto
RUN mvn clean verify --fail-never

# se copia el código del proyecto del backend
COPY backend/movieframe/src /project/src

#construccion de la app de angular en una ruta particular
COPY --from=builder /project/dist/frontend /project/src/main/resources/static/new

# se compila el codigo de java una vez compilado el codigo
RUN mvn clean package -o -DskipTests=true

#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM eclipse-temurin:17-jdk

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/src/app/

# Copia el JAR del contenedor de compilación
COPY --from=backend /project/target/*.jar /usr/src/app/

# Indica el puerto que expone el contenedor
EXPOSE 8443

# Comando que se ejecuta al hacer docker run
ENTRYPOINT [ "java", "-jar", "movieframe-0.0.1-SNAPSHOT.jar" ]
