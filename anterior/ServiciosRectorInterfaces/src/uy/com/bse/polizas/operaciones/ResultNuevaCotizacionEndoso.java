package uy.com.bse.polizas.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.DatosContratante;
import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultNuevaCotizacionEndoso extends ResultGenerico{
	private DatosCotizacion datosCotizacion;
	private DatosContratante datosContratante;
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();
	private ValidacionGenerica confirmacion;
	
	public DatosCotizacion getDatosCotizacion() {
		return datosCotizacion;
	}
	public ArrayList<Certificado> getCertificados() {
		return certificados;
	}
	public void setCertificados(ArrayList<Certificado> certificados) {
		this.certificados = certificados;
	}
	public ValidacionGenerica getConfirmacion() {
		return confirmacion;
	}
	public void setConfirmacion(ValidacionGenerica confirmacion) {
		this.confirmacion = confirmacion;
	}
	public void setDatosCotizacion(DatosCotizacion datosCotizacion) {
		this.datosCotizacion = datosCotizacion;
	}
	public DatosContratante getDatosContratante() {
		return datosContratante;
	}
	public void setDatosContratante(DatosContratante datosContratante) {
		this.datosContratante = datosContratante;
	}
	public void setUnCertificado(Certificado cert) {
		certificados.add(cert);
	}
	
}
