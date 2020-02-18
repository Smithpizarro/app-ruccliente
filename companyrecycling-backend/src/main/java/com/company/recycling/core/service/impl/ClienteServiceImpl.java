package com.company.recycling.core.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.recycling.core.bean.Cliente;
import com.company.recycling.core.bean.ClienteKpi;
import com.company.recycling.core.repository.ClienteDao;
import com.company.recycling.core.service.ClienteService;
import com.company.recycling.core.util.AppException;
import com.company.recycling.web.models.ClienteCollectionModel;
import com.company.recycling.web.models.ClienteKpiModel;
import com.company.recycling.web.models.ClienteModel;
import com.company.recycling.web.models.PaginacionModel;
import com.company.recycling.web.util.Constantes;
import com.company.recycling.web.util.Util;



@Service
public class ClienteServiceImpl implements ClienteService {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	public ClienteDao clienteDao;



	@Override
	public ClienteCollectionModel findByParametros(Integer pagina, Integer paginado) throws AppException {

		Integer totalRegistros = clienteDao.listaTablaByParametrosTotalCount(pagina, paginado);
		System.out.println("totalRegistros= "+ totalRegistros);
        PaginacionModel paginacion = new PaginacionModel();
		paginacion.setPagina(pagina);
		paginacion.setCantidadRegistros(paginado);
		paginacion.setTotalRegistros(totalRegistros);
		paginacion.setIndicadorPaginacion(true);
		Double cantidadPaginas = Math.ceil(paginacion.getTotalRegistros() / new Double(paginacion.getCantidadRegistros()));
		paginacion.setCantidadPaginas(cantidadPaginas.intValue());

		List<Cliente> listaTabla = clienteDao.listaTablaByParametros(pagina, paginado);

		ClienteCollectionModel personaCollection = new ClienteCollectionModel();
		personaCollection.setListaCliente(Util.mapAtoBB(listaTabla));
        personaCollection.setPaginacion(paginacion);

		return personaCollection;
		
		
	}

	@Override
	public ClienteModel getCliente(String idPersona) throws AppException {

		Cliente cliente = null;
		String mensajeError = null;

		try {

			cliente = clienteDao.getCliente(idPersona);
	         return Util.mapAtoB(cliente);
          } catch (Exception e) {

			mensajeError = e.getMessage();
			logger.info(mensajeError);

			if (e instanceof AppException) {
				Constantes.CodigoError codigoError = Constantes.CodigoError
						.getCodigoError(((AppException) e).getCodigo());
				if (codigoError != null)
					mensajeError = codigoError.getCodigo() + ": " + codigoError.getMensage();
			}
			throw e;

		}
	}
	
	@Override
	public ClienteKpiModel getkpiCliente() throws AppException {

		ClienteKpi clienteKpi = null;
		String mensajeError = null;

		try {

			clienteKpi = clienteDao.getKpiCliente();
	         return Util.mapAtoB(clienteKpi);
          } catch (Exception e) {

			mensajeError = e.getMessage();
			logger.info(mensajeError);

			if (e instanceof AppException) {
				Constantes.CodigoError codigoError = Constantes.CodigoError
						.getCodigoError(((AppException) e).getCodigo());
				if (codigoError != null)
					mensajeError = codigoError.getCodigo() + ": " + codigoError.getMensage();
			}
			throw e;

		}
	}
		
	

	@Override
	public ClienteModel createCliente(ClienteModel clienteModel) throws AppException {

		Cliente cliente = null;
    	String mensajeError = null;

		try {
		
			cliente = clienteDao.createCliente(Util.mapAtoB(clienteModel));
			return Util.mapAtoB(cliente);

		} catch (Exception e) {

			mensajeError = e.getMessage();

			logger.info(mensajeError);

			if (e instanceof AppException) {
				Constantes.CodigoError codigoError = Constantes.CodigoError
						.getCodigoError(((AppException) e).getCodigo());
				if (codigoError != null)
					mensajeError = codigoError.getCodigo() + ": " + codigoError.getMensage();
			}
			throw e;
		}		
	}

	@Override
	public ClienteModel updateCliente(ClienteModel clienteModel) throws AppException {

		Cliente cliente = null;
        String mensajeError = null;

		try {
			
			cliente = clienteDao.updateCliente(Util.mapAtoB(clienteModel));
			return  Util.mapAtoB(cliente);

		} catch (Exception e) {

			mensajeError = e.getMessage();

			logger.info(mensajeError);

			if (e instanceof AppException) {
				Constantes.CodigoError codigoError = Constantes.CodigoError
						.getCodigoError(((AppException) e).getCodigo());
				if (codigoError != null)
					mensajeError = codigoError.getCodigo() + ": " + codigoError.getMensage();
			}
			throw e;

		}
	}

	@Override
	public void deleteCliente(String idCliente) throws AppException {

		String mensajeError = null;

		try {
		
			clienteDao.deleteCliente(idCliente);
		   return;

		} catch (Exception e) {

			mensajeError = e.getMessage();

			logger.info(mensajeError);

			if (e instanceof AppException) {
				Constantes.CodigoError codigoError = Constantes.CodigoError
						.getCodigoError(((AppException) e).getCodigo());
				if (codigoError != null)
					mensajeError = codigoError.getCodigo() + ": " + codigoError.getMensage();
			}
			throw e;

		}
	}
}
