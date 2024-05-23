package com.ibq.entrevalles.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class ImagenExperiencia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Lob
    @Column(columnDefinition = "LONGBLOB")
	private byte[] imagen;
	@ManyToOne
    @JoinColumn(name="experiencia_id", nullable=false) 
	@JsonIgnore
	private Experiencia experiencia;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	public Experiencia getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(Experiencia experiencia) {
		this.experiencia = experiencia;
	}
	
	
}
