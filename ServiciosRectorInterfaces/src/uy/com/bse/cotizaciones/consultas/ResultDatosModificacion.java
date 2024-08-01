package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatosModificacion extends ResultGenerico{
	private ArrayList<BienCert> bien;
	private DatosCotizacion cotizacion;
	private Cliente cliente;
	private ArrayList<DireccionEC> direccionesCliente = new ArrayList<DireccionEC>();
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();
	
	
	public ResultDatosModificacion(){
		this.bien = new ArrayList<BienCert>();
	}

	public ArrayList<BienCert> getBien() {
		return bien;
	}

	public void setBien(ArrayList<BienCert> bien) {
		this.bien = bien;
	}
	
	public void setUnBienCert(BienCert bien){
		this.bien.add(bien);
	}

	public DatosCotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(DatosCotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<DireccionEC> getDireccionesCliente() {
		return direccionesCliente;
	}

	public void setDireccionesCliente(ArrayList<DireccionEC> direccionesCliente) {
		this.direccionesCliente = direccionesCliente;
	}

	public ArrayList<Certificado> getCertificados() {
		return certificados;
	}

	public void setCertificados(ArrayList<Certificado> certificados) {
		this.certificados = certificados;
	}
	
	public void setUnaDireccion(DireccionEC item){
		this.direccionesCliente.add(item);
	}
	
	public void setUnCertificado(Certificado item){
		this.certificados.add(item);
	}
}
