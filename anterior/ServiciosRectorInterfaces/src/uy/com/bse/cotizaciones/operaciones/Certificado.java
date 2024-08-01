package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Bien;
import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.cotizaciones.entidades.Anexo;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;

public class Certificado implements Serializable{
	
	private Integer numCertificado;
	private ArrayList<PlanCobertura> listaPlanesCobertura = new ArrayList<PlanCobertura>();
	private ArrayList<Anexo> anexos = new ArrayList<Anexo>();
	private String permiteAnular;
	private String habilitoInsertar;
	private String existeAsegurado;
	private String requiereAsegurado;
	private String activo;
	private String codPlanCobertura;
	private String descPlanCobertura;
	private String certificadoNuevo;
	private String anulado;
	private Cliente asegurado;
	private ArrayList<DireccionEC> direccionesAsegurado = new ArrayList<DireccionEC>();
	private ArrayList<BienCert> bienCert = new ArrayList<BienCert>();
	private ArrayList<Bien> bien = new ArrayList<Bien>();
	private ArrayList<BienCobertura> bienesCobertura = new ArrayList<BienCobertura>();
	private ArrayList<PlanCobertura> listaPosiblesPlanesCobertura = new ArrayList<PlanCobertura>();
	
	public ArrayList<PlanCobertura> getListaPosiblesPlanesCobertura() {
		return listaPosiblesPlanesCobertura;
	}
	public void setListaPosiblesPlanesCobertura(ArrayList<PlanCobertura> listaPosiblesPlanesCobertura) {
		this.listaPosiblesPlanesCobertura = listaPosiblesPlanesCobertura;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public ArrayList<PlanCobertura> getListaPlanesCobertura() {
		return listaPlanesCobertura;
	}
	public void setListaPlanesCobertura(
			ArrayList<PlanCobertura> listaPlanesCobertura) {
		this.listaPlanesCobertura = listaPlanesCobertura;
	}
	
	public void setUnaCobertura(PlanCobertura planCobertura){
		listaPlanesCobertura.add(planCobertura);	
	}
	
	public void setUnaPosibleCobertura(PlanCobertura planCobertura){
		listaPosiblesPlanesCobertura.add(planCobertura);	
	}
	public ArrayList<Anexo> getAnexos() {
		return anexos;
	}
	public void setAnexos(ArrayList<Anexo> anexos) {
		this.anexos = anexos;
	}

	public void setUnAnexo(Anexo item){
		this.anexos.add(item);
	}
	public String getPermiteAnular() {
		return permiteAnular;
	}
	public void setPermiteAnular(String permiteAnular) {
		this.permiteAnular = permiteAnular;
	}
	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}
	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}
	public String getExisteAsegurado() {
		return existeAsegurado;
	}
	public void setExisteAsegurado(String existeAsegurado) {
		this.existeAsegurado = existeAsegurado;
	}
	public Cliente getAsegurado() {
		return asegurado;
	}
	public void setAsegurado(Cliente asegurado) {
		this.asegurado = asegurado;
	}
	public ArrayList<DireccionEC> getDireccionesAsegurado() {
		return direccionesAsegurado;
	}
	public void setDireccionesAsegurado(ArrayList<DireccionEC> direccionesAsegurado) {
		this.direccionesAsegurado = direccionesAsegurado;
	}
	public void setUnaDireccion(DireccionEC item){
		this.direccionesAsegurado.add(item);
	}
	public ArrayList<BienCert> getBienCert() {
		return bienCert;
	}
	public void setBienCert(ArrayList<BienCert> bienCert) {
		this.bienCert = bienCert;
	}	
	public void setUnBienCert(BienCert bien){
		this.bienCert.add(bien);
	}
	public ArrayList<BienCobertura> getBienes() {
		return bienesCobertura;
	}
	public void setBienes(ArrayList<BienCobertura> bienes) {
		this.bienesCobertura = bienes;
	}
	public void setUnElemento(BienCobertura item){
		this.bienesCobertura.add(item);
	}
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
	public ArrayList<BienCobertura> getBienesCobertura() {
		return bienesCobertura;
	}
	public void setBienesCobertura(ArrayList<BienCobertura> bienesCobertura) {
		this.bienesCobertura = bienesCobertura;
	}
	public String getDescPlanCobertura() {
		return descPlanCobertura;
	}
	public void setDescPlanCobertura(String descPlanCobertura) {
		this.descPlanCobertura = descPlanCobertura;
	}
	public ArrayList<Bien> getBien() {
		return bien;
	}
	public void setBien(ArrayList<Bien> bien) {
		this.bien = bien;
	}	
	public void setUnBien(Bien item){
		this.bien.add(item);
	}
	public String getRequiereAsegurado() {
		return requiereAsegurado;
	}
	public void setRequiereAsegurado(String requiereAsegurado) {
		this.requiereAsegurado = requiereAsegurado;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getCertificadoNuevo() {
		return certificadoNuevo;
	}
	public void setCertificadoNuevo(String certificadoNuevo) {
		this.certificadoNuevo = certificadoNuevo;
	}
	public String getAnulado() {
		return anulado;
	}
	public void setAnulado(String anulado) {
		this.anulado = anulado;
	}
	
}
