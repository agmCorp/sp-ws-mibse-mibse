package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Componente;
import uy.com.bse.cotizaciones.entidades.Cumulo;
import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.cotizaciones.operaciones.PlanPagoCotizacion;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatosCotizacion extends ResultGenerico{
	private DatosCotizacion cotizacion;
	private Cliente cliente;
	private ArrayList<DireccionEC> direccionesCliente = new ArrayList<DireccionEC>();
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();
	
	private ArrayList<PlanPagoCotizacion> planesPago = new ArrayList<PlanPagoCotizacion>();
	private ArrayList<Componente> componentes = new ArrayList<Componente>();
	private ArrayList<FueraPauta> fuerasPauta = new ArrayList<FueraPauta>();
	private ArrayList<Cumulo> cumulos = new ArrayList<Cumulo>();
	private ArrayList<Certificado> certificadosResumen = new ArrayList<Certificado>();
	private ArrayList<String> titulos = new ArrayList<String>();
	private ArrayList<Certificado> certificadosCobertura = new ArrayList<Certificado>();
	
	private Double premioEmision;
	private Double premioFacturacion;
	private Boolean esUnipersonal;
	private Boolean enviarFacturaEmail;
	private Boolean emitirConRUT;

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
	
	public void setUnaDireccion(DireccionEC item){
		this.direccionesCliente.add(item);
	}

	public ArrayList<Certificado> getCertificados() {
		return certificados;
	}

	public void setCertificados(ArrayList<Certificado> certificados) {
		this.certificados = certificados;
	}
	
	public void setUnCertificado(Certificado item){
		this.certificados.add(item);
	}

	public void setUnPlanPagoCotizaciones(PlanPagoCotizacion item){
		this.planesPago.add(item);
	}

	public ArrayList<PlanPagoCotizacion> getPlanesPago() {
		return planesPago;
	}

	public void setPlanesPago(ArrayList<PlanPagoCotizacion> planesPago) {
		this.planesPago = planesPago;
	}

	public ArrayList<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(ArrayList<Componente> componentes) {
		this.componentes = componentes;
	}
	public void setUnComponente(Componente item){
		this.componentes.add(item);
	}

	public Double getPremioEmision() {
		return premioEmision;
	}

	public void setPremioEmision(Double premioEmision) {
		this.premioEmision = premioEmision;
	}

	public Double getPremioFacturacion() {
		return premioFacturacion;
	}

	public void setPremioFacturacion(Double premioFacturacion) {
		this.premioFacturacion = premioFacturacion;
	}

	public ArrayList<Cumulo> getCumulos() {
		return cumulos;
	}

	public void setCumulos(ArrayList<Cumulo> cumulos) {
		this.cumulos = cumulos;
	}

	public ArrayList<FueraPauta> getFuerasPauta() {
		return fuerasPauta;
	}

	public void setFuerasPauta(ArrayList<FueraPauta> fuerasPauta) {
		this.fuerasPauta = fuerasPauta;
	}
	public void setUnaFueraPauta(FueraPauta item){
		this.fuerasPauta.add(item);
	}

	public ArrayList<Certificado> getCertificadosResumen() {
		return certificadosResumen;
	}

	public void setCertificadosResumen(ArrayList<Certificado> certificadosResumen) {
		this.certificadosResumen = certificadosResumen;
	}
	public void setUnCertificadoResumen(Certificado item){
		this.certificadosResumen.add(item);
	}

	public ArrayList<String> getTitulos() {
		return titulos;
	}

	public void setTitulos(ArrayList<String> titulos) {
		this.titulos = titulos;
	}
	public void setUnTitulo(String item){
		this.titulos.add(item);
	}

	public ArrayList<Certificado> getCertificadosCobertura() {
		return certificadosCobertura;
	}

	public void setCertificadosCobertura(
			ArrayList<Certificado> certificadosCobertura) {
		this.certificadosCobertura = certificadosCobertura;
	}
	public void setUnCertificadoCobertura(Certificado item){
		this.certificadosCobertura.add(item);
	}

	public Boolean getEsUnipersonal() {
		return esUnipersonal;
	}

	public void setEsUnipersonal(Boolean esUnipersonal) {
		this.esUnipersonal = esUnipersonal;
	}

	public Boolean getEnviarFacturaEmail() {
		return enviarFacturaEmail;
	}

	public void setEnviarFacturaEmail(Boolean enviarFacturaEmail) {
		this.enviarFacturaEmail = enviarFacturaEmail;
	}

	public Boolean getEmitirConRUT() {
		return emitirConRUT;
	}

	public void setEmitirConRUT(Boolean emitirConRUT) {
		this.emitirConRUT = emitirConRUT;
	}
}
