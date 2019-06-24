DELIMITER // 
create procedure getComandos(p_id_usuario bigint)
begin
SELECT comando.id,comando.id_actuador AS idActuador,comando.desde,comando.hasta,comando.tipo,comando.fecha_hora AS fechaHora,comando.confirmacion
FROM 				comando
INNER JOIN 		actuador ON comando.id_actuador = actuador.id
INNER JOIN 		cultivo ON actuador.id_cultivo = cultivo.id
INNER JOIN 		usuario ON cultivo.id_usuario = usuario.id
WHERE usuario.id = p_id_usuario;
end;
//
DELIMITER;