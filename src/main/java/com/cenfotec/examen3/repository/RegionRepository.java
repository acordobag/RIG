package com.cenfotec.examen3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
	Optional<List<Region>> findByPais(Pais p);	
	Optional<List<Region>> findByNombreContaining(String nombre);
}
