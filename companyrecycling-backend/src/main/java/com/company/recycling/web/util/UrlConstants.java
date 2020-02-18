package com.company.recycling.web.util;

/**
 * @author mestrasu
 *
 */
public class UrlConstants {
	public static final String URL_APP_BASE = "/rest";	   
	public static final String URL_RECYCLING_REQUEST = "/v1/requests";
	public static final String URL_RECYCLING_PAGE_REQUEST = "/v1/request/page/{page}";
	
	public static final String URL_RECYCLING_RECYCLER = "/v1/recyclers";
	public static final String URL_RECYCLING_PAGE_RECYCLER = "/v1/recyclers/page/{page}";
	public static final String URL_RECYCLING_CAMPUS_RECYCLER = "/v1/recyclers/campus/{campus}";
	

	//Versiones de Servicios
	public static final String V1="/v1";
	public static final String V2="/v2";

	
	private UrlConstants() {
	    throw new IllegalStateException("Utility class");
	  }

}

