package com.cenfotec.examen3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cenfotec.examen3.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	@Query("select a FROM Animal a where a.region.pais.id = :id")
	Optional<List<Animal>> findByPais(@Param("id")Long paisId);
	
	Optional<List<Animal>> findByNombreContaining(String nombre);
}
