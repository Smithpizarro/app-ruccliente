package com.company.recycling.web.controllers;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.recycling.core.service.ClienteService;
import com.company.recycling.core.util.AppException;
import com.company.recycling.web.models.ClienteCollectionModel;
import com.company.recycling.web.models.ClienteKpiModel;
import com.company.recycling.web.models.ClienteModel;
import com.company.recycling.web.models.ErrorModelRs;
import com.company.recycling.web.util.Constantes;


@RestController
@RequestMapping("/cliente")
@Validated
public class ClienteRestController {

	protected final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	public ClienteService clienteService;
	
	
	public ClienteRestController() {
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	  ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		ErrorModelRs errorModel = new ErrorModelRs(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
				Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
	    return new ResponseEntity<Object>(errorModel, HttpStatus.BAD_REQUEST);
	  }
     
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	  ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorModelRs errorModel = new ErrorModelRs(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
				Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
	    return new ResponseEntity<Object>(errorModel, HttpStatus.BAD_REQUEST);
	  }
	
	@GetMapping(path = "/holamundo")
	public String holaMundo() {
		return "hola como estas";
	}

	@GetMapping(path = "/listarCliente/{pagina}")
	public ResponseEntity<Object> getAllPersonaPagina(@Valid @PathVariable("pagina") String pagina) {
		ClienteCollectionModel clienteCollectionModel = null;
		ErrorModelRs errorModel = null;
        
		System.out.println("entro ");
		try {
			clienteCollectionModel = getListaPersona(pagina);

			if (clienteCollectionModel != null ) {

				return new ResponseEntity<Object>(clienteCollectionModel, HttpStatus.OK);
			} else {
				
				errorModel = new ErrorModelRs(Constantes.CodigoError.DATOS_NO_SE_ENCUENTRAN_REGISTRADOS.getCodigo(),
						Constantes.CodigoError.DATOS_NO_SE_ENCUENTRAN_REGISTRADOS.getMensage());
				return new ResponseEntity<Object>(errorModel, HttpStatus.OK);	
			}
		}
		catch (AppException e) {
			
			Constantes.CodigoError codigoError = Constantes.CodigoError .getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModel = new ErrorModelRs(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping(path = "/getCliente/{id}")
	public  ResponseEntity<Object> getCliente(@Valid @PathVariable("id") String idCliente) throws AppException {
		ClienteModel clientemodel;
		ErrorModelRs errorModel = null;

		try {
			clientemodel = clienteService.getCliente(idCliente);
            return new ResponseEntity<Object>(clientemodel, HttpStatus.OK);
			
		}
        catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModel = new ErrorModelRs(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(path = "/kpideclientes")
	public  ResponseEntity<Object> getkpideCliente() throws AppException {
		ClienteKpiModel clientekpimodel;
		ErrorModelRs errorModel = null;

		try {
			clientekpimodel = clienteService.getkpiCliente();
            return new ResponseEntity<Object>(clientekpimodel, HttpStatus.OK);
			
		}
        catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModel = new ErrorModelRs(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}	
	
	@PostMapping(path = "/createCliente")
	public ResponseEntity<Object> createCliente(@Valid @RequestBody ClienteModel cliente){
		ClienteModel clientemodel;
		ErrorModelRs errorModel = null;
		try {
			if (cliente != null) {
						 
				clientemodel = clienteService.createCliente(cliente);
				return new ResponseEntity<Object>(clientemodel, HttpStatus.CREATED);
			} else {

				errorModel = new ErrorModelRs(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
						Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
				return new ResponseEntity<Object>(errorModel, HttpStatus.BAD_REQUEST);

			}

		} catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModel = new ErrorModelRs(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/updateCliente")
	public ResponseEntity<Object> updateCliente(@Valid @RequestBody ClienteModel cliente) {

		ClienteModel clientemodel;
		ErrorModelRs errorModel = null;
		
		try {
			if (cliente != null) {
				
				clientemodel = clienteService.updateCliente(cliente);
			    return new ResponseEntity<Object>(clientemodel, HttpStatus.OK);
          
			} else {

				errorModel = new ErrorModelRs(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
						Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
				return new ResponseEntity<Object>(errorModel, HttpStatus.BAD_REQUEST);
            }

		} catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModel = new ErrorModelRs(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@DeleteMapping(path = "/deleteCliente/{id}")
	public ResponseEntity<Object> deleteCliente(@Valid @PathVariable("id") String idPersona) {

		String configuracioncampoDTOtmp ="El CLiente ha sido eliminado.";
		ErrorModelRs errorModel = null;
		try { 
			
			      clienteService.deleteCliente(idPersona);
			return new ResponseEntity<Object>(configuracioncampoDTOtmp, HttpStatus.OK);	
			} catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModel = new ErrorModelRs(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModel = new ErrorModelRs(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private ClienteCollectionModel getListaPersona(String pagina) throws Exception{
			return clienteService.findByParametros(Integer.parseInt(pagina) ,8);
    }
	
}
