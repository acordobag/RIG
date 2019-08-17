package com.cenfotec.examen3.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cenfotec.examen3.model.Animal;
import com.cenfotec.examen3.repository.PaisRepository;
import com.cenfotec.examen3.repository.AnimalRepository;

public class AnimalController {
	@SuppressWarnings("unused")
	private PaisRepository paisRepo;
	private AnimalRepository repo;
	
	AnimalController(PaisRepository pr, AnimalRepository r) {
		this.paisRepo = pr;
		this.repo = r;
	}

	@GetMapping(path = { "/{idPais}" })
	public ResponseEntity<List<Animal>> findAll( @PathVariable long idPais) {	
		return repo.findByPais(idPais).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<List<Animal>> findAll(@RequestParam String nombre) {
		return repo.findByNombreContaining(nombre).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
}
