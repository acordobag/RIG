package com.cenfotec.examen3.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RIG {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pais;
	private String continente;
	private double superficieTerrestre;
	private double superficieMaritima;
	private List<Provincia> divisionPolitica;
	private List<Regiones> divisionBiologica;
}
