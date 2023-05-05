FROM maven:3.9.0-eclipse-temurin-17 as builder


# Define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

# Copia las dependencias del proyecto
COPY pom.xml /project/

# Descarga las dependencias del proyecto
RUN mvn clean verify --fail-never

# Copia el código del proyecto
COPY . /project

# Compila proyecto
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
