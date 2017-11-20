PERSISTENCIA
Bd-> usando MySQL

1)create database dondeInvierto;

2)use dondeInvierto;

el ORM de la aplicacion va a crear sus tablas


En linux para iniciar mysql:
mysql -u root -p
ingresar contrasena


WEB
Para la parte Web no existe un registro , por lo tanto hay que crear a mano algun usuario en la consola de base de datos:

insert into Usuarios (username,password) values ("admin", "admin");

SERVIDOR
Para el deploy en VM de un IaaS.

1)Instalar
-Java8
-Maven
-Git
-Mysql
-Tomcat8

2)Compilar el projecto con maven.
3)Para ejecutar una clase .java mvn exec:java. Previamente, en el pom.xml, ir
al plugin de mvn exec, e indicar en <mainClass></mainClass> la clase que se quiere ejecutar.


