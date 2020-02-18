package com.company.recycling.core.repository.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.recycling.core.bean.Cliente;
import com.company.recycling.core.bean.ClienteKpi;
import com.company.recycling.core.repository.ClienteDao;
import com.company.recycling.core.util.AppException;
import com.company.recycling.core.util.Conexion;
import com.company.recycling.core.util.Constantes;


@Repository
public class ClienteDaoImpl implements ClienteDao {

	
	public ClienteDaoImpl() {
				
	}
	
	
	@Override
	public Integer listaTablaByParametrosTotalCount(Integer pagina, Integer paginado) throws AppException{
		
		CallableStatement cstmt = null;
		Long totalNotificaciones = null;
		Conexion con = null;
	
			System.out.println("entro 2");
			
			try {
				con = new Conexion();
			} catch (SQLException sqle) {
				throw new AppException(sqle);
			} catch (Exception e) {
				throw new AppException(e);
			} 
		
			try {
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema(Constantes.SCHEMA_CLIENTE);
			cstmt = con.getConexion().prepareCall(Constantes.SP_COUNT_CLIENTE);
                     
			cstmt.registerOutParameter(1, Types.BIGINT);
			
			cstmt.setLong(2, pagina);
			cstmt.setLong(3, paginado);
			cstmt.setLong(4, 0);
			cstmt.execute();
			totalNotificaciones = (cstmt.getLong(1));
            con.getConexion().commit();
     
			return totalNotificaciones.intValue();
			
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		
	}
	
	@Override
	public List<Cliente> listaTablaByParametros( Integer pagina, Integer paginado) throws AppException{
	
		List<Cliente> listapersonas = new ArrayList<>();

		CallableStatement cstmt = null;
		ResultSet rs = null;
		Conexion con = null;

		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema(Constantes.SCHEMA_CLIENTE);
			cstmt = con.getConexion().prepareCall(Constantes.SP_SELECT_CLIENTE);

			cstmt.registerOutParameter(1, Types.OTHER);
		
			cstmt.setLong(2, pagina);
			cstmt.setLong(3, paginado);
			cstmt.setLong(4, 0);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(1);

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setClienteId(rs.getLong("ID_CLIENTE"));
				cliente.setNombre((rs.getString("NOMBRE")));
				cliente.setApellido(rs.getString("APELLIDO"));
				cliente.setEdad(rs.getLong("EDAD"));
				cliente.setFecha_nacimiento(rs.getString("FECHA_NACIMIENTO"));
				cliente.setEdad_muerte(rs.getLong("EDAD_MUERTE"));
				
				listapersonas.add(cliente);
			}
			con.getConexion().commit();

		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return listapersonas;
	}
   

	@Override
	public  Cliente getCliente(String idCliente) throws AppException{
		
		Cliente cliente = new Cliente();
		Conexion con = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			con = new Conexion();
		} catch (Exception e) {
			throw new AppException(e);
		}

		try {

			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema(Constantes.SCHEMA_CLIENTE);
			cs = con.getConexion().prepareCall(Constantes.SP_SELECT_CLIENTE);

			cs.registerOutParameter(1, Types.OTHER);
			cs.setLong(2, 1);
			cs.setLong(3, 1);
			cs.setLong(4, Long.parseLong(idCliente));

			// conn.setAutoCommit(false);
			cs.executeUpdate();
			con.getConexion().commit();

			rs = (ResultSet) cs.getObject(1);
			while (rs.next()) {
				
				cliente.setClienteId(rs.getLong("ID_CLIENTE"));
				cliente.setNombre((rs.getString("NOMBRE")));
				cliente.setApellido(rs.getString("APELLIDO"));
				cliente.setEdad(rs.getLong("EDAD"));
				cliente.setFecha_nacimiento(rs.getString("FECHA_NACIMIENTO"));
				cliente.setEdad_muerte(rs.getLong("EDAD_MUERTE"));
				
				
			}
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), rs, null, cs, null);
			} catch (Exception e) {
				throw new AppException(e);
			}

		}

		return cliente;
	}
     
	@Override
	public  ClienteKpi getKpiCliente() throws AppException{
		
		ClienteKpi clienteKpi = new ClienteKpi();
		Conexion con = null;
		CallableStatement cstmt = null;
		
		try {
			con = new Conexion();
		} catch (Exception e) {
			throw new AppException(e);
		}

		try {

			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema(Constantes.SCHEMA_CLIENTE);
			cstmt = con.getConexion().prepareCall(Constantes.SP_KPI_CLIENTE);

			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.execute();
			
			clienteKpi.setEdadPromedio(cstmt.getBigDecimal(1));
			clienteKpi.setDesviacionEstandar(cstmt.getBigDecimal(2));
			con.getConexion().commit();
;
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return clienteKpi;
	}
	
	@Override
	public  Cliente createCliente(Cliente cliente) throws AppException{
		
		CallableStatement cstmt = null;
		Long resultado;
        Conexion con = null;
	
		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema(Constantes.SCHEMA_CLIENTE);
			cstmt = con.getConexion().prepareCall(Constantes.SP_CREATE_CLIENTE);

			cstmt.registerOutParameter(1, Types.BIGINT);
			cstmt.setString(2,cliente.getNombre() == null ? null : cliente.getNombre());
			cstmt.setString(3, cliente.getApellido() == null ? null :  cliente.getApellido());
			cstmt.setLong(4, cliente.getEdad() == null ? null : cliente.getEdad());
			cstmt.setString(5,cliente.getFecha_nacimiento() == null ? null : cliente.getFecha_nacimiento());
			cstmt.setLong(6, cliente.getEdad_muerte() == null ? null : cliente.getEdad_muerte());
			cstmt.execute();
			resultado = cstmt.getLong(1);

			cliente.setClienteId(resultado);

			con.getConexion().commit();
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return cliente;
	}
	
	@Override
    public  Cliente updateCliente(Cliente cliente) throws AppException{
    	
    	CallableStatement cstmt = null;

		Conexion con = null;
	
		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema(Constantes.SCHEMA_CLIENTE);
			cstmt = con.getConexion().prepareCall(Constantes.SP_UPDATE_CLIENTE);

			cstmt.setLong(1, cliente.getClienteId()==null?0:cliente.getClienteId());

			cstmt.setString(2,cliente.getNombre() == null ? null : cliente.getNombre());
			cstmt.setString(3, cliente.getApellido() == null ? null :  cliente.getApellido());
			cstmt.setLong(4, cliente.getEdad() == null ? null : cliente.getEdad());
			cstmt.setString(5,cliente.getFecha_nacimiento() == null ? null : cliente.getFecha_nacimiento());
			cstmt.setLong(6, cliente.getEdad_muerte() == null ? null : cliente.getEdad_muerte());

			cstmt.executeUpdate();

			con.getConexion().commit();
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return cliente;
    }
  
    public  void deleteCliente(String idPersona) throws AppException{
    	
    	CallableStatement cstmt = null;
		Conexion con = null;
	
		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema(Constantes.SCHEMA_CLIENTE);
			cstmt = con.getConexion().prepareCall(Constantes.SP_DELETE_CLIENTE);
			
			
			cstmt.setLong(1,  Long.parseLong(idPersona));
			
			//int rows = 
			cstmt.executeUpdate();
			con.getConexion().commit();
		    
			/*if(rows!=-1) {
				
				msjconfiguracion ="La persona con  id: "+idPersona+" se ha eliminado ";
			}*/
		
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
	}
    
    
}
