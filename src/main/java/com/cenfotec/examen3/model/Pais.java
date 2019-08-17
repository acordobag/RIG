package com.cenfotec.examen3.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String continente;
	private double superficieTerrestre;
	private double superficieMaritima;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "pais", cascade = CascadeType.ALL)
	@Builder.Default
	private Set<Provincia> divisionPolitica = new HashSet();
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "pais", cascade = CascadeType.ALL)
	@Builder.Default
	private Set<Region> divisionBiologica = new HashSet();
}
