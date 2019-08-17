package com.cenfotec.examen3.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.repository.PaisRepository;

@RestController
@RequestMapping({ "/pais" })
public class PaisController {

	private PaisRepository repo;
	
	PaisController(PaisRepository r){
		this.repo = r;
	}
	
	@GetMapping
	public List<Pais> findAll() {
		return repo.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Pais> findById(@PathVariable long id) {
		return repo.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Pais create(@RequestBody Pais contact) {
		return repo.save(contact);
	}

}
