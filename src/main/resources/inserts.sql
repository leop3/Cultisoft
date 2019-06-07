-- Cultivo
insert into cultivo (nombre,descripcion,zonahoraria,clave) values('Zanahoria','es una zana','am','clave1');
insert into cultivo (nombre,descripcion,zonahoraria,clave) values('Zapallo','es un zapa','am','clave2');
insert into cultivo (nombre,descripcion,zonahoraria,clave) values('Tomate','es un toma','pm','clave3');
insert into cultivo (nombre,descripcion,zonahoraria,clave) values('Banana','es una banana','pm','clave4');
insert into cultivo (nombre,descripcion,zonahoraria,clave) values('Naranjo','es naranja','am','clave5');

-- Sensor
insert into sensor (tipo,descripcion) values('luz','calcula la iluminacion');
insert into sensor (tipo,descripcion) values('temperatura','calcula la temperatura');
insert into sensor (tipo,descripcion) values('humedad','calcula la humedad');

-- Comando 
insert into comando (tipo,recibido,fechaHora,desde,hasta) values('comandoTipo1',true,null,null,null);
insert into comando (tipo,recibido,fechaHora,desde,hasta) values('comandoTipo2',true,null,null,null);
