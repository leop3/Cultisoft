package com.cultisoft.controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.entities.Comando;
import com.cultisoft.responses.ResponseComando;
import com.cultisoft.responses.ResponseNovedades;
import com.cultisoft.service.ComandoService;

@RestController
@RequestMapping("/comando")
public class ComandoRestController {

	@Autowired(required = true)
	ComandoService service;

	@PostMapping(path = "/novedades")
	public ResponseNovedades getNovedades() {
		ResponseNovedades response = new ResponseNovedades();

		return response;
	}

	/**
	 * 
	 */
	@PostMapping(path = "/ejecutar")
//	public void ejecutarComando(@RequestBody Comando command) {
	public void ejecutarComando() {
		List<Comando> commands = new ArrayList<>();
		try {
			Connection conn = this.conectar();
			CallableStatement cs = conn.prepareCall("{call getComandos(?)}");
			cs.setLong(1,1);
			cs.execute();
			final ResultSet rs = cs.getResultSet();
			while(rs.next()) {
				commands.add(
					new Comando(
							rs.getLong("id"),
							rs.getString("tipo"),
							rs.getDate("fechaHora"),
							rs.getDate("desde"),
							rs.getDate("hasta"),
							rs.getBoolean("confirmacion"),
							rs.getLong("idActuador")
							)
				);
			}
			System.out.println(commands);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para recibir los comandos enviados por los usuarios
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping(path = "/enviarComando")
	public ResponseComando recibirComando(@RequestBody Comando command) {
		ResponseComando response = new ResponseComando();
		try {
			service.agregar(command);
			response.setEstado("OK");
		} catch (Exception e) {
			response.setEstado("Error: " + e);
		}
		return response;
	}

	public Connection conectar() throws Exception {
		String databaseURL = "jdbc:mariadb://localhost:3306/cultisoft";
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection(databaseURL, "root", "root");
	}
}
