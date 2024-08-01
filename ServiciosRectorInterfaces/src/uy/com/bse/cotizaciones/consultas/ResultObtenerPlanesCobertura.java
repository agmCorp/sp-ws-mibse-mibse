package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.lovs.Moneda;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.cotizaciones.operaciones.Riesgo;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPlanesCobertura extends ResultGenerico {
	
	private static final long serialVersionUID = 9009604858871203546L;
	private Integer diaVto;
	private Moneda moneda;
	private Integer codPlanPago;
	private ArrayList<PlanPago> listaPlanesPago = new ArrayList<PlanPago>(); 
	private ArrayList<Certificado>listaCertificados = new ArrayList<Certificado>();
	private ArrayList<String> titulos = new ArrayList<String>();
	private ArrayList<Riesgo> riesgos = new ArrayList<Riesgo>();
	private Double montoCuotaCert;
	private Double montoTotalCert;
	private Integer codNivelComision;
	private Integer codNivelComisionBroker;
	private String descNivelComision;
	private String descNivelComisionBroker;
	
	
	public Integer getDiaVto() {
		return diaVto;
	}
	public void setDiaVto(Integer diaVto) {
		this.diaVto = diaVto;
	}
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	public Integer getCodPlanPago() {
		return codPlanPago;
	}
	public void setCodPlanPago(Integer codPlanPago) {
		this.codPlanPago = codPlanPago;
	}
	public ArrayList<PlanPago> getListaPlanesPago() {
		return listaPlanesPago;
	}
	public void setListaPlanesPago(ArrayList<PlanPago> listaPlanesPago) {
		this.listaPlanesPago = listaPlanesPago;
	}

	public ArrayList<Certificado> getListaCertificados() {
		return listaCertificados;
	}
	public void setListaCertificados(ArrayList<Certificado> listaCertificados) {
		this.listaCertificados = listaCertificados;
	}
	public ArrayList<String> getTitulos() {
		return titulos;
	}
	public void setTitulos(ArrayList<String> titulos) {
		this.titulos = titulos;
	}
	public Double getMontoCuotaCert() {
		return montoCuotaCert;
	}
	public void setMontoCuotaCert(Double montoCuotaCert) {
		this.montoCuotaCert = montoCuotaCert;
	}
	public Double getMontoTotalCert() {
		return montoTotalCert;
	}
	public void setMontoTotalCert(Double montoTotalCert) {
		this.montoTotalCert = montoTotalCert;
	}

	public void setUnPlanPago(PlanPago planPago){
		listaPlanesPago.add(planPago);	
	}

	public void setUnCertificado(Certificado certificado){
		listaCertificados.add(certificado);	
	}
	public void setUnTitulo(String titulo){
		titulos.add(titulo);
	}
	public Integer getCodNivelComision() {
		return codNivelComision;
	}
	public void setCodNivelComision(Integer codNivelComision) {
		this.codNivelComision = codNivelComision;
	}
	public String getDescNivelComision() {
		return descNivelComision;
	}
	public void setDescNivelComision(String descNivelComision) {
		this.descNivelComision = descNivelComision;
	}
	public Integer getCodNivelComisionBroker() {
		return codNivelComisionBroker;
	}
	public void setCodNivelComisionBroker(Integer codNivelComisionBroker) {
		this.codNivelComisionBroker = codNivelComisionBroker;
	}
	public String getDescNivelComisionBroker() {
		return descNivelComisionBroker;
	}
	public void setDescNivelComisionBroker(String descNivelComisionBroker) {
		this.descNivelComisionBroker = descNivelComisionBroker;
	}
	
	public void setUnRiesgo(Riesgo item){
		this.riesgos.add(item);
	}
	public ArrayList<Riesgo> getRiesgos() {
		return riesgos;
	}
	public void setRiesgos(ArrayList<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}
	
}
