Bd-> usando MySQL

crearla: create database dondeInvierto;

use dondeInvierto;

el ORM de la aplicacion va a crear sus tablas


En linux para iniciar mysql:
mysql -u root -p

contrasena





Para la parte Web no existe un registro , por lo tanto hay que crear a mano algun usuario en la consola de base de datos:

insert into Usuarios (username,password) values ("admin", "admin");

