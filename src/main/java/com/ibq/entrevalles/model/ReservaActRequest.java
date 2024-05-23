package com.ibq.entrevalles.model;

import java.util.Date;

public class ReservaActRequest {
	private Long actividad;

	private Double precioTotal;
	private Long usuario;

	private Date fechaInicio;
	private Date fechaFin;
	private Integer huespedesTotales;
	public Long getActividad() {
		return actividad;
	}
	public void setActividad(Long actividad) {
		this.actividad = actividad;
	}
	public Double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getHuespedesTotales() {
		return huespedesTotales;
	}
	public void setHuespedesTotales(Integer huespedesTotales) {
		this.huespedesTotales = huespedesTotales;
	}
	
	
}
