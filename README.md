



<h1 style="text-align: center">CURSO SPRING : OPEN BOOTCAMP</h1>


<ul>

<li><h2>Ejercicio 4, 5 y 6</h2></li>

<p>Se ha desarrollado un CRUD para Laptops.</p>


<li><h3>Herramientas utilizadas &#9889</h3></li>

Lenguajes: Java 17
Frameworks: Spring Boot, Maven.
IDE: Intellij Idea

<li><h3> Database &#9997</h3></li>

Base de datos utilizada H2 SQL. Para configurar, editar los siguientes campos el archivo: `resources/application.properties`</br>

spring.jpa.show-sql=true

spring.datasource.url=jdbc:h2:file:C:/data/sample

spring.datasource.username=sa

spring.datasource.password=

spring.datasource.driverClassName=org.h2.Driver

spring.jpa.hibernate.ddl-auto=create-drop

spring.sql.init.mode=always

spring.jpa.defer-datasource-initialization=true


<li><h3>EndPoints &#10024</h3></li>

En cuanto a los Endpoints, fue incluido el archivo postman en `/documents` con la collection de los endpoints disponibles para uso de la api. Incluyen metodos para un CRUD completo sobre el objeto "Laptop".

</ul>