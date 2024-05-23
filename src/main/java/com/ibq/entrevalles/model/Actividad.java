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
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titulo;
	@Lob
    @Column(columnDefinition = "LONGBLOB")
	private byte[] imagen;
	
	private String tipoDeporte;
	
	private String descripcion;
	@Column(length = 1500)
	
	private String informacion;
	
	private Double precio;
	
	private String localizacion;
	
	@OneToMany(mappedBy="actividad")
	private List<ReservaAct> reservas;
	
	@OneToMany(mappedBy="actividad")
	private List<ImagenActividad> imagenes;
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
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

	public String getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	private String tipoActividad;

	public String getTipoDeporte() {
		return tipoDeporte;
	}

	public void setTipoDeporte(String tipoDeporte) {
		this.tipoDeporte = tipoDeporte;
	}

	public List<ReservaAct> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaAct> reservas) {
		this.reservas = reservas;
	}

	public List<ImagenActividad> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<ImagenActividad> imagenes) {
		this.imagenes = imagenes;
	}
	


}
