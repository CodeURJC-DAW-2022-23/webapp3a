# **MovieFrame**

# FASE 1

| Nombre y apellidos | Correo | Cuenta github |
| --- | --- | --- |
| Maria Amparo Alami | ma.alami.2020@alumnos.urjc.es | MaAlami2020 |
| Cristobal Justo Rustarazo | c.justo.2019@alumnos.urjc.es | CristobalJR |
| Cassiel Seth Mayorca Heirisman | cs.mayorca.2018@alumnos.urjc.es | cassiel_smh |
| Javier García Seller | j.garcias.2020@alumnos.urjc.es | Javarto |
| Shadith Perez  Rivera | sy.perez.2022@alumnos.urjc.es | Shadith |

#### Zona de organizacion [Trello](https://trello.com/w/movieframe)

## **1. Entidades:**
  - Usuario
  - Película o serie
  - Autor de la película
  - Crítica o reseña

## **2. Usuarios:**
  * **_Anónimo_**: no introduce ninguna credencial en la aplicación, se le muestra la cartelera de las películas y series más recientes o mejor calificadas.
  * **_Registrado_**: se registra en la aplicación (nombre, nombre de usuario, email(para mandarle recomendaciones), foto y contraseña).
  * **_Administrador_**: es el primer usuario que se registra en la app (nombre, nombre de usuario, email(para mandarle recomendaciones), foto, contraseña). La contraseña está cifrada en un fichero de configuración 

## **3. Permisos de los usuarios:**
  * **_Anónimo_**: visualiza la cartelera (nombres de peliculas, personajes, fecha de estreno, calificación media, trailer de la peli o serie...), no puede subir reseñas ni visualizar las de otros
  * **_Registrado_**: visualiza la cartelera, agrega/edita reseñas, vota películas o series, visualizar el histórico de reseñas realizadas
  * **_Administrador_**:visualiza la cartelera, agrega/edita reseñas, vota películas o series, visualizar el histórico de reseñas realizadas, eliminar reseñas de usuarios registrados, información no verificada y añadir películas o series a la app.

## **4. Imágenes:**
  * Fotos de portada las películas y series, cortas escenas, fotos de su elenco

## **5. Gráficos:**
  * Películas más taquilleras 
  * Peliculas mas populares (Un diagrama que clasifique las películas según el número de votos o número de reseñas)
  * Directores, actices y demas con mas premios
  
## **6. Tecnología complementaria:**
  * Usar "Google Maps" para mostrar el/los cines donde se puede ver una determinada película
  * Notificaciones de actulizaciones de los autores, peliculas y demas que sigues
  * Generar un PDF con recomendaciones de series y películas (cartel incluido) y que este sea enviado a los usuários mediante un mail

## **7. Algoritmo avanzado:**
  * Usuario registrado:
    * _Si no ha publicado ninguna reseña_: se le muestra la cartelera de películas y series más recientes (fecha más actual)
    * _si lo ha hecho_: se le muestra la cartelera de películas y series de un estilo similar al de las ya reseñadas por el usuario (ordenados de fecha más reciente a menos)

## **8. Pantallas de la aplicación:**
  * **_initial_screen_**:  cartelera con películas seleccionadas por el administrador, un traíler de las películas cargadas, un botón para hacer iniciar sesión, y gráficos con información cuantitativa sobre las películas
  * **_director_screen_**: se muestra la información personal del sujeto junto con un listado de sus pelis dirigidas y una valoración media de su trabajo
  * **_login_screen_**: dispone de campos para iniciar sesión, y un botón para registrarse en caso de no poder iniciar sesión
  * **_modification_reviews_screen_**: en este pantalla se muestra el listado de reseñas de todos los usuarios registrados en la app con un boton de eliminar en cada una de ellas, solo el administrador puede acceder a ella.
  * **_movie_aggregation_screen_**:en este pantalla se muestra un listado de peliculas aleatorias en la app con un boton de agregar en cada una de ellas, solo el administrador puede acceder a esta página
  * **_recommendations_screen_**:la pantalla tiene un buscador para filtrar por correo y categoría de película para mandar a un usuario registrado películas como recomendación, solo el administrador tiene acceso a esta página
  * **_movie_screen_**:se muestra la información de una película, enlace a la pantilla del director, botón para volver a la página de incio, cuadro de texto para escribir una reseña y cuadro para enviar una votación, además de un mapa para buscar cines
  * **_reviews_screen_**:página donde un usuario registrado puede ver todas sus reseñas hechas desde la primera vez que se dió de alta en la app
  * **_signup_screen_**:página con cuadros de diálogo para meter información y foto(opcional), y botón para volver a la página de inicio

