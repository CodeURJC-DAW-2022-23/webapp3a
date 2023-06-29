#se añade la imagen de node
FROM node:18-alpine AS builder

WORKDIR /project

COPY frontend/ .

RUN npm install
# generación de los ficheros para producción en la ruta específica "new"
RUN npm run build -- --base-href=/new/

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

# se copian los ficheros
# origen: dist/frontend
# destino: src/main/resources/static/new
COPY --from=builder /project/dist/frontend /project/src/main/resources/static/new

# construccion de la app una vez compilado el codigo
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
