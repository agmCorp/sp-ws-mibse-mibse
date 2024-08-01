package uy.com.bse.afiliaciones;

import java.io.Serializable;

public class AfiliacionCuenta implements Serializable {
	private static final long serialVersionUID = 5840360066032359638L;

	private String tipoPago;
	private String tipoDocumento;
	private String paisDocumento;
	private String numeroDocumento;
	private String moneda;
	private String nombreTitular;
	private String celular;
	private String email;
	private String tipoCuenta;
	private String sucursal;
	private String cuenta;
	private String subCuenta;
	private String institucion;
	private String estado;

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getPaisDocumento() {
		return paisDocumento;
	}

	public void setPaisDocumento(String paisDocumento) {
		this.paisDocumento = paisDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getSubCuenta() {
		return subCuenta;
	}

	public void setSubCuenta(String subCuenta) {
		this.subCuenta = subCuenta;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