![Screen](Screens.JPG)

# FASE 2

## **1. Navegación:**

Navegación del usuaurio anónimo

![navegacionIII](navegacion_III(public).JPG)

Navegación del usuaurio registrado

![navegacionI](navegacion_I(user).JPG)

Navegación del administrador

![navegacionII](navegacion_II(admin).JPG)
## **2. Instrucciones de ejecucción:**

Éstos serían los pasos para ejecutar el proyecto sin comandos:
1. clonar el proyecto 
2. incoporar el proyecto en Visual Studio Code con GitHub Desktop
3. instalar las dependencias de spring boot en el archivo pom.xml
4. en el archivo application.properties meter el usuario, contraseña con la que se registró en MySQL, otro usuario y contraseña que funciona como administrador y agregar el fichero keystore.jks
5. arrancar el proyecto con el dashboard
6. meter la url `https://localhost:8443` en navegador

Éstos serían los pasos a seguir en caso de utilizar comandos:

El proyecto requiere de una base de datos MySQL que se puede arrancar usando Docker con el comando `$Docker run --rm -e MySQL_ROOT_PASSWORD=password \ -e MYSQL_DATABASE=webapp3 -p 3306:3306 -d mysql:8.0`

La aplicación se ejecuta con el comando `mvn spring-boot:run`

El proyecto usa una base de datos MySQL:
* esquema: movieframe
* usuario: webapp3
* contraseña: Mundialmente1

Dentro de una de las tablas (user) de la base de datos se han almacenado usuarios de la aplicación por defecto, éstos son:

* usuario: `edward`, contraseña: `edu123456`
* usuario: `hughjackman`, contraseña cifrada: `$2a$12$9nJUxHrHgRXGWWpt8tfmteaIl1Ts0EPQ/0aIgswVXIMgp7kwWHbd`

La versión de java es la 17

## **3. Entidades de la base de datos:**
![entidades](Entidades.JPG)
## **4. Diagrama de clases y templates:**
![clases](Clases.JPG)

## **5. Participación:**
#### **_5.1. Tareas:_**
En esta fase se han realizado las siguientes tareas:

- una gráfica de las películas en el que se muestra un diagrama de barras donde el eje horizontal muestra el nombre de las películas disponibles, y el vertical, el número de reseñas que se han hecho sobre una peli particular 
- posibilitar al usuario registrado añadir una reseña (votar y meter un comentario)
- mostrar la información de una película al pulsar sobre ella desde la pantalla de inicio
- mostrar el listado de películas al iniciar la aplicación
- mostrar los enlaces de los directores de una película; y mostrar la info. de uno particular al clikar sobre el enlace
- posibilitar al administrador eliminar una reseña de cualquier usuario
- mostrar al administrador todas las reseñas de todos los usuarios y posibilitarle eliminar una
- se implementó un algoritmo de consulta avanzada donde en un buscador se introduce el nombre de una peli y se muestran todas aquellas ordenadas de mayor a menor puntuación de votos
- se agregó una pantalla donde el administrador crea una nueva peli (rellena los campos para meter el títulos, descripción, imagen,...), - se agregó una pantalla donde el administador selecciona una película y se genera un PDF sobre la misma como recomendación; para ello se usó una librería externa
- se posibilitó al usuario registrado mostrarle todas sus reseñas en una pantalla y  editar una
- se añadió seguridad para chequear el tipo de usuario logeado después de iniciar sesión
#### **_5.2. Commits:_**
* a36aab1736b5930af5d56943880c7ceb17878684 --> implementadas funcionalidades de la gráfica, mostrar películas de la pantalla inicial, mostrar una movie, escribir una reseña, mostrar info. de un director
* 9dad6feca2b5f09d9c0538e13f40a939e0aa4e21
--> implementada pantalla del administrador
* d388b8478dcb7b264b9f684b7d7e88282dd25f78 --> algoritmo de consulta avanzada
* b1e1a342334029d30fc58e480a3d3a7dcbc34643 --> generar recomendación de una peli en PDF
* fa90679c32908f059306abd9555ebb78af2cf26c --> @post del administrador de una peli con imagen
#### **_5.3. Ficheros:_**

- backend\movieframe\src\main\java\es\webapp3\movieframe\controller\Home.java
- backend\movieframe\src\main\java\es\webapp3\movieframe\controller\MovieController.java
- backend\movieframe\src\main\java\es\webapp3\movieframe\model\movie.java
- backend\movieframe\src\main\java\es\webapp3\movieframe\service\DataBaseInitializer.java
- backend\movieframe\src\main\resources\templates\recommendations_screen.html

