package uy.com.bse.polizas.entidades;

import uy.com.bse.cotizaciones.operaciones.PlanCobertura;

public class PlanCoberturaPoliza extends PlanCobertura{
	private String seleccionado;

	public String getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(String seleccionado) {
		this.seleccionado = seleccionado;
	}

}
