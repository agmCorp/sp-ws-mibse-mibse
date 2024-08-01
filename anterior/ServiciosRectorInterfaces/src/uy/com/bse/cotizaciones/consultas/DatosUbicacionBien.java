package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class DatosUbicacionBien implements Serializable{
	private BienCert datosBien;
	private	Ubicacion datosUbicacion;
	
	
	public BienCert getDatosBien() {
		return datosBien;
	}
	public void setDatosBien(BienCert datosBien) {
		this.datosBien = datosBien;
	}
	public Ubicacion getDatosUbicacion() {
		return datosUbicacion;
	}
	public void setDatosUbicacion(Ubicacion datosUbicacion) {
		this.datosUbicacion = datosUbicacion;
	}
	
	
}