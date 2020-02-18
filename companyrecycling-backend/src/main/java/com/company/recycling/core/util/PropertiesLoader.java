package com.company.recycling.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

public class PropertiesLoader {

	private static final String SYSTEM_USER_DIR = "user.dir";
	private static final String FILE_NOT_FOUND_MSG = "Archivo de propiedades %s no se ha encontrado...";
	private static final String SRC_MAIN_RESOURCES = "src/main/resources";

	public Properties load(String fileName, ServletContext servletContext) {
		Properties prop = new Properties();
		InputStream iStream = null;
		try {
			if(servletContext != null) {
				iStream = findFile(fileName, servletContext);				
			}else {
				iStream = new FileInputStream(fileName);
			}
			prop.load(iStream);
		}
		catch (IOException ignore) {
		}
		finally {
			if (iStream != null) {
				try {
					iStream.close();
				}
				catch (IOException ignore) {
				}
			}
		}
		return prop;
	}

	private InputStream findFile(String fileName, ServletContext servletContext) throws FileNotFoundException {
		InputStream iStream = findInWorkingDirectory(fileName);
		if (iStream == null)
			iStream = findInServletContext(fileName, servletContext);
		if (iStream == null)
			iStream = findInClasspath(fileName);
		if (iStream == null)
			iStream = findInSourceDirectory(fileName);
		if (iStream == null)
			throw new FileNotFoundException(String.format(FILE_NOT_FOUND_MSG, fileName));
		return iStream;
	}

	private InputStream findInServletContext(String fileName, ServletContext servletContext) throws FileNotFoundException {
		return servletContext.getResourceAsStream(fileName);
	}
	
	private InputStream findInSourceDirectory(String fileName) throws FileNotFoundException {
		return new FileInputStream(SRC_MAIN_RESOURCES + fileName);
	}

	private InputStream findInClasspath(String fileName) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	}

	private InputStream findInWorkingDirectory(String fileName) {
		try {
			return new FileInputStream(System.getProperty(SYSTEM_USER_DIR) + fileName);
		}
		catch (FileNotFoundException e) {
			return null;
		}
	}
}
