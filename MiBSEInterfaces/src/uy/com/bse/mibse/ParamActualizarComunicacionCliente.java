package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarComunicacionCliente extends ParamGenerico {
	
	
	private static final long serialVersionUID = 2944067059496940849L;
	private DatosComunicacion comunicacion;
	
	public DatosComunicacion getComunicacion() {
		return comunicacion;
	}
	public void setComunicacion(DatosComunicacion comunicacion) {
		this.comunicacion = comunicacion;
	}
		
}
