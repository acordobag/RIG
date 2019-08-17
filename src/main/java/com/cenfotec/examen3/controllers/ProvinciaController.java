package com.cenfotec.examen3.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.model.Provincia;
import com.cenfotec.examen3.repository.PaisRepository;
import com.cenfotec.examen3.repository.ProvinciaRepository;

@RestController
@RequestMapping({ "/provincia" })
public class ProvinciaController {

	private PaisRepository paisRepo;
	private ProvinciaRepository repo;
	
	ProvinciaController(PaisRepository pr, ProvinciaRepository r) {
		this.paisRepo = pr;
		this.repo = r;
	}

	@GetMapping(path = { "/{idPais}" })
	public ResponseEntity<List<Provincia>> findAll( @PathVariable long idPais) {
		Pais np = paisRepo.findById(idPais).get();		
		return repo.findByPais(np).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<List<Provincia>> findAll(@RequestParam String nombre) {
		return repo.findByNombreContaining(nombre).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "/{idPais}/{id}" })
	public ResponseEntity<Provincia> findById(@PathVariable long id, @PathVariable long idPais) {
		return repo.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@Transactional
	@PostMapping(path = { "/{idPais}" })
	public ResponseEntity<List<Provincia>> addPoliticalDivision(@RequestBody Set<Provincia> provincias, @PathVariable long idPais) {
		Pais pais = paisRepo.findById(idPais).get();
		List<Provincia> r = new ArrayList<Provincia>();
		provincias.forEach(p ->{
			p.setPais(pais);
			r.add(repo.save(p));
		});
		return ResponseEntity.ok().body(r);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Provincia> update(@PathVariable("id") long id, @RequestBody Provincia provincia) {
		return repo.findById(id).map(record -> {
			record.setNombre(provincia.getNombre());
			Provincia updated = repo.save(record);
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
