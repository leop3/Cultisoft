select comando.id , comando.id_actuador as idActuador , comando.desde , comando.hasta , comando.tipo, comando.fecha_hora as fechaHora
from comando 
 inner join actuador on comando.id_actuador = actuador.id
 inner join cultivo on actuador.id_cultivo  = cultivo.id
 inner join usuario on cultivo.id_usuario = usuario.id
 where usuario.id = 0