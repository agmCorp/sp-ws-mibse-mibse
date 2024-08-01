package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Beneficiario;
import uy.com.bse.cotizaciones.entidades.ObjetoBien;

public class BienCert implements Serializable{
	private Integer consecutivo;
	private String cliente;
	private String descripcion;
	private String seleccionado;	
	private Integer certificado;	
	private String fechaBaja;
	private Integer posicionBien;
	private ArrayList<DatosBienCert> datos;
	private Integer codigoBien;
	private String habilitoAnexos;
	private String habilitoTextos;
	private String habilitoListaBienes;
	private String habilitoUbicacion;
	private String habilitoBeneficiarios;
	private String habilitoAcreedores;
	private String habilitoNominas;
	private String habilitoQuitarBien;
	private String requierePadron;
	private String codPlanCobertura;
	private String etiquetaCapital;
	private Ubicacion ubicacionBien;
	private ArrayList<ObjetoBien> objetosBien = new ArrayList<ObjetoBien>();
	private ArrayList<AcreedorBien> acreedoresBien = new ArrayList<AcreedorBien>();
	private ArrayList<Beneficiario> beneficiariosBien = new ArrayList<Beneficiario>();
	private ArrayList<Cobertura> coberturas = new ArrayList<Cobertura>();

	
	
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}

	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}

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

	public BienCert(){
		this.datos = new ArrayList<DatosBienCert>();
	}
	
	public Integer getCodigoBien() {
		return codigoBien;
	}

	public void setCodigoBien(Integer codigoBien) {
		this.codigoBien = codigoBien;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Integer getPosicionBien() {
		return posicionBien;
	}
	public void setPosicionBien(Integer posicionBien) {
		this.posicionBien = posicionBien;
	}
	public ArrayList<DatosBienCert> getDatos() {
		return datos;
	}
	public void setDatos(ArrayList<DatosBienCert> datos) {
		this.datos = datos;
	}
	public Integer getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(String seleccionado) {
		this.seleccionado = seleccionado;
	}
	public Integer getCertificado() {
		return certificado;
	}
	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}
	
	public void setUnDatoBienCert(DatosBienCert item){
		this.datos.add(item);
	}
	

	public String getHabilitoQuitarBien() {
		return habilitoQuitarBien;
	}

	public void setHabilitoQuitarBien(String habilitoQuitarBien) {
		this.habilitoQuitarBien = habilitoQuitarBien;
	}
	
	

	public String getRequierePadron() {
		return requierePadron;
	}

	public void setRequierePadron(String requierePadron) {
		this.requierePadron = requierePadron;
	}

	public BienCert(Integer consecutivo, String cliente, String descripcion,
			String seleccionado, Integer certificado, String fechaBaja,
			Integer posicionBien, ArrayList<DatosBienCert> datos,
			Integer codigoBien) {
		super();
		this.consecutivo = consecutivo;
		this.cliente = cliente;
		this.descripcion = descripcion;
		this.seleccionado = seleccionado;
		this.certificado = certificado;
		this.fechaBaja = fechaBaja;
		this.posicionBien = posicionBien;
		this.datos = datos;
		this.codigoBien = codigoBien;
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

	public ArrayList<ObjetoBien> getObjetosBien() {
		return objetosBien;
	}

	public void setObjetosBien(ArrayList<ObjetoBien> objetosBien) {
		this.objetosBien = objetosBien;
	}

	public ArrayList<AcreedorBien> getAcreedoresBien() {
		return acreedoresBien;
	}

	public void setAcreedoresBien(ArrayList<AcreedorBien> acreedoresBien) {
		this.acreedoresBien = acreedoresBien;
	}

	public ArrayList<Beneficiario> getBeneficiariosBien() {
		return beneficiariosBien;
	}

	public void setBeneficiariosBien(ArrayList<Beneficiario> beneficiariosBien) {
		this.beneficiariosBien = beneficiariosBien;
	}
	
	public void setUnObjeto(ObjetoBien item){
		this.objetosBien.add(item);
	}
	
	public void setUnAcreedor(AcreedorBien item){
		this.acreedoresBien.add(item);
	}
	
	public void setUnBeneficiario(Beneficiario item){
		this.beneficiariosBien.add(item);
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
}
