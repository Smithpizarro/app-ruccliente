package com.company.recycling.web.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.company.recycling.core.bean.Cliente;
import com.company.recycling.core.bean.ClienteKpi;
import com.company.recycling.core.util.AppException;
import com.company.recycling.web.models.ClienteKpiModel;
import com.company.recycling.web.models.ClienteModel;
import com.company.recycling.web.models.FormRucModel;

public class Util {

	public static List<ClienteModel> mapAtoBB(List<Cliente> listCliente) {

		List<ClienteModel> listCLienteModel = new ArrayList<ClienteModel>();
		ClienteModel clienteModel;
		for (Cliente cliente : listCliente) {

			clienteModel = new ClienteModel();
			clienteModel.setClienteId(cliente.getClienteId());
			clienteModel.setNombre(cliente.getNombre());
			clienteModel.setApellido(cliente.getApellido());
			clienteModel.setEdad(cliente.getEdad());
			clienteModel.setFecha_nacimiento(cliente.getFecha_nacimiento());
			clienteModel.setEdad_muerte(cliente.getEdad_muerte());
			listCLienteModel.add(clienteModel);

		}
		clienteModel = null;
		return listCLienteModel;
	}

	public static ClienteModel mapAtoB(Cliente cliente) {

		ClienteModel clienteModel = new ClienteModel();
		clienteModel.setClienteId(cliente.getClienteId());
		clienteModel.setNombre(cliente.getNombre());
		clienteModel.setApellido(cliente.getApellido());
		clienteModel.setEdad(cliente.getEdad());
		clienteModel.setFecha_nacimiento(cliente.getFecha_nacimiento());
		clienteModel.setEdad_muerte(cliente.getEdad_muerte());
		return clienteModel;
	}
	
	public static ClienteKpiModel mapAtoB(ClienteKpi clienteKpi) {

		ClienteKpiModel clienteKpiModel = new ClienteKpiModel();
		clienteKpiModel.setEdadPromedio(clienteKpi.getEdadPromedio());
		clienteKpiModel.setDesviacionEstandar(clienteKpi.getDesviacionEstandar());

		return clienteKpiModel;
	}

	public static Cliente mapAtoB(ClienteModel clienteModel) {

		Cliente cliente = new Cliente();
		cliente.setClienteId(clienteModel.getClienteId());
		cliente.setNombre(clienteModel.getNombre());
		cliente.setApellido(clienteModel.getApellido());
		cliente.setEdad(clienteModel.getEdad());
		cliente.setFecha_nacimiento(clienteModel.getFecha_nacimiento());
		cliente.setEdad_muerte(clienteModel.getEdad_muerte());

		return cliente;
	}

//	/ 
//	   public static int validacionPlaneta(String planeta) {
//	   
//	   SearchPlanetDTO search = new SearchPlanetDTO(); Map<String, String>
//	   uriVariables = new HashMap<>(); uriVariables.put("searchpla", planeta);
//	   
//	   HttpHeaders headers = new HttpHeaders();
//	   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	   headers.add("user-agent",
//	   "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"
//	   ); HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//	   
//	   
//	   ResponseEntity<SearchPlanetDTO> responseEntity = new
//	   RestTemplate().exchange("https://swapi.co/api/planets/?search={searchpla}",
//	   HttpMethod.GET, entity, new ParameterizedTypeReference<SearchPlanetDTO>() {
//	   },uriVariables); if (responseEntity != null && responseEntity.hasBody()) {
//	   search = responseEntity.getBody(); }
//	   
//	   
//	   return search.getCount(); }
//	  
	
	   public static FormRucModel validacionRuc(String ruc) throws AppException {
		
		  FormRucModel search = new FormRucModel(); 
		  Map<String, String> uriVariables = new HashMap<>(); 
		  uriVariables.put("ruc", ruc);
		  
		 HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		   headers.add("user-agent",
		   "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"
		   );
		   HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		   
		   
			ResponseEntity<FormRucModel> responseEntity = new RestTemplate().exchange("http://wsruc.com/Ruc2WS_JSON.php?tipo=2&ruc={ruc}&token=cXdlcnR5bGFtYXJja19zYUBob3RtYWlsLmNvbXF3ZXJ0eQ==", HttpMethod.GET, null,
					new ParameterizedTypeReference<FormRucModel>() {
					}, uriVariables);
		   
		   if (responseEntity != null && responseEntity.hasBody()) {
		   search = responseEntity.getBody(); 
		   }
		   
		   
		   return search;
		   }
}
