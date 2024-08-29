package uy.com.bse.mibse.sp.ws.mibse.model.dto;

import java.util.ArrayList;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;


public class ResultObtenerComunicacionesCliente extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;

	private ArrayList<DatosComunicacion> comunicaciones = new ArrayList<DatosComunicacion>();

	public ArrayList<DatosComunicacion> getComunicaciones() {
		return comunicaciones;
	}

	public void setComunicaciones(ArrayList<DatosComunicacion> comunicaciones) {
		this.comunicaciones = comunicaciones;
	}

	public void setUno(DatosComunicacion comunicacion) {
		this.comunicaciones.add(comunicacion);
	}
}
