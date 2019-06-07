package com.cultisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cultisoft.entities.Sensor;
import com.cultisoft.repositories.SensorJpaRepository;

@Service
public class SensorService {

	@Autowired(required = true)
	@Qualifier(value = "SensorJpaRepository")
	SensorJpaRepository sensorJpaRepository;

	public List<Sensor> mostrarTodo() {
		return sensorJpaRepository.findAll();
	}

	public Sensor agregar(Sensor sensor) {
		return sensorJpaRepository.save(sensor);
	}

	public Sensor actualizar(Sensor sensor) {
		return sensorJpaRepository.save(sensor);
	}

	public void borrar(Sensor sensor) {
		sensorJpaRepository.delete(sensor);
	}

	public Sensor buscar(String id) {
		return sensorJpaRepository.findById(Long.parseLong(id));
	}

}
