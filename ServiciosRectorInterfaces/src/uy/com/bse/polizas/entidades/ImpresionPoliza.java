package uy.com.bse.polizas.entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class ImpresionPoliza implements Serializable {
	private static final long serialVersionUID = -2038455423451699442L;

	private Integer codRamo;
	private Integer numEndoso;
	private Integer numPoliza;
	private Integer numCertificado;
	private String reporteSinPremio;
	private String reportePremio;
	private String codSucursal;
	private String impresionPoliza;
	private String impresionResumen;
	private String impresionCertificado;
	private String impresionAccidentesTrabajo;
	private String impresionSoa;
	private String impresionDorso;
	private String impresionCartas;
	private String impresionMercosur;
	private String urlDorso;
	private ArrayList<ImpresionMercosur> listaMercosur = new ArrayList<ImpresionMercosur>();
	private ArrayList<ImpresionSOA> listaSOA = new ArrayList<ImpresionSOA>();
	
	private ArrayList<Opcion> partes = new ArrayList<Opcion>();
	
	private ArrayList<Opcion> cartas = new ArrayList<Opcion>();
	
	public String getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
	}
	public String getImpresionPoliza() {
		return impresionPoliza;
	}
	public void setImpresionPoliza(String impresionPoliza) {
		this.impresionPoliza = impresionPoliza;
	}
	public String getImpresionResumen() {
		return impresionResumen;
	}
	public void setImpresionResumen(String impresionResumen) {
		this.impresionResumen = impresionResumen;
	}

	public String getImpresionAccidentesTrabajo() {
		return impresionAccidentesTrabajo;
	}
	public void setImpresionAccidentesTrabajo(String impresionAccidentesTrabajo) {
		this.impresionAccidentesTrabajo = impresionAccidentesTrabajo;
	}
	public String getImpresionSoa() {
		return impresionSoa;
	}
	public void setImpresionSoa(String impresionSoa) {
		this.impresionSoa = impresionSoa;
	}
	public String getImpresionDorso() {
		return impresionDorso;
	}
	public void setImpresionDorso(String impresionDorso) {
		this.impresionDorso = impresionDorso;
	}

	public String getImpresionMercosur() {
		return impresionMercosur;
	}
	public void setImpresionMercosur(String impresionMercosur) {
		this.impresionMercosur = impresionMercosur;
	}
	public ArrayList<ImpresionMercosur> getListaMercosur() {
		return listaMercosur;
	}
	public void setListaMercosur(ArrayList<ImpresionMercosur> listaMercosur) {
		this.listaMercosur = listaMercosur;
	}
	
	public void setUno(ImpresionMercosur impresionMercosur){
		listaMercosur.add(impresionMercosur);
	}
	
	public void setUno(ImpresionSOA impresionSOA){
		listaSOA.add(impresionSOA);
	}
	public ArrayList<Opcion> getPartes() {
		return partes;
	}
	public void setPartes(ArrayList<Opcion> partes) {
		this.partes = partes;
	}
	public ArrayList<Opcion> getCartas() {
		return cartas;
	}
	public void setCartas(ArrayList<Opcion> cartas) {
		this.cartas = cartas;
	}
	
	public void setUnoCarta(Opcion carta){
		cartas.add(carta);
	}
	
	public void setUnoParte(Opcion parte){
		partes.add(parte);
	}
	public String getImpresionCertificado() {
		return impresionCertificado;
	}
	public void setImpresionCertificado(String impresionCertificado) {
		this.impresionCertificado = impresionCertificado;
	}
	public String getReporteSinPremio() {
		return reporteSinPremio;
	}
	public void setReporteSinPremio(String reporteSinPremio) {
		this.reporteSinPremio = reporteSinPremio;
	}
	public String getReportePremio() {
		return reportePremio;
	}
	public void setReportePremio(String reportePremio) {
		this.reportePremio = reportePremio;
	}
	public ArrayList<ImpresionSOA> getListaSOA() {
		return listaSOA;
	}
	public void setListaSOA(ArrayList<ImpresionSOA> listaSOA) {
		this.listaSOA = listaSOA;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getUrlDorso() {
		return urlDorso;
	}
	public void setUrlDorso(String urlDorso) {
		this.urlDorso = urlDorso;
	}
	public String getImpresionCartas() {
		return impresionCartas;
	}
	public void setImpresionCartas(String impresionCartas) {
		this.impresionCartas = impresionCartas;
	}
	  
}
