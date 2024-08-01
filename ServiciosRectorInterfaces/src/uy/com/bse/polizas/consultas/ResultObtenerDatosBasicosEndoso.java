package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosBasicosEndoso extends ResultGenerico{
	
	private DatosCotizacion datos;
	private Cliente cliente;
	private ArrayList<DireccionEC> direccionesCliente = new ArrayList<DireccionEC>();
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();
	
	
	public DatosCotizacion getDatos() {
		return datos;
	}
	public void setDatos(DatosCotizacion datos) {
		this.datos = datos;
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
