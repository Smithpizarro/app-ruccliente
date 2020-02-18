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
import com.company.recycling.web.models.FormRucModel;
import com.company.recycling.web.util.Constantes;
import com.company.recycling.web.util.UrlConstants;
import com.company.recycling.web.util.Util;


@RestController
@RequestMapping("/rest")
@Validated
public class FormRucRestController {

	protected final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	public ClienteService clienteService;
	
	
	public FormRucRestController() {
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
	


	
	@GetMapping(path = "/FormRuc/{ruc}")
	public  ResponseEntity<Object> getFormRuc(@Valid @PathVariable("ruc") String ruc) throws AppException {
		FormRucModel clientemodel;
		ErrorModelRs errorModel = null;

		try {
			clientemodel   =  Util.validacionRuc(ruc);
			if(!clientemodel.getRazon_social().equals("RUC no registrado")) {
            return new ResponseEntity<Object>(clientemodel, HttpStatus.OK);
			}else {
	        
			errorModel = new ErrorModelRs(Constantes.CodigoError.RUC_NO_EXISTE.getCodigo(),Constantes.CodigoError.RUC_NO_EXISTE.getMensage());  
			return new ResponseEntity<Object>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
			}
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
	
	
	
}
