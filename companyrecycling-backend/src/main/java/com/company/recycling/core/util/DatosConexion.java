
package com.company.recycling.core.util;

public class DatosConexion {
 
	private String driverclassname;
	private String url;
	private String username;
	private String password;
	public DatosConexion() {

		this.url = "jdbc:postgresql://ec2-23-23-195-205.compute-1.amazonaws.com/dddmamf7r0tndu?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		this.driverclassname = "org.postgresql.Driver";
		this.username = "pallpmdhbzuwqz";
		this.password = "f731b3583545ec2ee481dd579d61d43d1e4c056439465cedbf4abf67edb6e981";
		
//		this.url = "jdbc:postgresql://localhost:5433/postgres";
// 		this.driverclassname = "org.postgresql.Driver";
//		this.username = "postgres";
//		this.password = "Arlette123";
//		
	}
	public DatosConexion(String driverclassname, String url, String username, String password) {
	super();
	this.driverclassname = driverclassname;
	this.url = url;
	this.username = username;
	this.password = password;
   }
	public String getDriverclassname() {
		return driverclassname;
	}
	public void setDriverclassname(String driverclassname) {
		this.driverclassname = driverclassname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
 }	
