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
    * Si hace login por 1ª vez: se le muestra la cartelera de películas y series más recientes
    * Si no es la 1ª vez que hace login: se le muestra la cartelera de películas y series de

  * **_Administrador_**: se registra en la app (nombre, apodo, fecha nacimiento, foto), tiene una contraseña cifrada en un fichero de configuración 

## **3. Permisos de los usuarios:**
  * **_Anónimo_**: visualiza la cartelera (nombre, descripción, personajes, fecha de estreno, pais, calificación media, trailer de la peli o serie...), no puede subir reseñas ni visualizar las de otros
  * **_Registrado_**: visualiza la cartelera, agrega/edita reseñas, vota películas o series, visualiza y comenta reseñas de otros registrados
  * **_Administrador_**: eliminar reseñas, comentarios, información no verificada, añadir películas o series a la app, enviar mail a un usuario cuando una reseña suya haya sido comentada por otro