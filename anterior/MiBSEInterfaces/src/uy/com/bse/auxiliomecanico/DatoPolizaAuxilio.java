
package uy.com.bse.auxiliomecanico;

import java.io.Serializable;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;


public class DatoPolizaAuxilio implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String ano;
    private int coberturasRestantes;
    private int codigoError;
    private int correspondeServicio;
    private int es0Km;
    private int esCliente;
    private String estado;
    private XMLGregorianCalendar fechaDesde;
    private XMLGregorianCalendar fechaHasta;
    private int libreKM;
    private List<DatoAuxilioOtorgado> listaAuxilios;
    private String marca;
    private String mensajeError;
    private String modelo;
    private String nroChasis;
    private String nroMatricula;
    private String nroMotor;
    private int nroPoliza;
    private long sesionId;
    private String tipoCombustible;
    private String tipoVehiculo;
    private String tresPDos;
    
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public int getCoberturasRestantes() {
		return coberturasRestantes;
	}
	public void setCoberturasRestantes(int coberturasRestantes) {
		this.coberturasRestantes = coberturasRestantes;
	}
	public int getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}
	public int getCorrespondeServicio() {
		return correspondeServicio;
	}
	public void setCorrespondeServicio(int correspondeServicio) {
		this.correspondeServicio = correspondeServicio;
	}
	public int getEs0Km() {
		return es0Km;
	}
	public void setEs0Km(int es0Km) {
		this.es0Km = es0Km;
	}
	public int getEsCliente() {
		return esCliente;
	}
	public void setEsCliente(int esCliente) {
		this.esCliente = esCliente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public XMLGregorianCalendar getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(XMLGregorianCalendar fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public XMLGregorianCalendar getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(XMLGregorianCalendar fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public int getLibreKM() {
		return libreKM;
	}
	public void setLibreKM(int libreKM) {
		this.libreKM = libreKM;
	}
	public List<DatoAuxilioOtorgado> getListaAuxilios() {
		return listaAuxilios;
	}
	public void setListaAuxilios(List<DatoAuxilioOtorgado> listaAuxilios) {
		this.listaAuxilios = listaAuxilios;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNroChasis() {
		return nroChasis;
	}
	public void setNroChasis(String nroChasis) {
		this.nroChasis = nroChasis;
	}
	public String getNroMatricula() {
		return nroMatricula;
	}
	public void setNroMatricula(String nroMatricula) {
		this.nroMatricula = nroMatricula;
	}
	public String getNroMotor() {
		return nroMotor;
	}
	public void setNroMotor(String nroMotor) {
		this.nroMotor = nroMotor;
	}
	public int getNroPoliza() {
		return nroPoliza;
	}
	public void setNroPoliza(int nroPoliza) {
		this.nroPoliza = nroPoliza;
	}
	public long getSesionId() {
		return sesionId;
	}
	public void setSesionId(long sesionId) {
		this.sesionId = sesionId;
	}
	public String getTipoCombustible() {
		return tipoCombustible;
	}
	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getTresPDos() {
		return tresPDos;
	}
	public void setTresPDos(String tresPDos) {
		this.tresPDos = tresPDos;
	}

 
}
