package uy.com.bse.mibse;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarComunicacionesCliente extends ParamGenerico {
	
	private static final long serialVersionUID = 5353248848940025117L;
	private ArrayList<DatosComunicacion> comunicaciones;

	public synchronized final ArrayList<DatosComunicacion> getComunicaciones() {
		return comunicaciones;
	}

	public synchronized final void setComunicaciones(ArrayList<DatosComunicacion> comunicaciones) {
		this.comunicaciones = comunicaciones;
	}

}
