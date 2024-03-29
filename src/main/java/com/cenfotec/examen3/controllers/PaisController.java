package com.cenfotec.examen3.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.repository.PaisRepository;

@RestController
@RequestMapping({ "/pais" })
public class PaisController {

	private PaisRepository repo;

	PaisController(PaisRepository r) {
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
	public Pais create(@RequestBody Pais pais) {
		return repo.save(pais);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Pais> update(@PathVariable("id") long id, @RequestBody Pais pais) {
		return repo.findById(id).map(record -> {
			record.setNombre(pais.getNombre());
			record.setSuperficieTerrestre(pais.getSuperficieTerrestre());
			record.setSuperficieMaritima(pais.getSuperficieMaritima());
			Pais updated = repo.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repo.findById(id).map(record -> {
			repo.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
