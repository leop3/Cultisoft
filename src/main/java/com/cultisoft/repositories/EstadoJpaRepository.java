package com.cultisoft.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Estado;

@Repository("EstadoJpaRepository")

public interface EstadoJpaRepository extends JpaRepository<Estado, Serializable>{

	public abstract List<Estado> findBySensor(String id);
}
