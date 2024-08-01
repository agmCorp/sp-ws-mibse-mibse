package uy.com.bse.consultas.operaciones;

import uy.com.bse.consultas.entidades.DetallePolizaFlotante;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDetallePolizaFlotante extends ResultGenerico{

	private static final long serialVersionUID = 1053006948145293687L;
	
	
	private DetallePolizaFlotante dato;


	public DetallePolizaFlotante getDato() {
		return dato;
	}


	public void setDato(DetallePolizaFlotante dato) {
		this.dato = dato;
	}
	
	
}
