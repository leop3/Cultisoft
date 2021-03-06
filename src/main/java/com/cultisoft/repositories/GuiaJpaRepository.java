package com.cultisoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Guia;

@Repository("guiaJpaRepository")
public interface GuiaJpaRepository extends JpaRepository<Guia, Long> {

}
