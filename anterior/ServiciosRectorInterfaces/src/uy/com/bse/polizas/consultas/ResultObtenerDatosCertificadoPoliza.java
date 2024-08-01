package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.CertificadoPoliza;
import uy.com.bse.polizas.entidades.EncabezadoPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosCertificadoPoliza extends ResultGenerico{
	private EncabezadoPoliza encabezado;
	private CertificadoPoliza datosCertificado;
	
	public EncabezadoPoliza getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoPoliza encabezado) {
		this.encabezado = encabezado;
	}
	public CertificadoPoliza getDatosCertificado() {
		return datosCertificado;
	}
	public void setDatosCertificado(CertificadoPoliza datosCertificado) {
		this.datosCertificado = datosCertificado;
	}	

}
