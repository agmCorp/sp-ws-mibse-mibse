package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAcreedoresFiltrados extends ParamGenerico {

	private static final long serialVersionUID = 4124924838257093737L;
	private String filtroNombre;

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

}
