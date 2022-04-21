package com.bse.servicios.ws.viajeros;

import java.util.List;

import com.bse.accesodatos.eviajeros.CoberturaPrexistentes;

public class CoberturasPrexistentesResp {
	
	private String codigoError;
	private String descripcionError;
	private List<CoberturaPrexistentes> coberturasPrexistentes;
	
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setCoberturasPrexistentes(List<CoberturaPrexistentes> coberturasPrexistentes) {
		this.coberturasPrexistentes = coberturasPrexistentes;
	}

	public List<CoberturaPrexistentes> getCoberturasPrexistentes() {
		return coberturasPrexistentes;
	}

}
