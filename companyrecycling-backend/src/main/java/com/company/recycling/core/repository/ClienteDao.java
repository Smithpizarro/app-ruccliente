package com.company.recycling.core.repository;

import java.util.List;

import com.company.recycling.core.bean.Cliente;
import com.company.recycling.core.bean.ClienteKpi;
import com.company.recycling.core.util.AppException;




public interface ClienteDao {

	
	public Integer listaTablaByParametrosTotalCount(Integer pagina, Integer paginado) throws AppException;
	
	public List<Cliente> listaTablaByParametros( Integer pagina, Integer paginado) throws AppException;
   
	public  Cliente getCliente(String idPersona) throws AppException;

	public  Cliente createCliente(Cliente persona) throws AppException;
    public  Cliente updateCliente(Cliente persona) throws AppException;
  
    public  void deleteCliente(String idPersona) throws AppException;
    
    public  ClienteKpi getKpiCliente() throws AppException;
    
}
