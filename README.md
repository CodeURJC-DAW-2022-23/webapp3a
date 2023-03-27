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