package uy.com.bse.consultas.entidades;

import java.io.Serializable;

public class CabezalNomina implements Serializable {
	
	private static final long serialVersionUID = -3604506093131465246L;
	private Integer numNomina;
	private String descNomina;
	private String tipoDocumento;
	private String numDocumento;
	private String nombre;
	private String rangoCapitalesInicio;
	private String rangoCapitalesFin;
	private Integer codMoneda;
	private String descMoneda;
	
	public Integer getNumNomina() {
		return numNomina;
	}
	public void setNumNomina(Integer numNomina) {
		this.numNomina = numNomina;
	}
	public String getDescNomina() {
		return descNomina;
	}
	public void setDescNomina(String descNomina) {
		this.descNomina = descNomina;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	public String getRangoCapitalesInicio() {
		return rangoCapitalesInicio;
	}
	public void setRangoCapitalesInicio(String rangoCapitalesInicio) {
		this.rangoCapitalesInicio = rangoCapitalesInicio;
	}
	public String getRangoCapitalesFin() {
		return rangoCapitalesFin;
	}
	public void setRangoCapitalesFin(String rangoCapitalesFin) {
		this.rangoCapitalesFin = rangoCapitalesFin;
	}
	public Integer getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getDescMoneda() {
		return descMoneda;
	}
	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}
	
	
	


}
