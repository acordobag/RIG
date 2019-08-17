package com.cenfotec.examen3.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String continente;
	private double superficieTerrestre;
	private double superficieMaritima;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "pais", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Provincia> divisionPolitica;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "pais", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Region> divisionBiologica;
}
