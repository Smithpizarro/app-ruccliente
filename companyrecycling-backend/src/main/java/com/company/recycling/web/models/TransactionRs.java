package com.company.recycling.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class TransactionRs<T> {
	@JsonProperty("codRpta")
	private String codigo="7002";
	
	@JsonProperty("desRpta")
	private String descripcion="Ocurrio un error";
	
	@JsonProperty("detRpta")
	@JsonInclude(Include.NON_NULL)
	private T respuesta;
	
	public void isSuccess(){
		codigo="0000";
		descripcion="Respuesta Exitosa";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public T getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(T respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
