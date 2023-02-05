# MovieFrame

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
  - Autor de la reseña
  - Crítica o reseña

## **2. Usuarios:**
  * **_Anónimo_**: no introduce ninguna credencial en la aplicación, se le muestra la cartelera de la película y series más recientes.
  * **_Registrado_**: se registra en la aplicación (nombre, apodo, fecha de nacimiento, foto y contraseña).
  * **_Administrador_**: se registra en la app (nombre, apodo, fecha nacimiento, foto, contraseña). La contraseña está cifrada en un fichero de configuración 

## **3. Permisos de los usuarios:**
  * **_Anónimo_**: visualiza la cartelera (nombre, descripción, personajes, fecha de estreno, pais, calificación media, trailer de la peli o serie...), no puede subir reseñas ni visualizar las de otros
  * **_Registrado_**: visualiza la cartelera, agrega/edita reseñas, vota películas o series, visualiza y comenta reseñas de otros registrados, visualizar el histórico de reseñas realizadas
  * **_Administrador_**: eliminar reseñas, comentarios, información no verificada, añadir películas o series a la app, enviar mail a un usuario cuando una reseña suya haya sido comentada por otro

## **4. Imágenes:**
  * Fotos de portada las películas y series, cortas escenas, fotos de su elenco

## **5. Gráficos:**
  * Películas más taquilleras 
  * Un diagrama que clasifique las películas según el número de votos o número de reseñas o comentarios
  * Premios
  
## **6. Tecnología complementaria:**
  * Usar "Google Maps" para mostrar el/los cines donde se puede ver una determinada película
  * Enviar un mail a un usuario registrado afectado cada vez que su reseña es comentada
  * Enlace a YouTube para visualizar el trailer

## **7. Algoritmo avanzado:**
  * Usuario registrado:
    * _Si no ha comentado la reseña de ningún usuario_: se le muestra la cartelera de películas y series más recientes (fecha más actual)
    * _si lo ha hecho_: se le muestra la cartelera de películas y series de esos usuarios (ordenados de fecha más reciente a menos)

