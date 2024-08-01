package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCalcularCuotasVehiculo extends ResultGenerico {

	private static final long serialVersionUID = 7619931387453650817L;
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();
	private ArrayList<Integer> coutas= new ArrayList<Integer>();
	private String fechaCotizacion;
	private String datosReporte;

	public void setUno(Certificado certificado) {
		this.certificados.add(certificado);
	}

	public ArrayList<Certificado> getCertificados() {
		return certificados;
	}

	public void setCertificados(ArrayList<Certificado> certificados) {
		this.certificados = certificados;
	}

	public ArrayList<Integer> getCoutas() {
		return coutas;
	}

	public void setCoutas(ArrayList<Integer> coutas) {
		this.coutas = coutas;
	}
	
	public void setUno(Integer cuota) {
		this.coutas.add(cuota);
	}

	public String getFechaCotizacion() {
		return fechaCotizacion;
	}

	public void setFechaCotizacion(String fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	public String getDatosReporte() {
		return datosReporte;
	}

	public void setDatosReporte(String datosReporte) {
		this.datosReporte = datosReporte;
	}

}
