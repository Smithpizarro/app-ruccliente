package com.company.recycling.core.service;

import com.company.recycling.core.util.AppException;
import com.company.recycling.web.models.ClienteCollectionModel;
import com.company.recycling.web.models.ClienteKpiModel;
import com.company.recycling.web.models.ClienteModel;


public interface ClienteService {

	
	public  ClienteCollectionModel findByParametros (Integer pagina , Integer paginado)  throws AppException;
	public  ClienteModel getCliente(String idCliente) throws AppException;
    public  ClienteModel createCliente(ClienteModel clienteModel) throws AppException;
    public  ClienteModel updateCliente(ClienteModel clienteModel) throws AppException;
    public  void deleteCliente(String idPersona) throws AppException;
    public  ClienteKpiModel getkpiCliente()  throws AppException;
    
}
