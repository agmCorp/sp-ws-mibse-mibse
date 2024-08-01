package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerTotalesCuotasPoliza extends ResultGenerico{
	private EncabezadoCuota encabezado;
	private ArrayList<DatosTotalesCuotas> datosTotalesCuotas = new ArrayList<DatosTotalesCuotas>();
	
	public EncabezadoCuota getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoCuota encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<DatosTotalesCuotas> getDatosTotalesCuotas() {
		return datosTotalesCuotas;
	}
	public void setDatosTotalesCuotas(
			ArrayList<DatosTotalesCuotas> datosTotalesCuotas) {
		this.datosTotalesCuotas = datosTotalesCuotas;
	}

	public void setUnDato(DatosTotalesCuotas item){
		this.datosTotalesCuotas.add(item);
	}
}
