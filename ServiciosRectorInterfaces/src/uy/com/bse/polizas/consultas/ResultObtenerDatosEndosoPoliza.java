package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.EncabezadoPoliza;
import uy.com.bse.polizas.entidades.Endoso;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosEndosoPoliza extends ResultGenerico{
	private EncabezadoPoliza encabezado;
	private Endoso datosEndoso;

	public EncabezadoPoliza getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(EncabezadoPoliza encabezado) {
		this.encabezado = encabezado;
	}
	
	public Endoso getDatosEndoso() {
		return datosEndoso;
	}

	public void setDatosEndoso(Endoso datosEndoso) {
		this.datosEndoso = datosEndoso;
	}
	
}
