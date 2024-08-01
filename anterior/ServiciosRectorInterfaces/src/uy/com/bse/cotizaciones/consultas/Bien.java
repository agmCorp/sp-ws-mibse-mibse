package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;
import java.util.ArrayList;

public class Bien implements Serializable{
	private Integer bienNum;
	private String bienDescripcion;
	private String fechaBaja;
	private ArrayList<DatosBien> datos = new ArrayList<DatosBien>();
	private Integer codBien;
	private Integer posBien;	
	private Integer consecutivo;
	private Integer certificado;
	private ArrayList <Franquicia> franquicias = new ArrayList<Franquicia>();
	private ArrayList <Cobertura> coberturas = new ArrayList<Cobertura>();
	private String habilitoAnexos;
	private String habilitoTextos;
	private String habilitoListaBienes;
	private String habilitoUbicacion;
	private String habilitoBeneficiarios;
	private String habilitoAcreedores;
	private String habilitoNominas;
	private String habilitoQuitar;
	private String etiquetaCapital;
	private Ubicacion ubicacionBien;

	
	
	public String getHabilitoAnexos() {
		return habilitoAnexos;
	}
	public void setHabilitoAnexos(String habilitoAnexos) {
		this.habilitoAnexos = habilitoAnexos;
	}
	public String getHabilitoTextos() {
		return habilitoTextos;
	}
	public void setHabilitoTextos(String habilitoTextos) {
		this.habilitoTextos = habilitoTextos;
	}
	public String getHabilitoListaBienes() {
		return habilitoListaBienes;
	}
	public void setHabilitoListaBienes(String habilitoListaBienes) {
		this.habilitoListaBienes = habilitoListaBienes;
	}
	public String getHabilitoUbicacion() {
		return habilitoUbicacion;
	}
	public void setHabilitoUbicacion(String habilitoUbicacion) {
		this.habilitoUbicacion = habilitoUbicacion;
	}
	public String getHabilitoBeneficiarios() {
		return habilitoBeneficiarios;
	}
	public void setHabilitoBeneficiarios(String habilitoBeneficiarios) {
		this.habilitoBeneficiarios = habilitoBeneficiarios;
	}
	public String getHabilitoAcreedores() {
		return habilitoAcreedores;
	}
	public void setHabilitoAcreedores(String habilitoAcreedores) {
		this.habilitoAcreedores = habilitoAcreedores;
	}
	public String getHabilitoNominas() {
		return habilitoNominas;
	}
	public void setHabilitoNominas(String habilitoNominas) {
		this.habilitoNominas = habilitoNominas;
	}
	public Integer getCertificado() {
		return certificado;
	}
	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}
	public Integer getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	public Integer getCodBien() {
		return codBien;
	}
	public void setCodBien(Integer codBien) {
		this.codBien = codBien;
	}
	public Integer getPosBien() {
		return posBien;
	}
	public void setPosBien(Integer posBien) {
		this.posBien = posBien;
	}
	public Integer getBienNum() {
		return bienNum;
	}
	public void setBienNum(Integer bienNum) {
		this.bienNum = bienNum;
	}
	public String getBienDescripcion() {
		return bienDescripcion;
	}
	public void setBienDescripcion(String bienDescripcion) {
		this.bienDescripcion = bienDescripcion;
	}
	public Bien(){
		this.datos = new ArrayList<DatosBien>();
	}	
	public ArrayList<DatosBien> getDatos() {
		return datos;
	}
	public void setDatos(ArrayList<DatosBien> datos) {
		this.datos = datos;
	}
	
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}	
	public void setUnDato(DatosBien item){
		this.datos.add(item);
	}
	public ArrayList<Franquicia> getFranquicias() {
		return franquicias;
	}
	public void setFranquicias(ArrayList<Franquicia> franquicias) {
		this.franquicias = franquicias;
	}
	public ArrayList<Cobertura> getCoberturas() {
		return coberturas;
	}
	public void setCoberturas(ArrayList<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}
	public void setUnaCobertura(Cobertura item){
		this.coberturas.add(item);
	}
	
	public void setUnaFranquicia(Franquicia item){
		this.franquicias.add(item);
	}
	public String getHabilitoQuitar() {
		return habilitoQuitar;
	}
	public void setHabilitoQuitar(String habilitoQuitar) {
		this.habilitoQuitar = habilitoQuitar;
	}
	public String getEtiquetaCapital() {
		return etiquetaCapital;
	}
	public void setEtiquetaCapital(String etiquetaCapital) {
		this.etiquetaCapital = etiquetaCapital;
	}
	public Ubicacion getUbicacionBien() {
		return ubicacionBien;
	}
	public void setUbicacionBien(Ubicacion ubicacionBien) {
		this.ubicacionBien = ubicacionBien;
	}

	
	
}
