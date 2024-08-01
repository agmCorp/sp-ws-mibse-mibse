package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.DatosContratante;
import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultNuevaCotizacion extends ResultGenerico{
	private DatosCotizacion datosCotizacion;
	private DatosContratante datosContratante;
	private ArrayList<Certificado> certificados;
	public ResultNuevaCotizacion(){
			this.certificados = new ArrayList<Certificado>();
	}
	
	public DatosCotizacion getDatosCotizacion() {
		return datosCotizacion;
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
	
	public ArrayList<Certificado> getCertificados() {
		return certificados;
	}

	public void setCertificados(ArrayList<Certificado> certificados) {
		this.certificados = certificados;
	}
	
	public void setUnCertificado(Certificado certificado){
		this.certificados.add(certificado);
	}
	
}
