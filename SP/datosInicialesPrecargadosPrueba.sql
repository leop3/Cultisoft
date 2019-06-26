select comando.id , comando.id_actuador as idActuador , comando.desde , comando.hasta , comando.tipo, comando.fecha_hora as fechaHora
from comando 
 inner join actuador on comando.id_actuador = actuador.id
 inner join cultivo on actuador.id_cultivo  = cultivo.id
 inner join usuario on cultivo.id_usuario = usuario.id
 where usuario.id = 1
 
 
 
 drop schema cultisoft

create schema cultisoft

 /*Set de Prueba*/
 
insert into usuario(nombre,password) values  ("srplanta","JazMin123");
insert into usuario(nombre,password) values  ("hater","plantasMalnacidas");
insert into usuario(nombre,password) values  ("plantlover","BuenaPlantita223"); 

select * from usuario 
 
insert into cultivo(clave,descripcion,nombre,id_usuario) values ("claceloide","descripcion 2","Coca",1);
insert into cultivo(clave,descripcion,nombre,id_usuario) values ("ehWachen","esta re crito esto eh","Jazmin",2);
insert into cultivo(clave,descripcion,nombre,id_usuario) values ("tukiloa","sube de peso","Mepelope",2);
insert into cultivo(clave,descripcion,nombre,id_usuario) values ("memepa","tartamuuudoodo","Tenedoide",3);
insert into cultivo(clave,descripcion,nombre,id_usuario) values ("cooc","ococ","coco",1);

select * from cultivo

delete from cultivo where id = 7


update cultivo set id_usuario = 3 where id = 2

insert into actuador(descripcion,estado,tipo,id_cultivo) values ("actuador 1",false,"tipo 1",1);
insert into actuador(descripcion,estado,tipo,id_cultivo) values ("actuador 2",false,"tipo 2",2);
insert into actuador(descripcion,estado,tipo,id_cultivo) values ("actuador 3",false,"tipo 3",2);
insert into actuador(descripcion,estado,tipo,id_cultivo) values ("actuador 4",false,"tipo 4",1);
insert into actuador(descripcion,estado,tipo,id_cultivo) values ("actuador 5",false,"tipo 5",1);
insert into actuador(descripcion,estado,tipo,id_cultivo) values ("actuador 6",false,"tipo 6",4);
insert into actuador(descripcion,estado,tipo,id_cultivo) values ("actuador 7",false,"tipo 7",2);
 	 
select * from actuador 
 
 
insert into comando(confirmacion,desde,fecha_hora,hasta,tipo,id_actuador) values(false,null,null,null,"tipo1",1);
insert into comando(confirmacion,desde,fecha_hora,hasta,tipo,id_actuador) values(false,null,null,null,"tipo2",2);
insert into comando(confirmacion,desde,fecha_hora,hasta,tipo,id_actuador) values(false,null,null,null,"tipo3",3);
insert into comando(confirmacion,desde,fecha_hora,hasta,tipo,id_actuador) values(false,null,null,null,"tipo4",4);
insert into comando(confirmacion,desde,fecha_hora,hasta,tipo,id_actuador) values(false,null,null,null,"tipo5",5);
insert into comando(confirmacion,desde,fecha_hora,hasta,tipo,id_actuador) values(false,null,null,null,"tipo6",6);
insert into comando(confirmacion,desde,fecha_hora,hasta,tipo,id_actuador) values(false,null,null,null,"tipo7",7);
 
select * from comando
