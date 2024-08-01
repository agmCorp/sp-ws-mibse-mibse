package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReporteClienteCorredor implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;
	private Integer numProductor;
	private Integer numPersona;
	private String tipoDocumento;
	private String numDocumento;
	private String razonSocial;
	private String nombre;
	private String telefono;
	private String celular;
	private String mail;
	private Integer numCliente;

	public ReporteClienteCorredor(Integer numProductor, Integer numPersona,Integer numCliente, String tipoDocumento, String numDocumento, String razonSocial, String nombre, String telefono, String celular, String mail) {
		super();
		this.numProductor = numProductor;
		this.numPersona = numPersona;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.razonSocial = razonSocial;
		this.nombre = nombre;
		this.telefono = telefono;
		this.celular = celular;
		this.mail = mail;
		this.numCliente = numCliente;
	}

	public ReporteClienteCorredor() {
		super();
	}

	public Integer getNumProductor() {
		return numProductor;
	}

	public void setNumProductor(Integer numProductor) {
		this.numProductor = numProductor;
	}

	public Integer getNumPersona() {
		return numPersona;
	}

	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
