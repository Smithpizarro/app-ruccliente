package com.company.recycling.web.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class ClienteKpiModel {
	 
	@NotNull
	 public BigDecimal edadPromedio;
	 
	@NotNull
	 public BigDecimal desviacionEstandar;

	public BigDecimal getEdadPromedio() {
		return edadPromedio;
	}

	public void setEdadPromedio(BigDecimal edadPromedio) {
		this.edadPromedio = edadPromedio;
	}

	public BigDecimal getDesviacionEstandar() {
		return desviacionEstandar;
	}

	public void setDesviacionEstandar(BigDecimal desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}
 
}
