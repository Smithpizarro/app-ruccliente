package com.company.recycling.core.bean;

public class Cliente {
 public Long   clienteId;
 public String nombre;
 public String apellido;
 public Long edad;
 public String fecha_nacimiento;
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
