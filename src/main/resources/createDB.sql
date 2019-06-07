create table actuador
(
  id          int auto_increment
    primary key,
  descripcion varchar(30),
  tipo varchar(15),
  activo boolean
);

create table comando
(
  id          int auto_increment
    primary key,
  tipo varchar(30),
  recibido boolean,
  fechaHora date,
  desde date,
  hasta date
);

create table cultivo
(
  id          int auto_increment
    primary key,
   nombre varchar(30),
   descripcion varchar(30),
   zonaHoraria varchar(30),
   clave varchar(20)
                              
);

create table sensor
(
  id          int auto_increment
    primary key,
   tipo varchar(20),
   descripcion varchar(30)
);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

create table usuario
(
  id          int auto_increment
    primary key,
  nombre varchar(30),
  password varchar(20)
);