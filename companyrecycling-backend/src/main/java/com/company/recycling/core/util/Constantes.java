package com.company.recycling.core.util;

public class Constantes {
	
	public static final String CADENA_VACIA = "";
	public static final String ESPACIO_BLANCO = " ";
	public static final String GUION = "-";
	public static final String ARROBA = "@";
	public static final String SPRING_CONFIG_DIRECTORY_BASE = "appcliente.spring.config.directory.base";
	public static final String SPRING_CONFIG_DIRECTORY = "appcliente.spring.config.directory";
	public static final String LOG4J_CONFIG_FILE = "appcliente.log4j.config.file";
	public static final String SECRET_KEY = "BBVA";	
	
	
	public static final String SCHEMA_CLIENTE = "clientes";
	
	public static final String SP_COUNT_CLIENTE  = "{ ? =  CALL clientes.sp_count_clientes(?,?,?) }";
	public static final String SP_SELECT_CLIENTE = "{ ? =  CALL clientes.sp_listar_clientes(?,?,?) }";
	public static final String SP_CREATE_CLIENTE = "{ ? =  CALL sp_agregar_cliente(?,?,?,?,?) }";
	public static final String SP_UPDATE_CLIENTE = "{ CALL SP_ACTUALIZAR_cliente(?,?,?,?,?,?) }";
	public static final String SP_DELETE_CLIENTE = "{ CALL sp_delete_cliente(?) }";
	public static final String SP_KPI_CLIENTE = "{ CALL sp_kpi_clientes(?,?) }";
}
