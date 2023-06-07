# se añade la imagen de java
FROM maven:3.9.0-eclipse-temurin-17 as builder


# se define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

# se copian las dependencias del proyecto
COPY pom.xml /project/

# se descargan las dependencias del proyecto
RUN mvn clean verify --fail-never

# se copia el código del proyecto de angular
COPY . /project

# se compila el codigo de java una vez compilado el codigo de angular
RUN mvn clean package -o -DskipTests=true

#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM eclipse-temurin:17-jdk

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/src/app/

# Copia el JAR del contenedor de compilación
COPY --from=builder /project/target/*.jar /usr/src/app/

# Indica el puerto que expone el contenedor
EXPOSE 8443

# Comando que se ejecuta al hacer docker run
ENTRYPOINT [ "java", "-jar", "movieframe-0.0.1-SNAPSHOT.jar" ]
