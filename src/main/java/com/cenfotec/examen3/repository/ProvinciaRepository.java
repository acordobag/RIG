package com.cenfotec.examen3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
	Optional<List<Provincia>> findByPais(Pais p);	
	Optional<List<Provincia>> findByNombreContaining(String nombre);
}
