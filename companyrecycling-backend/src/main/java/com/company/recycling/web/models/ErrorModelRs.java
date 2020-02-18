package com.company.recycling.web.models;


import java.io.Serializable;


public class ErrorModelRs implements Serializable {

	
	private static final long serialVersionUID = 2280401299648354981L;
	
	String codigo;
	String mensage;
	
	
	public ErrorModelRs(String codigo, String mensage) {
		super();
		this.codigo = codigo;
		this.mensage = mensage;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensage() {
		return mensage;
	}
	public void setMensage(String mensage) {
		this.mensage = mensage;
	}
	
}
