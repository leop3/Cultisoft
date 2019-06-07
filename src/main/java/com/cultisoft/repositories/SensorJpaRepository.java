package com.cultisoft.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Sensor;

@Repository("SensorJpaRepository")
public interface SensorJpaRepository extends JpaRepository<Sensor, Serializable> {

	public abstract Sensor findByDescripcion(String descripcion);

	public abstract Sensor findById(Long id);
}
