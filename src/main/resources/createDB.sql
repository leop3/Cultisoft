create table actuador
(
  id int auto_increment
    primary key,
   id_cultivo int,
  descripcion varchar(30),
  tipo varchar(15),
  activo boolean
);

create table comando
(
  id int auto_increment
    primary key,
  id_actuador int,
  tipo varchar(30),
  fechaHora date,
  desde date,
  hasta date,
  confirmacion boolean 
);

create table cultivo
(
  id int auto_increment
    primary key,
    id_usuario int,
   clave varchar(20),
   nombre varchar(30),
   descripcion varchar(30)
);

create table sensor
(
  id int auto_increment
    primary key,
   tipo varchar(20),
   descripcion varchar(30),
   id_cultivo int
);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

create table usuario
(
  id int auto_increment
    primary key,
  nombre varchar(30),
  password varchar(20)
);

create table variable
(
	id int auto_increment
		primary key,
	tipo varchar(30),
	desde date,
	hasta date,
	umbral varchar(30),
	id_sensor int,
	id_actuador int
);

create table guia
(
	id int auto_increment
		primary key,
	id_usuario int,
	nombre varchar(30),
	descripcion varchar(30),
	tipo varchar(30)
);

create table comentario
(
	id int auto_increment
		primary key,
	id_guia int,
	id_usuario int,
	comentario varchar(100)
);

create table puntuacion
(
	id int auto_increment
		primary key,
	id_guia int,
	id_usuario int,
	puntuacion int
  	
);

create table cultivo_variable
(
	id int auto_increment
		primary key,
	id_cultivo int,
	id_variable int
);

create table guia_variable
(
	id int auto_increment
		primary key,
	id_guia int,
	id_variable int
);



ALTER TABLE actuador ADD CONSTRAINT fk_actuador_cultivo foreign key(id_cultivo) references cultivo(id) on delete cascade on update no action;
ALTER TABLE comando ADD CONSTRAINT fk_comando_actuador foreign key(id_actuador) references actuador(id) on delete cascade on update no action;
ALTER TABLE cultivo ADD CONSTRAINT fk_cultivo_usuario foreign key(id_usuario) references usuario(id) on delete cascade on update no action;
ALTER TABLE sensor ADD CONSTRAINT fk_sensor_cultivo foreign key(id_cultivo) references cultivo(id) on delete cascade on update no action;
ALTER TABLE variable ADD CONSTRAINT fk_variable_sensor foreign key(id_sensor) references sensor(id) on delete cascade on update no action;
ALTER TABLE variable ADD CONSTRAINT fk_variable_actuador foreign key(id_actuador) references actuador(id) on delete cascade on update no action;
ALTER TABLE guia ADD CONSTRAINT fk_guia_usuario foreign key(id_usuario) references usuario(id) on delete cascade on update no action;
ALTER TABLE comentario ADD CONSTRAINT fk_comentario_guia foreign key(id_guia) references guia(id) on delete cascade on update no action;
ALTER TABLE comentario ADD CONSTRAINT fk_comentario_usuario foreign key(id_usuario) references usuario(id) on delete cascade on update no action;
ALTER TABLE puntuacion ADD CONSTRAINT fk_puntuacion_guia foreign key(id_guia) references guia(id) on delete cascade on update no action;
ALTER TABLE puntuacion ADD CONSTRAINT fk_puntuacion_usuario foreign key(id_usuario) references usuario(id) on delete cascade on update no action;
ALTER TABLE cultivo_variable ADD CONSTRAINT fk_cultivo_variable foreign key(id_cultivo) references cultivo(id) on delete cascade on update no action;
ALTER TABLE cultivo_variable ADD CONSTRAINT fk_variable_cultivo foreign key(id_variable) references variable(id) on delete cascade on update no action;
ALTER TABLE guia_variable ADD CONSTRAINT fk_guia_varuavke foreign key(id_guia) references guia(id) on delete cascade on update no action;
ALTER TABLE guia_variable ADD CONSTRAINT fk_variable_guia foreign key(id_variable) references variable(id) on delete cascade on update no action;