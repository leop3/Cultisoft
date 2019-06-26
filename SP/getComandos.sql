
/*Obtener los comandos que no estan finalizados que pertenezcan a un usuario*/
DELIMITER // 
create procedure getComandos(p_id_usuario bigint)
begin
SELECT comando.id,comando.id_actuador AS idActuador,comando.desde,comando.hasta,comando.tipo,comando.fecha_hora AS fechaHora,comando.confirmacion
FROM 				comando
INNER JOIN 		actuador ON comando.id_actuador = actuador.id
INNER JOIN 		cultivo ON actuador.id_cultivo = cultivo.id
INNER JOIN 		usuario ON cultivo.id_usuario = usuario.id
WHERE usuario.id = p_id_usuario AND comando.confirmacion is not true;
end;
//
DELIMITER;

/*Obtener los Actuadores de un usuario*/

DELIMITER // 
create procedure getActuadores(p_id_usuario bigint)
begin
SELECT a.id as idActuador,a.estado ,a.tipo, id_cultivo
FROM 				actuador a
INNER JOIN 		cultivo c ON a.id_cultivo = c.id
INNER JOIN 		usuario u ON c.id_usuario = u.id
WHERE u.id = p_id_usuario;
end;
//
DELIMITER;
