package com.company.recycling.web.models;


import java.io.Serializable;
import java.util.List;



public class RequestCollectionModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<ClienteModel> listaRequest;
	private PaginacionModel paginacion;
	
	
	public List<ClienteModel> getListaRequest() {
		return listaRequest;
	}
	public void setListaRequest(List<ClienteModel> listaRequest) {
		this.listaRequest = listaRequest;
	}
	public PaginacionModel getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(PaginacionModel paginacion) {
		this.paginacion = paginacion;
	}
	


	
	


}
