package uy.com.bse.consultas.entidades;

import java.io.Serializable;

public class IntegranteNominaAccidenteTrabajo implements Serializable {

	private static final long serialVersionUID = 1101516112916070312L;
	private String mesCargo;
	private String tipoDocumento;
	private String descTipoDocumento;
	private String numDocumento;
	private String nombre;
	private String codTipoSalario;
	private String descTipoSalario;
	private Double montoSalario;
	private String fechaAlta;
	private String fechaBaja;

	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescTipoDocumento() {
		return descTipoDocumento;
	}

	public void setDescTipoDocumento(String descTipoDocumento) {
		this.descTipoDocumento = descTipoDocumento;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodTipoSalario() {
		return codTipoSalario;
	}

	public void setCodTipoSalario(String codTipoSalario) {
		this.codTipoSalario = codTipoSalario;
	}

	public String getDescTipoSalario() {
		return descTipoSalario;
	}

	public void setDescTipoSalario(String descTipoSalario) {
		this.descTipoSalario = descTipoSalario;
	}

	public Double getMontoSalario() {
		return montoSalario;
	}

	public void setMontoSalario(Double montoSalario) {
		this.montoSalario = montoSalario;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getMesCargo() {
		return mesCargo;
	}

	public void setMesCargo(String mesCargo) {
		this.mesCargo = mesCargo;
	}

	/*
	 * <mes-cargo>03/2016</mes-cargo>
	 * 
	 *         <tp-documento>PAS</tp-documento>
	 *         <desc-tp-documento>PASAPORTE</desc-tp-documento>
	 *         <nu-documento>46954055</nu-documento>         <nombre>LOBO NUÑEZ
	 * CESAR ALBERTO</nombre>         <cod-tp-salario>M</cod-tp-salario>
	 *         <desc-tp-salario>MENSUAL</desc-tp-salario>
	 *         <mt-salario>80424</mt-salario>         <fe-alta>01/03/2016</fe-alta>
	 *         <fe-baja>01/05/2016</fe-baja>
	 */

}
