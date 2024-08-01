package uy.com.bse.recuotificacion;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerConsolidado extends ResultGenerico{
	private Consolidado datosConsolidado;

	public Consolidado getDatosConsolidado() {
		return datosConsolidado;
	}

	public void setDatosConsolidado(Consolidado datosConsolidado) {
		this.datosConsolidado = datosConsolidado;
	}

}
