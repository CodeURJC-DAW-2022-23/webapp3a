# sample-project-phase2

Ejemplo que contiene algunas de las funcionalidades solicitadas en la Fase 2 del proyecto, en concreto:

* Aplicación web implementada con Spring MVC y templates Mustache.
* Base de datos MySQL
* Persistencia de imágenes en la base de datos 
* Carga de imágenes en los datos de ejemplo
* Posibilidad de editar las imágenes asociadas a los libros
* Roles de usuarios con diferentes funcionalidades
* Seguridad con https

Por defecto el proyecto require una base de datos MySQL disponible en localhost con la siguiente configuración:
* Esquema: `books`
* Usuario: `root`
* Contraseña: `password`

Se puede arrancar usando docker con el comando:

```
$ docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=books -p 3306:3306 -d mysql:8.0
```

La aplicación se ejecuta con el comando:

```
mvn spring-boot:run
```

Los usuarios son:

* Usuario: `user`, contraseña: `pass`
* Usuario: `admin`, contraseña: `adminpass`
