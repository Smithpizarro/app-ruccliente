package com.company.recycling.web.models;


import java.io.Serializable;
import java.util.List;



public class ClienteCollectionModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<ClienteModel> listaCliente;
	private PaginacionModel paginacion;
	
	public List<ClienteModel> getListaCliente() {
		return listaCliente;
	}
	public void setListaCliente(List<ClienteModel> listaCliente) {
		this.listaCliente = listaCliente;
	}
	public PaginacionModel getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(PaginacionModel paginacion) {
		this.paginacion = paginacion;
	}

	
	


}
