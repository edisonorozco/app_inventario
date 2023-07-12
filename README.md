# API de Inventario y Depreciación de Activos

Este proyecto se basa en la creación de un API que ofrece una 
solución eficiente para gestionar el inventario y la depreciación de 
los activos de una compañía. La necesidad de este sistema surge de la 
importancia de llevar un control preciso de los equipos y su valor en el tiempo.

## Problema

El problema a abordar es la falta de un sistema que permita registrar,
actualizar y dar de baja los equipos de una compañía, así como calcular y 
mostrar la depreciación anual de su valor. Para ello, se requiere una solución 
que proporcione una interfaz sencilla y funcional para gestionar estos activos.

## Solución propuesta

La solución propuesta consiste en desarrollar un servicio de API que permita a 
los usuarios registrar los equipos de la compañía con información relevante, 
como número de serie, descripción, nombre, fecha de compra y valor de compra. 
Además, el sistema calculará automáticamente la depreciación anual del 4% del 
valor de cada equipo, aunque este porcentaje puede ajustarse según las reglas 
fiscales específicas.

El API proporcionará una consulta para obtener los datos básicos de un equipo, 
incluyendo su depreciación acumulada. Además, permitirá la actualización de la 
información de los equipos existentes y la posibilidad de dar de baja aquellos que
ya no están en uso.

## Tecnologías utilizadas

- Maven
- Java 19
- Spring boot 3
- Git

## Instalación y configuración

### * Instalación local

En este ejemplo se mostrará como instalar localmente el proyecto, requisitos tener instalado:
- Git
- Java 19
- Maven

### 1.	Descargar este proyecto:

Utilizar el comando de git clone, para esto debe contar con git instalado en su 
ordenador. Al ejecutar el comando se descargará una carpeta con los archivos 
necesarios para ejecutar la aplicación.

### 2.	Compilar el proyecto

Para compilar el proyecto y generar el archivo .JAR abra la carpeta que descargo en 
el paso anterior y dentro de esta carpeta ejecutar una línea de comandos, por ejemplo, 
en Windows cmd.

![Texto alternativo](images/img_1.png)

### 3. Luego ejecute el comando mvn package, escribiendo el comando y presionando la tecla enter.

![Texto alternativo](images/img_2.png)

### 4.	Ejecutar el proyecto

Al ejecutar el paso anterior se creo una nueva carpeta llamada ***“target”*** en su 
directorio, dentro de la consola anterior ejecute ahora el comando ***“cd target”*** y 
luego el comando ***“java -jar app-inventory-0.0.1-SNAPSHOT.jar”*** 

![Texto alternativo](images/img_3.png)

Al hacer esto se mostrará lo siguiente

![Texto alternativo](images/img_4.png)
Y ya tenemos el proyecto en ambiente local con Java 19, Spring, Maven y Git.