# FASE 3

## **1. Documentación de la API Rest:**

Link a la especificación OpenAPI:

https://raw.githack.com/CodeURJC-DAW-2022-23/webapp3a/fase-3/backend/movieframe/api-docs/api-docs.yaml

Link a la documentación en HTML:

https://raw.githack.com/CodeURJC-DAW-2022-23/webapp3a/fase-3/backend/movieframe/api-docs/api-docs.html

## **2. Actualización del diagrama de clases:**
![clases1](Clases1.JPG)

## **3. Instrucciones de ejecución de la aplicación dockerizada:**

Primero hay que añadir las siguientes configuraciones al fichero "application.properties":

```application.properties
#MySQL connection

spring.datasource.url=jdbc:mysql://db:3306/movieframe
spring.datasource.username=root
spring.datasource.password=Mundialmente1
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```
A continuación, se crea el fichero "docker-compose.yml" en el mismo directorio en el que se encuentra el archivo Dockerfile.
Este fichero de docker-compose cuenta con 2 contenedores o servicios de la app, "web" y "db". El contenedor "db" usa la imagen estándar de mysql en DockerHub, `mysql:8.0`, además de variables de entorno que llevan las mismas configuraciones que el archivo de propiedades, y crear los volúmenes para que los datos sean persistentes. El contenedor "web" tiene definido el nombre de la imagen que se crea, las variables de entorno que usa, la ruta del Dockerfile para construir la imagen, los puertos asociados al host, una línea de código para indicar el orden de arranque, de manera que primero arranca el servicio "db" y luego el servicio "web"; y una política de espera.

Finalmente, se ejecuta el comando `docker-compose up` en la línea de comandos de windows powershell, y cuando la applicación esté levantada, en el navegador se introduce `https://localhost:8443`

## **4. Documentación para construir la imagen docker:**
Como requisito principal se necesita disponer del fichero -Dockerfile- en el cual, a grandes rasgos, se añaden instrucciones para crear un contenedor de la imagen Maven llamado "builder", copiar el código y las dependencias del proyecto dentro del contenedor en la ruta /project, crear un contenedor de la imagen eclipse-temurin:17-jdk (el número de la versión se corresponde con el número de la versión con el que se ejecuta el proyecto).
Para terminar, se introducen líneas de código para copiar el binario .jar desde el contenedor "builder", exponer el puerto 8443 y hacer el despliegue dentro del contenedor.
Teniendo todo esto, se ejecuta el comando `docker build -t maalami2020/movieframe:v1 .`

Con docker build se crea la imagen que va a contener el despliegue, con el nombre `maalami2020/movieframe`, donde "maalami2020" se refiere al nombre de usuario de DockerHub y "movieframe" al nombre del proyecto; la versión `v1` que es el tag, y el `.` porque es la dirección donde está el Dockerfile y donde se está ejecutando el comando.  
 
## **5. Participación:**
#### **_5.1. Tareas:_**
En esta fase se han realizado las siguientes tareas:

- crear los métodos de las funcionalidades de la web en versión API Rest; además, definirlos en la clase "@RestController" con la ruta empezando por "/api".
- definir una clase "RestSecurityconfiguration" para controlar el acceso a las páginas de la API Rest
- Definición de una colección en postman con peticiones acerca de las funcionalidades disponibles en la app, y redacción de la misma en el fichero "api.postman-collection.json"
- Documentar la API Rest en remoto y local.
- Construir imágenes y hacer el despliegue del proyecto en docker
- Realizar el diseño arquitectónico con los métodos y clases de la API Rest y redactar el README
#### **_5.2. Commits:_**
* 385625bad3174894627c0fdfb2f1c8f13daaec36 --> peticiones Postman
* 802d18bfa6c2b3632c455c4ae58255e85284b536 --> documentación de la API Rest en remoto
* b7048ce05b0b97ee96ed2a33ba93a05462355ebc --> documentación de la API Rest en local
* d2ab5480c466642eadaf3c3d07bce89c945a9d85 --> aplicación dockerizada
*  ac51a4b0f9453ecb2bc08b05af5fa81417912d3b --> diseño arquitectónico y README
#### **_5.3. Ficheros:_**

- backend\movieframe\api.postman_collection.json
- backend\movieframe\src\main\java\es\webapp3\movieframe\controller\MovieRestController.java
- backend\movieframe\pom.xml
- backend\movieframe\docker\docker-compose.yml
- backend\movieframe\src\main\java\es\webapp3\movieframe\security\RestSecurityconfiguration.java