package com.ibq.entrevalles.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Experiencia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titulo;
	@Lob
    @Column(columnDefinition = "LONGBLOB")
	private byte[] imagen;
	
	private Integer habitaciones;
	
	private String descripcion;
	@Column(length = 1500)
	private String informacion;
	
	private Double precio;
	
	private String localizacion;
	
	private Integer banios;
	
	private String tipoAlojamiento;
	
	
	@OneToMany(mappedBy="experiencia")
	private List<Reserva> reservas;
	
	@OneToMany(mappedBy="experiencia")
	private List<ImagenExperiencia> imagenes;
	
	@OneToMany(mappedBy="experiencia")
	private List<Equipamiento> equipamientos;
	
	@OneToMany(mappedBy="experiencia")
	private List<Restriccion> restricciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Integer getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Integer getBanios() {
		return banios;
	}

	public void setBanios(Integer banios) {
		this.banios = banios;
	}

	public String getTipoAlojamiento() {
		return tipoAlojamiento;
	}

	public void setTipoAlojamiento(String tipoAlojamiento) {
		this.tipoAlojamiento = tipoAlojamiento;
	}

	public List<Equipamiento> getEquipamientos() {
		return equipamientos;
	}

	public void setEquipamientos(List<Equipamiento> equipamientos) {
		this.equipamientos = equipamientos;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public List<Restriccion> getRestricciones() {
		return restricciones;
	}

	public void setRestricciones(List<Restriccion> restricciones) {
		this.restricciones = restricciones;
	}

	public List<ImagenExperiencia> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<ImagenExperiencia> imagenes) {
		this.imagenes = imagenes;
	}

	
	
	
	
}
