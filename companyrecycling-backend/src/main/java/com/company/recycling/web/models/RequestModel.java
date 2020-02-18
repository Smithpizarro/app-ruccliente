package com.company.recycling.web.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RequestModel {
	 
	 public Long   clienteId;
	 @NotNull
	 
	 public String nombre;
	 @NotNull
	 public String apellido;
	 @NotNull
	 public Long edad;
	 @NotNull
	 @Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	 public String fecha_nacimiento;
	 @NotNull
	 public Long edad_muerte;
	 
	 public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Long getEdad() {
		return edad;
	}
	public void setEdad(Long edad) {
		this.edad = edad;
	}
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public Long getEdad_muerte() {
		return edad_muerte;
	}
	public void setEdad_muerte(Long edad_muerte) {
		this.edad_muerte = edad_muerte;
	}
 

 
}
