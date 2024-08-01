package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValidarDatos extends ParamGenerico{
	String numCotizacion;
	String numCertificado;
	String posicionBien;
	
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getPosicionBien() {
		return posicionBien;
	}
	public void setPosicionBien(String posicionBien) {
		this.posicionBien = posicionBien;
	}
		
}
